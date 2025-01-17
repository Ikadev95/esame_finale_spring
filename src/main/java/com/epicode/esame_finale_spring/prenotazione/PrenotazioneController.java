package com.epicode.esame_finale_spring.prenotazione;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/api/prenotazioni")
@PreAuthorize("isAuthenticated()")
public class PrenotazioneController {
    private final PrenotazioneService prenotazioneService;


    @PostMapping
    public ResponseEntity<Prenotazione> createPrenotazione(@RequestBody PrenotazioneCreaRequest prenotazioneCreaRequest){
        return new ResponseEntity<>(prenotazioneService.prenotaEvento(prenotazioneCreaRequest), HttpStatus.CREATED);
    }
}
