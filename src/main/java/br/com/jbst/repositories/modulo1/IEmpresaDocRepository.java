package br.com.jbst.repositories.modulo1;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;


import br.com.jbst.entities.EmpresaDoc;

public interface IEmpresaDocRepository extends JpaRepository <EmpresaDoc, UUID> {

}