package com.epicode.esame_finale_spring.evento;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@PreAuthorize("isAuthenticated()")
@RequestMapping("api/eventi")
public class EventoController {
    private final EventoService eventoService;

    @PreAuthorize("hasRole('ORGANIZER')")
    @PostMapping
    public ResponseEntity<Evento> saveEvento(@RequestBody EventoCreaRequest eventoCreaRequest){
        return new ResponseEntity<>(eventoService.saveEvento(eventoCreaRequest), HttpStatus.CREATED);
    }

    @PreAuthorize("hasRole('ORGANIZER')")
    @PutMapping("/{id}")
    public ResponseEntity<Evento> modifyEvento(@RequestBody EventoCreaRequest eventoCreaRequest, @PathVariable Long id){
        return ResponseEntity.ok(eventoService.modifyEvento(eventoCreaRequest, id));
    }
    @PreAuthorize("hasRole('ORGANIZER')")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteEvento(@PathVariable Long id, @RequestBody Long idOrg){
        eventoService.deleteEvento(id, idOrg);
        return new ResponseEntity<>("evento eliminato", HttpStatus.NO_CONTENT);
    }


}
