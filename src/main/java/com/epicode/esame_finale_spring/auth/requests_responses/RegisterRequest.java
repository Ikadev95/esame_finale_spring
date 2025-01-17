package com.epicode.esame_finale_spring.auth.requests_responses;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class RegisterRequest {
    @NotNull
    private String username;
    @NotNull
    private String password;
    private String nome;
    private String cognome;
    private String email;
}
