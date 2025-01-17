package com.epicode.esame_finale_spring.prenotazione;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/api/prenotazioni")
@PreAuthorize("isAuthenticated()")
public class PrenotazioneController {
    private final PrenotazioneService prenotazioneService;


    @PreAuthorize("isAuthenticated()")
    @PostMapping
    public ResponseEntity<Prenotazione> createPrenotazione(
            @RequestParam Long idEvento,
            @AuthenticationPrincipal UserDetails userDetails) {

        Prenotazione prenotazione = prenotazioneService.prenotaEvento(idEvento, userDetails);

        return new ResponseEntity<>(prenotazione, HttpStatus.CREATED);
    }
}
