package com.epicode.esame_finale_spring.prenotazione;

import com.epicode.esame_finale_spring.auth.AppUser;
import com.epicode.esame_finale_spring.evento.Evento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PrenotazioneRepository extends JpaRepository<Prenotazione, Long> {
    boolean existsByEventoAndUtente(Evento evento, AppUser utente);
}
