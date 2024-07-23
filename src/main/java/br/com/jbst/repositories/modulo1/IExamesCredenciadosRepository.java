package br.com.jbst.repositories.modulo1;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.jbst.entities.ExamesCredenciados;

public interface IExamesCredenciadosRepository extends JpaRepository<ExamesCredenciados, UUID> {

}
