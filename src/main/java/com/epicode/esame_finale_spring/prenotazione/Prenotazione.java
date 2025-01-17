package com.epicode.esame_finale_spring.prenotazione;

import com.epicode.esame_finale_spring.auth.AppUser;
import com.epicode.esame_finale_spring.evento.Evento;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "prenotazioni")
public class Prenotazione {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "evento_id")
    private Evento evento;

    @ManyToOne
    @JoinColumn(name = "utente_id")
    private AppUser utente;

}