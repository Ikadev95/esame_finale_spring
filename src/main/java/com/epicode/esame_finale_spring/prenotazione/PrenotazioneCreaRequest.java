package com.epicode.esame_finale_spring.prenotazione;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class PrenotazioneCreaRequest {
    @NotNull(message = "l'id organizzatore non può essere vuoto")
    private Long idUser;
    @NotNull(message = "l'id evento non può essere vuoto")
    private Long idEvento;
}
