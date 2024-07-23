package br.com.jbst.entities;


import java.util.List;

import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "riscos")
public class Riscos {
	
	@Id
	@Column(name = "id_risco")
	private UUID idRisco;
	
	@Column(name = "grupo", length = 50, nullable = true)
	private String grupo;//Quimico, Fisico, Biológico
	
	@Column(name = "tipo", length = 50, nullable = true)
	private String tipo;//Fisico: Ruido / Quimico: Poeiras
	
	@Column(name = "fonte_geradora", length = 500, nullable = true)
	private String fonte_geradora; // AR - Máquinas
	
	@Column(name = "meios_de_propagacao", length = 500, nullable = true)
	private String meios_de_propagacao; // ar
	
	@Column(name = "cor", length = 50, nullable = true)
	private String cor;
	
	@Column(name = "meios_de_controles", length = 1000, nullable = true)
	private String meios_de_controles;
	
	@Column(name = "controles_existentes", length = 1000, nullable = true)
	private String controles_existentes;
	
	@Column(name = "controles_inexistentes", length = 1000, nullable = true)
	private String controles_inexistentes;

	
	@OneToMany(mappedBy = "riscos")
	private List<Danos> danos;
	
	@OneToOne (mappedBy = "riscos")
	private Potencial_Severidade_Danos potencial_severidade_danos;

	@OneToMany (mappedBy = "riscos")
	private List< MedidasDeControle>  medidasdecontrole;
	
	@OneToOne (mappedBy = "riscos")
	private ProbabilidadeOcorrencia probabilidadeocorrencia;
	
	@OneToOne (mappedBy = "riscos")
	private Nivel_Severidade nivel_severidade;
	

	@ManyToOne
	@JoinColumn(name = "id_funcao_doc" , nullable = true)
	private FuncaoDoc funcaodoc;
	

}
