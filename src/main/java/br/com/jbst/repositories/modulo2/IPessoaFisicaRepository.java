package br.com.jbst.repositories.modulo2;

import java.util.UUID;

import br.com.jbst.entities.PessoaFisica;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IPessoaFisicaRepository extends JpaRepository <PessoaFisica, UUID> {
}
