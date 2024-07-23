package br.com.jbst.entities;

import java.time.Instant;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.Data;

@Data
public class Recomendacoes {
	@Id
	@Column(name = "idRecomendacoes")
	private UUID idRecomendacoes;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "datahoracriacao", nullable = false)
	private Instant dataHoraCriacao;
	
	@Column(name = "Recomendacoes", length = 100, nullable = false)
	private String Recomendacoes;
	
	@Column(name = "descricao_Recomendacoes", length = 1000, nullable = false)
	private String descricao_Recomendacoes;
	
	@OneToOne
	@JoinColumn(name = "id_unidadedoc")
	private UnidadeDoc unidadedoc;
}
