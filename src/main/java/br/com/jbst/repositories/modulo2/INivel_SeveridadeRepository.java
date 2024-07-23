package br.com.jbst.repositories.modulo2;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.jbst.entities.Nivel_Severidade;

public interface INivel_SeveridadeRepository extends JpaRepository<Nivel_Severidade, UUID>{

}
