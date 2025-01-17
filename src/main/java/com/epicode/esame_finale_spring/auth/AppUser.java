package com.epicode.esame_finale_spring.auth;

import com.epicode.esame_finale_spring.prenotazione.Prenotazione;
import com.epicode.esame_finale_spring.utente.Utente;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;
import java.util.Set;

@Entity
@Table(name = "users")
@Data
public class AppUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false)
    private String password;

    @ElementCollection(fetch = FetchType.EAGER)
    @Enumerated(EnumType.STRING)
    private Set<Role> roles;

    @OneToOne(mappedBy = "appUser", cascade = CascadeType.ALL)
    private Utente utente;

    @OneToMany (mappedBy = "utente", cascade = CascadeType.ALL)
    private List<Prenotazione> prenotazioni;
}
