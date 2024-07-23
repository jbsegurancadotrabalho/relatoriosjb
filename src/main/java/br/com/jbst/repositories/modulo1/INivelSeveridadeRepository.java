package br.com.jbst.repositories.modulo1;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.jbst.entities.Nivel_Severidade;

public interface INivelSeveridadeRepository extends JpaRepository<Nivel_Severidade, UUID> {

}
