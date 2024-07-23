package br.com.jbst.repositories.modulo1;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.jbst.entities.DocumentosFree;

@Repository
public interface IDocumentosFreeRepositories extends JpaRepository<DocumentosFree, UUID> {

  
    
    @Query("SELECT MAX(r.numerodocumentofree) FROM DocumentosFree r")
    Integer findMaxDocumentosFree();
}
