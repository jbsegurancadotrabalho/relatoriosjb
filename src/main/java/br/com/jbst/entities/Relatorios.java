package br.com.jbst.entities;
import java.time.Instant;

import java.util.List;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.Data;

@Data
@Entity
@Table(name = "relatorios")
public class Relatorios {
	
	@Id 	
	@Column(name = "id_relatorios")
	private UUID idRelatorios;

	@Column(name = "nome_relatorio",  length = 100, nullable = true)
	private String nome_relatorio;
	
	
	@Column(name = "descricao_relatorio",  length = 500, nullable = true)
	private String descricao_relatorio;
	
	@Column(name = "numerorelatorio", nullable = true)
    private Integer numeroRelatorio; // Make sure this attribute exists
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "datainicio", nullable = true)
	private Instant datainicio;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "vigencia", nullable = true)
	private Instant vigencia;
	
	@Column(name = "validade", nullable = true)
	private String validade;
	
	@OneToMany(mappedBy = "relatorios")
	private List<Associacoes> associacoes;

	
}
