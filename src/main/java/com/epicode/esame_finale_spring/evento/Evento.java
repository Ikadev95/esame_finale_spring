package com.epicode.esame_finale_spring.evento;

import com.epicode.esame_finale_spring.prenotazione.Prenotazione;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Entity
@Data
@Table(name = "eventi")
public class Evento {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    private LocalDate data;
    private String luogo;
    private int postiDisponibili;
    private String titolo;
    private String descrizione;
    private Long idOrganizzatore;
    @OneToMany(mappedBy = "evento", cascade = CascadeType.ALL)
    private List<Prenotazione> prenotazioni;

}