package com.epicode.esame_finale_spring.prenotazione;

import com.epicode.esame_finale_spring.auth.AppUser;
import com.epicode.esame_finale_spring.auth.AppUserRepository;
import com.epicode.esame_finale_spring.evento.Evento;
import com.epicode.esame_finale_spring.evento.EventoRepository;
import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

@Service
@AllArgsConstructor
@Validated
public class PrenotazioneService {
   private final PrenotazioneRepository prenotazioneRepository;
    private final EventoRepository eventoRepository;
    private final AppUserRepository utenteRepository;

    //prenota un evento
    public Prenotazione prenotaEvento(@Valid Long idEvento,
                                      @AuthenticationPrincipal UserDetails userDetails) {

        Evento evento = eventoRepository.findById(idEvento)
                .orElseThrow(() -> new EntityNotFoundException("Evento non trovato"));


        AppUser utente = utenteRepository.findByUsername(userDetails.getUsername())
                .orElseThrow(() -> new EntityNotFoundException("Utente non trovato"));

        if (prenotazioneRepository.existsByEventoAndUtente(evento, utente)) {
            throw new EntityExistsException("L'utente ha già prenotato questo evento");
        }

        Prenotazione prenotazione = new Prenotazione();
        prenotazione.setEvento(evento);
        prenotazione.setUtente(utente);

        return prenotazioneRepository.save(prenotazione);
    }
}
