package br.com.jbst.entities;

import java.time.Instant;
import java.util.UUID;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.Data;

@Data
@Entity
@Table(name = "apresentacao")
public class Apresentacao {

	
	@Id
	@Column(name = "idapresentacao")
	private UUID idApresentacao;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "datahoracriacao", nullable = false)
	private Instant dataHoraCriacao;
	
	@Column(name = "apresentacao", length = 100, nullable = false)
	private String apresentacao;
	
	@Column(name = "descricao_apresentacao", length = 1000, nullable = false)
	private String descricao_apresentacao;
	
	@ManyToOne
	@JoinColumn (name = "id_unidadedoc")
	private UnidadeDoc unidadedoc;
}
