package br.com.jbst.repositories.modulo2;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import br.com.jbst.entities.Fotos;

public interface IFotosRepository extends JpaRepository<Fotos, UUID> {
	@Transactional
	@Modifying
	@Query(value = "INSERT INTO fotos (id_fotos, nomeFoto, id_formacao_saude) "
			+ "VALUES (:idFotos, :nomeFoto, :idFormacaoSaude)", nativeQuery = true)
	void salvarFotosFormacaoSaude(@Param("idFotos") UUID idFotos, @Param("nomeFoto") byte[] bs,
			@Param("idFormacaoSaude") UUID idFormacaoSaude);
}
