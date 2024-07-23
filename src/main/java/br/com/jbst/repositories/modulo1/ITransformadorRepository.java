package br.com.jbst.repositories.modulo1;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.jbst.entities.LaudoEletrico.Transformadores;

public interface ITransformadorRepository extends JpaRepository<Transformadores, UUID>{

}
