package br.com.jbst.repositories.modulo2;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.jbst.entities.FotosAnexosEntity;

public interface IFotosAnexosRepository extends JpaRepository<FotosAnexosEntity, UUID> {

}
