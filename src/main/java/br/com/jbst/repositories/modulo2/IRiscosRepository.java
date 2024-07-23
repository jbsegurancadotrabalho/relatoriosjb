package br.com.jbst.repositories.modulo2;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.jbst.DTO.GetRiscosDTO;
import br.com.jbst.entities.Riscos;
import jakarta.transaction.Transactional;

public interface IRiscosRepository extends JpaRepository<Riscos, UUID> {
	   @Modifying
	    @Query(value = "INSERT INTO riscos (id_risco, grupo, tipo, fonte_geradora, meios_de_propagacao, cor, meios_de_controles, controles_existentes, controles_inexistentes) VALUES (?1, ?2, ?3, ?4, ?5, ?6, ?7, ?8, ?9)", nativeQuery = true)
	    void salvarRisco(UUID idRisco, String grupo, String tipo, String fonteGeradora, String meiosDePropagacao, String cor, String meiosDeControles, String controlesExistentes, String controlesInexistentes);
	
	   @Modifying
	 @Query("UPDATE Riscos r SET r.grupo = :grupo, r.tipo = :tipo, r.fonte_geradora = :fonteGeradora, r.meios_de_propagacao = :meiosDePropagacao, " +
	        "r.cor = :cor, r.meios_de_controles = :meiosDeControles, r.controles_existentes = :controlesExistentes, r.controles_inexistentes = :controlesInexistentes " +
	        "WHERE r.idRisco = :idRisco")
	 void atualizarRisco(@Param("idRisco") UUID idRisco, @Param("grupo") String grupo, @Param("tipo") String tipo, 
	                     @Param("fonteGeradora") String fonteGeradora, @Param("meiosDePropagacao") String meiosDePropagacao, 
	                     @Param("cor") String cor, @Param("meiosDeControles") String meiosDeControles, 
	                     @Param("controlesExistentes") String controlesExistentes, @Param("controlesInexistentes") String controlesInexistentes);


	   @Query("SELECT new br.com.jbst.DTO.GetRiscosDTO(r.idRisco, r.grupo, r.tipo, r.fonte_geradora, r.meios_de_propagacao, r.cor, r.meios_de_controles, r.controles_existentes, r.controles_inexistentes) FROM Riscos r")
	    List<GetRiscosDTO> buscarCamposEspecificos();
	   
	   @Query("SELECT new br.com.jbst.DTO.GetRiscosDTO(r.idRisco, r.grupo, r.tipo, r.fonte_geradora, r.meios_de_propagacao, r.cor, r.meios_de_controles, r.controles_existentes, r.controles_inexistentes) FROM Riscos r WHERE r.idRisco = :idRisco")
	   GetRiscosDTO buscarPorId(@Param("idRisco") UUID idRisco);
	   
	   @Modifying
	   @Query("DELETE FROM Riscos r WHERE r.idRisco = :idRisco")
	   void excluirRiscoPorId(@Param("idRisco") UUID idRisco);



}