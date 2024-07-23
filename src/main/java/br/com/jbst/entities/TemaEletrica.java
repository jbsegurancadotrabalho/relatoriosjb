package br.com.jbst.entities;

import java.time.Instant;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.Data;

@Data
public class TemaEletrica {

	
	@Id
	@Column(name = "idTema")
	private UUID idTema;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "datahoracriacao", nullable = false)
	private Instant dataHoraCriacao;
	
	@Column(name = "tema", length = 100, nullable = false)
	private String tema;
	
	@Column(name = "descricao_tema", length = 1000, nullable = false)
	private String descricao_tema;
	
	@ManyToOne
	@JoinColumn(name = "id_unidadedoc")
	private UnidadeDoc unidadedoc;
}
