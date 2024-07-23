package br.com.jbst.repositories.modulo1;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.jbst.entities.Associacoes;

public interface IAssociacoesRepository extends JpaRepository<Associacoes, UUID> {

	@Query("SELECT MAX(a.numeroAssociacoes) FROM Associacoes a")
    Integer findMaxNumeroAssociacao();
}
