package br.com.jbst.repositories.modulo1;


import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.jbst.entities.UnidadeDoc;

public interface IUnidadeDocRepository extends JpaRepository <UnidadeDoc, UUID> {

	
	
}