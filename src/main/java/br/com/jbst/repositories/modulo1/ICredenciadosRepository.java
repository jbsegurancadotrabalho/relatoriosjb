package br.com.jbst.repositories.modulo1;

import java.util.List;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.jbst.entities.Credenciados;
import br.com.jbst.entities.Exames;

public interface ICredenciadosRepository extends JpaRepository<Credenciados, UUID> {

	boolean existsByCnpj(String cnpj);

	@Query("SELECT e FROM Exames e " +
		       "JOIN e.examescredenciados ec " +
		       "JOIN ec.credenciados c " +
		       "JOIN c.endereco en " +
		       "WHERE en.localidade = :localidade AND en.uf = :uf")
		List<Exames> findExamesByCredenciadosLocation(@Param("localidade") String localidade, @Param("uf") String uf);


}
