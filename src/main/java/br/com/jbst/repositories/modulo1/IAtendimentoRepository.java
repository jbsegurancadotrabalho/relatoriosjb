package br.com.jbst.repositories.modulo1;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.jbst.entities.Atendimento;

public interface IAtendimentoRepository extends JpaRepository<Atendimento, UUID>{

	
	@Query("SELECT MAX(a.numeroatendimento) FROM Atendimento a")
    Integer findMaxNumeroAtendimento();
}
