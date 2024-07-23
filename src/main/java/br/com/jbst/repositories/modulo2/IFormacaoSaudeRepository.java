package br.com.jbst.repositories.modulo2;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.jbst.entities.FormacaoSaude;

public interface IFormacaoSaudeRepository extends JpaRepository<FormacaoSaude, UUID>{

}
