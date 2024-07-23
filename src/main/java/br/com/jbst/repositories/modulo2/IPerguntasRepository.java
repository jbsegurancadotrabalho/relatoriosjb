package br.com.jbst.repositories.modulo2;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.jbst.DTO2.GetPerguntasDTO;
import br.com.jbst.entities.LaudoEletrico.Perguntas;

@Repository
public interface IPerguntasRepository extends JpaRepository<Perguntas, UUID> {

	@Transactional
	@Modifying
	@Query(value = "INSERT INTO perguntas (id_perguntas, pergunta, id_quadros_medicoes) "
			+ "VALUES (:idPerguntas, :pergunta, :idQuadrosMedicoes)", nativeQuery = true)
	void salvarPerguntaComMedicao(@Param("idPerguntas") UUID idPerguntas, @Param("pergunta") String pergunta,
			@Param("idQuadrosMedicoes") UUID idQuadrosMedicoes);

	@Transactional
	@Modifying
	@Query(value = "INSERT INTO perguntas (id_perguntas, pergunta, id_quadros_paineis) "
			+ "VALUES (:idPerguntas, :pergunta, :idQuadrosPaineis)", nativeQuery = true)
	void salvarPerguntaPaineis(@Param("idPerguntas") UUID idPerguntas, @Param("pergunta") String pergunta,
			@Param("idQuadrosPaineis") UUID idQuadrosPaineis);

	@Transactional
	@Modifying
	@Query(value = "INSERT INTO perguntas (id_perguntas, pergunta, id_protecoes) "
			+ "VALUES (:idPerguntas, :pergunta, :idProtecoes)", nativeQuery = true)
	void salvarPerguntaProtecoes(@Param("idPerguntas") UUID idPerguntas, @Param("pergunta") String pergunta,
			@Param("idProtecoes") UUID idProtecoes);

	@Transactional
	@Modifying
	@Query(value = "INSERT INTO perguntas (id_perguntas, pergunta, id_tomadas) "
			+ "VALUES (:idPerguntas, :pergunta, :idTomadas)", nativeQuery = true)
	void salvarPerguntasTomadas(@Param("idPerguntas") UUID idPerguntas, @Param("pergunta") String pergunta,
			@Param("idTomadas") UUID idTomadas);

	@Modifying
	@Query(value = "INSERT INTO perguntas (id_perguntas, pergunta, id_procedimento) "
			+ "VALUES (:idPerguntas, :pergunta, :idProcedimentos)", nativeQuery = true)
	@Transactional
	void salvarPerguntasProcedimentos(@Param("idPerguntas") UUID idPerguntas, @Param("pergunta") String pergunta,
			@Param("idProcedimentos") UUID idProcedimentos);

	@Modifying
	@Query(value = "INSERT INTO perguntas (id_perguntas, pergunta, id_condutores) "
			+ "VALUES (:idPerguntas, :pergunta, :idCondutores)", nativeQuery = true)
	@Transactional
	void salvarPerguntasCondutores(@Param("idPerguntas") UUID idPerguntas, @Param("pergunta") String pergunta,
			@Param("idCondutores") UUID idCondutores);

	@Modifying
	@Query(value = "INSERT INTO perguntas (id_perguntas, pergunta, id_luminarias) "
			+ "VALUES (:idPerguntas, :pergunta, :idLuminarias)", nativeQuery = true)
	@Transactional
	void salvarPerguntasLuminarias(@Param("idPerguntas") UUID idPerguntas, @Param("pergunta") String pergunta,
			@Param("idLuminarias") UUID idLuminarias);

	@Modifying
	@Query(value = "INSERT INTO perguntas (id_perguntas, pergunta, id_aterramento) "
			+ "VALUES (:idPerguntas, :pergunta, :idAterramento)", nativeQuery = true)
	@Transactional
	void salvarPerguntasAterramento(@Param("idPerguntas") UUID idPerguntas, @Param("pergunta") String pergunta,
			@Param("idAterramento") UUID idAterramento);

	@Query("SELECT new br.com.jbst.DTO2.GetPerguntasDTO(p.idPerguntas, p.pergunta) FROM Perguntas p")
	List<GetPerguntasDTO> buscarTodasAsPerguntas();

	@Query("SELECT new br.com.jbst.DTO2.GetPerguntasDTO(p.idPerguntas, p.pergunta) FROM Perguntas p WHERE p.idPerguntas = :id")
	GetPerguntasDTO buscarPerguntaPorId(UUID id);


}
