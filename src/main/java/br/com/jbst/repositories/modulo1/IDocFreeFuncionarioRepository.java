package br.com.jbst.repositories.modulo1;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.jbst.entities.DocFreeFuncionario;

public interface IDocFreeFuncionarioRepository extends JpaRepository<DocFreeFuncionario, UUID> {

    @Query("SELECT MAX(r.numerodocumentofree) FROM DocFreeFuncionario r")
    Integer findMaxDocumentosFree();
}
