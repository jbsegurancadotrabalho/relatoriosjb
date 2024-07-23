package br.com.jbst.repositories.modulo1;


import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.jbst.entities.Relatorios;

@Repository
public interface IRelatoriosRepository extends JpaRepository<Relatorios, UUID> {

    @Query("SELECT MAX(r.numeroRelatorio) FROM Relatorios r")
    Integer findMaxNumeroRelatorio();
}
