package com.epicode.esame_finale_spring.evento;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface EventoRepository extends JpaRepository<Evento, Long> {
    @Query("SELECT e.idOrganizzatore FROM Evento e WHERE e.id = :id")
    long getIdOrganizer(@Param("id") Long id);
}
