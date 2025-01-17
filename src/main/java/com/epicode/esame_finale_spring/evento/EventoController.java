package com.epicode.esame_finale_spring.evento;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@PreAuthorize("isAuthenticated()")
@RequestMapping("api/eventi")
public class EventoController {
    private final EventoService eventoService;

    @PreAuthorize("hasRole('ORGANIZER')")
    @PostMapping
    public ResponseEntity<Evento> saveEvento(@RequestBody EventoCreaRequest eventoCreaRequest,  @AuthenticationPrincipal UserDetails userDetail){
        return new ResponseEntity<>(eventoService.saveEvento(eventoCreaRequest,userDetail), HttpStatus.CREATED);
    }

    @PreAuthorize("hasRole('ORGANIZER') || hasRole('ADMIN') ")
    @PutMapping("/{id}")
    public ResponseEntity<Evento> modifyEvento(@RequestBody EventoCreaRequest eventoCreaRequest, @PathVariable Long id,  @AuthenticationPrincipal UserDetails userDetail){
        return ResponseEntity.ok(eventoService.modifyEvento(eventoCreaRequest, id, userDetail));
    }
    @PreAuthorize("hasRole('ORGANIZER') || hasRole('ADMIN') ")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteEvento(@PathVariable Long id,  @AuthenticationPrincipal UserDetails userDetail){
        eventoService.deleteEvento(id, userDetail);
        return new ResponseEntity<>("evento eliminato", HttpStatus.NO_CONTENT);
    }
    @PreAuthorize("isAuthenticated()")
    @GetMapping
    public ResponseEntity<List<Evento>> getAllEventi(){
        return ResponseEntity.ok(eventoService.getEventi());
    }


}
