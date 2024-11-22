package br.com.jbst.repositories.modulo1;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.jbst.entities.Funcao;
import br.com.jbst.entities.FuncaoDocEntity;

public interface IFuncaoDocEntityRepository extends JpaRepository<FuncaoDocEntity, UUID>{

	@Query("SELECT f FROM FuncaoDocEntity f WHERE f.nome_da_funcao = :nomeDaFuncao")
	FuncaoDocEntity findByFuncaoDoc(@Param("nomeDaFuncao") String nomeDaFuncao);

}
