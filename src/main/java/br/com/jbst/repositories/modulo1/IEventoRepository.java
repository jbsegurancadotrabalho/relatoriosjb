package br.com.jbst.repositories.modulo1;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.jbst.entities.Evento;

public interface IEventoRepository extends JpaRepository<Evento, UUID> {

	@Query("SELECT MAX(e.numeroevento) FROM Evento e")
    Integer findMaxNumeroEvento();
	
	@Query("SELECT e FROM Evento e WHERE e.dia_hora BETWEEN :startOfMonth AND :endOfMonth")
    List<Evento> findEventosDoMes(Instant startOfMonth, Instant endOfMonth);
	
}
