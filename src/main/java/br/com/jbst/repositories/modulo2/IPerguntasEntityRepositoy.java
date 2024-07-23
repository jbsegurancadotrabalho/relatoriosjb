package br.com.jbst.repositories.modulo2;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import br.com.jbst.entities.LaudoEletrico.PerguntasEntity;

public interface IPerguntasEntityRepositoy extends JpaRepository<PerguntasEntity, UUID>{
	
	@Transactional
	@Modifying
	@Query("DELETE FROM PerguntasEntity p WHERE p.idPerguntas = :idPerguntas")
	void deletePerguntaPorId(@Param("idPerguntas") UUID idPerguntas);


}
