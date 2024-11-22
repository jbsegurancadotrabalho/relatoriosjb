package br.com.jbst.repositories.modulo1;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.jbst.entities.Agendamento;

public interface IAgendamentoRepository extends JpaRepository<Agendamento, UUID> {
	@Query("SELECT MAX(a.numeroagendamento) FROM Agendamento a")
    Integer findMaxNumeroAgendamento();
	
	  @Query("SELECT a FROM Agendamento a JOIN a.agenda ag WHERE EXTRACT(MONTH FROM ag.dia_hora) = :mes AND EXTRACT(YEAR FROM ag.dia_hora) = :ano AND a.funcionario IS NOT NULL")
	    List<Agendamento> findAgendamentoFuncionariosByMesAno(@Param("mes") int mes, @Param("ano") int ano);
	  
	  @Query("SELECT a FROM Agendamento a JOIN a.agenda ag WHERE EXTRACT(MONTH FROM ag.dia_hora) = :mes AND EXTRACT(YEAR FROM ag.dia_hora) = :ano AND a.pessoafisica IS NOT NULL")
	    List<Agendamento> findAgendamentoPessoaFisicaByMesAno(@Param("mes") int mes, @Param("ano") int ano);
	}

