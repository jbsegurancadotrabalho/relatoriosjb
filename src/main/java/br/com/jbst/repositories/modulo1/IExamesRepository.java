package br.com.jbst.repositories.modulo1;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.jbst.entities.Exames;

public interface IExamesRepository extends JpaRepository<Exames, UUID> {

    //@Query("SELECT e FROM Exames e WHERE e.nome_exame = :nomeExame")
   // List<Exames> findByNomeExame(@Param("nomeExame") String nomeExame);
	
	//@Query("SELECT e FROM Exames e LEFT JOIN FETCH e.examescredenciados WHERE e.nome_exame = :nomeExame")
	//List<Exames> findByNomeExameWithCredenciados(@Param("nomeExame") String nomeExame);
	
	@Query("SELECT e FROM Exames e LEFT JOIN FETCH e.examescredenciados ec " +
		       "JOIN ec.credenciados c " +
		       "JOIN c.endereco en " +
		       "WHERE e.nome_exame = :nomeExame AND en.localidade = :localidade AND en.uf = :uf AND en.bairro = :bairro " +
		       "ORDER BY ec.valorCredenciado")
		List<Exames> findByNomeExameAndLocalidadeAndBairro(@Param("nomeExame") String nomeExame,
		                                                   @Param("localidade") String localidade,
		                                                   @Param("uf") String uf,
		                                                   @Param("bairro") String bairro);
}