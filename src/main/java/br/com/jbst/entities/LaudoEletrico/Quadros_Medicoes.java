package br.com.jbst.entities.LaudoEletrico;

import java.util.List;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "quadros_medicoes")
public class Quadros_Medicoes {
	@Id
	@Column(name = "idquadros_medicoes")
	private UUID idQuadros_Medicoes;

	@Column(name = "tipo_tensao", length = 100, nullable = false)
	private String tipo_tensao;

	@Column(name = "nomeQuadroMedicao", length = 100, nullable = false)
	private String nomeQuadroMedicao;

	@Column(name = "descricaoQuadroMedicao", length = 500, nullable = false)
	private String descricaoQuadroMedicao;
	
	@ManyToOne
	@JoinColumn (name = "id_inspecao")
	private Inspecao inspecao;
	
	@OneToMany (mappedBy = "quadros_medicoes")
	private List<Perguntas> perguntas;

}
