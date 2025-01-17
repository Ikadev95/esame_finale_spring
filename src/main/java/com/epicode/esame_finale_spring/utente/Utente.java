package com.epicode.esame_finale_spring.utente;

import com.epicode.esame_finale_spring.auth.AppUser;
import com.epicode.esame_finale_spring.prenotazione.Prenotazione;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
@Table(name = "utenti")
public class Utente {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    private String nome;
    private String cognome;
    private String email;
    @OneToOne
    @JoinColumn(name ="user_id", nullable = false, unique = true)
    private AppUser appUser;

}