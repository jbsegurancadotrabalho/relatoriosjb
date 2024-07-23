package br.com.jbst.repositories.modulo1;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.jbst.entities.LaudoEletrico.Gerador;

public interface IGeradorRepository extends JpaRepository <Gerador, UUID>{

}
