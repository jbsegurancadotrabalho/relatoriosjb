package br.com.jbst.repositories.modulo2;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.jbst.entities.LaudoEletrico.Inspecao;

public interface IInspecaoRepository extends  JpaRepository<Inspecao, UUID> {


	@Query("SELECT MAX(i.numeroinspecao) FROM Inspecao i")
    Integer findMaxNumeroInspecao();
}
