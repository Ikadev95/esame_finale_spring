package com.epicode.esame_finale_spring.evento;

import com.epicode.esame_finale_spring.auth.AppUser;
import com.epicode.esame_finale_spring.auth.AppUserRepository;
import com.epicode.esame_finale_spring.utente.UtenteRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.util.Optional;

@Service
@AllArgsConstructor
@Validated
public class EventoService {
    private final EventoRepository eventoRepository;
    private final AppUserRepository utenteRepository;

    //salvo un evento
    public Evento saveEvento(@Valid EventoCreaRequest eventoCreaRequest,@AuthenticationPrincipal UserDetails userDetails){

        Evento e = new Evento();
        if(eventoCreaRequest.getData().isBefore(LocalDate.now())){
            throw new DateTimeException("data non valida");
        }
        AppUser utente = utenteRepository.findByUsername(userDetails.getUsername()).get();
        e.setData(eventoCreaRequest.getData());
        e.setTitolo(eventoCreaRequest.getTitolo());
        e.setDescrizione(eventoCreaRequest.getDescrizione());
        e.setLuogo(eventoCreaRequest.getLuogo());
        e.setIdOrganizzatore(utente.getId());
        if(eventoCreaRequest.getPostiDisponibili() <= 0){
            throw new IllegalArgumentException("I posti disponibili devono essere > 0");
        }
        e.setPostiDisponibili(eventoCreaRequest.getPostiDisponibili());

        return eventoRepository.save(e);
    }

    //modifico un evento
    public Evento modifyEvento(@Valid EventoCreaRequest e, Long id, @AuthenticationPrincipal UserDetails userDetails) {
        Optional<Evento> eventoEsistente = eventoRepository.findById(id);
        AppUser utente = utenteRepository.findByUsername(userDetails.getUsername()).get();

        if (eventoEsistente.isPresent()) {
            Evento evento = eventoEsistente.get();

            if (!evento.getIdOrganizzatore().equals(utente.getId())) {
                throw new IllegalArgumentException("L'ID dell'organizzatore non corrisponde all'evento");
            }

            BeanUtils.copyProperties(e, evento);

            return eventoRepository.save(evento);
        } else {
            throw new EntityNotFoundException("Evento non trovato");
        }
    }

    //elimino evento
    public Boolean deleteEvento (Long id, @AuthenticationPrincipal UserDetails userDetails){
        if(!eventoRepository.existsById(id)){
            throw new EntityNotFoundException("l'evento cercato non esiste");
        }
        AppUser utente = utenteRepository.findByUsername(userDetails.getUsername()).get();
        if(eventoRepository.getIdOrganizer(id) != utente.getId()){
            throw new IllegalArgumentException("L'ID dell'organizzatore non corrisponde all'evento");
        }
         eventoRepository.deleteById(id);
        return true;
    }


}
