package br.com.jbst.entities;

import java.util.List;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "cenario")
public class Cenario {

	@Id
	@Column(name = "id_cenario")
	private UUID idCenario;
	
	@Column(name = "nomeAtividade", length = 500, nullable = true)
	private String nomeAtividade;
	
	@Column(name = "descricaoAtividade", length = 500, nullable = true)
	private String descricaoAtividade;
	
	@Column(name = "area", length = 500, nullable = true)
	private String area;
     
	@ManyToOne
	@JoinColumn (name = "id_gho_setor")
	private GHO_SETOR gho_setor;

	@OneToMany (mappedBy = "cenario")
	private List <Etapas> etapas;
	

	@OneToMany (mappedBy = "cenario")
	private List <FuncaoEspecifica> funcaoespecifica;
	
	@OneToMany (mappedBy = "cenario")
	private List <Associacoes> associacoes;
	}
