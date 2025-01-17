package com.epicode.esame_finale_spring.utente;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("api/utenti")
public class UtentiController {

    private final UtenteService utenteService;

    @GetMapping
    public ResponseEntity<List<Utente>> getUtenti(){
        List<Utente> utenti = utenteService.findAllUsers();
        return ResponseEntity.ok(utenti);
    }
}
