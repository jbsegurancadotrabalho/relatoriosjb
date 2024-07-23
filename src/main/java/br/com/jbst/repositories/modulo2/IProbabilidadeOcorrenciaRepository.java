package br.com.jbst.repositories.modulo2;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.jbst.entities.Perigo;
import br.com.jbst.entities.ProbabilidadeOcorrencia;

public interface IProbabilidadeOcorrenciaRepository extends JpaRepository< ProbabilidadeOcorrencia, UUID> {

	Optional<Perigo> findByCategoria(String categoria);

}
