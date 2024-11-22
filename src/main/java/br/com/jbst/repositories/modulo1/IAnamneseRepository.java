package br.com.jbst.repositories.modulo1;

import java.util.List;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import br.com.jbst.entities.Anamnese;

public interface IAnamneseRepository extends JpaRepository<Anamnese, UUID> {

	
	@Query("SELECT MAX(a.numeroanamnese) FROM Anamnese a")
    Integer findMaxNumeroAnamnese();
	
    @Query("SELECT t FROM Anamnese t WHERE EXTRACT(MONTH FROM t.dia_hora) = :mes AND EXTRACT(YEAR FROM t.dia_hora) = :ano")
    List<Anamnese> findAnamneseByMesAndAno(@Param("mes") int mes, @Param("ano") int ano);
	}
