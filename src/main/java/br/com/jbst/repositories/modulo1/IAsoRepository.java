package br.com.jbst.repositories.modulo1;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.jbst.entities.ASO;
import br.com.jbst.entities.Anamnese;

public interface IAsoRepository extends JpaRepository<ASO, UUID> {

	
	@Query("SELECT MAX(a.numeroaso) FROM ASO a")
    Integer findMaxNumeroAso();
	
	
	 @Query("SELECT a FROM ASO a WHERE EXTRACT(MONTH FROM a.dia_hora) = :mes AND EXTRACT(YEAR FROM a.dia_hora) = :ano")
	    List<ASO> findAsoByMesAndAno(@Param("mes") int mes, @Param("ano") int ano);
		}