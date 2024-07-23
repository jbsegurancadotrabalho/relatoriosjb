package br.com.jbst.entities;

import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "epi_funcaodoc")
public class EpiFuncaodoc {
	@Id // Campo 1
	@Column(name = "id_epi_funcaodoc")
	private UUID id_epi_funcaodoc;

	// Campo 2
	@Column(name = "nome__epi_funcaodoc", length = 500, nullable = false)
	private String nome__epi_funcaodoc;

	// Campo 3
	@Column(name = "descricao_epi_funcaodoc", length = 500, nullable = false)
	private String descricao_epi_funcaodoc;

	@ManyToOne
	@JoinColumn(name = "id_funcaodoc")
	private FuncaoDoc funcaodoc;
}
