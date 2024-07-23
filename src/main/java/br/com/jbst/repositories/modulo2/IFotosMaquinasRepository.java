package br.com.jbst.repositories.modulo2;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.jbst.entities.FotosMaquinasEntity;

public interface IFotosMaquinasRepository extends JpaRepository<FotosMaquinasEntity, UUID> {

}
