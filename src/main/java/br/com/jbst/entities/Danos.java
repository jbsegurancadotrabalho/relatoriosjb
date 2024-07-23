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
@Table(name = "danos")
public class Danos {
	@Id
	@Column(name = "id_danos")
	private UUID idDanos;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "datahoracriacao", nullable = false) 
	private Instant dataHoraCriacao;

	@Column(name = "nomeDano", length = 100, nullable = false)
	private String nomeDano;

	@Column(name = "descricaoDano", length = 500, nullable = false)
	private String descricaoDano;
	
	@ManyToOne
	@JoinColumn(name = "id_riscos", nullable = true)
	private Riscos riscos;
	
	@ManyToOne
	@JoinColumn (name = "id_perigo", nullable = true)
	private Perigo perigo;
}
