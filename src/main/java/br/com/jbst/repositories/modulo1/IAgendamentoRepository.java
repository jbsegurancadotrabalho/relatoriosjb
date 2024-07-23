package br.com.jbst.repositories.modulo1;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.jbst.entities.Agendamento;

public interface IAgendamentoRepository extends JpaRepository<Agendamento, UUID> {
	@Query("SELECT MAX(a.numeroagendamento) FROM Agendamento a")
    Integer findMaxNumeroAgendamento();
}
