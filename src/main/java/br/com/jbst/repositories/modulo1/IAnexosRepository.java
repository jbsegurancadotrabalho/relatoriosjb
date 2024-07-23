package br.com.jbst.repositories.modulo1;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.jbst.entities.Anexos;

public interface IAnexosRepository extends JpaRepository <Anexos, UUID>{

}
