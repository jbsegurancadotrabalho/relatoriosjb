package br.com.jbst.repositories.modulo2;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.jbst.entities.GHO_SETOR;

public interface IGho_SetorRepository extends JpaRepository<GHO_SETOR, UUID> {

}
