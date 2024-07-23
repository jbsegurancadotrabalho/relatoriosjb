package br.com.jbst.repositories.modulo2;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.jbst.entities.LaudoEletrico.Quadros_Medicoes;

public interface IQuadros_MedicoesRepository extends JpaRepository <Quadros_Medicoes, UUID> {

}
