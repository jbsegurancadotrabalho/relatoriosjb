package br.com.jbst.repositories.modulo2;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.jbst.entities.RiscosEntity;

public interface IRiscosEntityRepository extends JpaRepository<RiscosEntity, UUID> {

}