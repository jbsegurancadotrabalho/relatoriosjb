package br.com.jbst.repositories.modulo1;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.jbst.entities.GHO_SETOR;

public interface ISetor_GHORepository extends JpaRepository <GHO_SETOR, UUID> {

}
