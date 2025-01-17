package com.epicode.esame_finale_spring.evento;

import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.util.Optional;

@Service
@AllArgsConstructor
@Validated
public class EventoService {
    private final EventoRepository eventoRepository;

    //salvo un evento
    public Evento saveEvento(@Valid EventoCreaRequest eventoCreaRequest){

        Evento e = new Evento();
        if(eventoCreaRequest.getData().isBefore(LocalDate.now())){
            throw new DateTimeException("data non valida");
        }
        e.setData(eventoCreaRequest.getData());
        e.setTitolo(eventoCreaRequest.getTitolo());
        e.setDescrizione(eventoCreaRequest.getDescrizione());
        e.setLuogo(eventoCreaRequest.getLuogo());
        e.setIdOrganizzatore(eventoCreaRequest.getIdOrganizzatore());

        return eventoRepository.save(e);
    }

    //modifico un evento
    public Evento modifyEvento(@Valid EventoCreaRequest e, Long id) {
        Optional<Evento> eventoEsistente = eventoRepository.findById(id);

        if (eventoEsistente.isPresent()) {
            Evento evento = eventoEsistente.get();

            if (!evento.getIdOrganizzatore().equals(e.getIdOrganizzatore())) {
                throw new IllegalArgumentException("L'ID dell'organizzatore non corrisponde all'evento");
            }

            BeanUtils.copyProperties(e, evento);

            return eventoRepository.save(evento);
        } else {
            throw new EntityNotFoundException("Evento non trovato");
        }
    }


}
