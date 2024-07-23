package br.com.jbst.repositories.modulo2;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.jbst.entities.LaudoEletrico.Quadros_Paineis;

public interface IQuadros_PaineisRepository extends JpaRepository<Quadros_Paineis, UUID> {

}
