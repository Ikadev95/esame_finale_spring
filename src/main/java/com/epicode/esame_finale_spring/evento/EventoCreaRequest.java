package com.epicode.esame_finale_spring.evento;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;

@Data
public class EventoCreaRequest {
    @NotNull(message = "la data non può essere vuota")
    private LocalDate data;
    @NotBlank(message = "il luogo non può essere vuoto")
    private String luogo;
    @NotNull (message = "i posti disponibili devono essere inseriti")
    private int postiDisponibili;
    private String titolo;
    private String descrizione;
}
