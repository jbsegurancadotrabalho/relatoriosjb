package br.com.jbst.repositories.modulo2;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.jbst.entities.LaudoEletrico.Maquinas_LaudoEletrico;

public interface IMaquinas_LaudoEletricoServiceRepository extends JpaRepository<Maquinas_LaudoEletrico, UUID> {

}
