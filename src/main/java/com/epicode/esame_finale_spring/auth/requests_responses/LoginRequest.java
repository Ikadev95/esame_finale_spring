package com.epicode.esame_finale_spring.auth.requests_responses;

import lombok.Data;

@Data
public class LoginRequest {
    private String username;
    private String password;
}
