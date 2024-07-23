package br.com.jbst.repositories.modulo1;

import java.util.UUID;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.jbst.entities.Agenda;

public interface IAgendaRepository extends JpaRepository <Agenda, UUID> {

	@Query("SELECT MAX(a.numeroagenda) FROM Agenda a")
    Integer findMaxNumeroAgenda();
}
