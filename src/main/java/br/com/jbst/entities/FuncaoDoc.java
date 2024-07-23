package br.com.jbst.entities;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "funcao_doc")
public class FuncaoDoc {
	@Id
	@Column(name = "id_funcao_doc")
	private UUID idFuncaoDoc;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "datahoracriacao", nullable = false)
	private Instant dataHoraCriacao;

	@Column(name = "setor_gho", length = 100, nullable = false)
	private String setor_gho;

	
	@Column(name = "nome_da_funcao", length = 100, nullable = false)
	private String nome_da_funcao;
	
	
	@Column(name = "cenario_funcaodoc", length = 255, nullable = false)
	private String cenario_funcaodoc;

	@ManyToOne
	@JoinColumn(name = "id_funcao" , nullable = true)
	private Funcao funcao;

	@ManyToMany (mappedBy = "funcaodoc")
	private List<UnidadeDoc> unidadedoc;

	@ManyToMany (mappedBy = "funcaodoc")
	private List<Etapas> etapas;
	
	@OneToMany(mappedBy = "funcaodoc")
	private List<EpiFuncaodoc> epi_funcaodoc;
	
	@OneToMany(mappedBy = "funcaodoc")
	private List<ExamesFuncaodoc> exames_funcaodoc;
	
	@OneToMany(mappedBy = "funcaodoc")
	private List<MaquinasFuncaodoc> maquinas_funcaodoc;
	
	@OneToMany(mappedBy = "funcaodoc")
	private List<Perigo> perigo;
	
	@OneToMany(mappedBy = "funcaodoc")
	private List<Riscos> riscos;
	
	@OneToMany(mappedBy = "funcaodoc")
	private List<EPI> epi;
	
	@OneToMany(mappedBy = "funcaodoc")
	private List<CursoDeNrs> cursodenrs;
	
	@OneToMany(mappedBy = "funcaodoc")
	private List<AvaliaçõesOcupacionaisFuncoes> avaliacoesocupacionaisfuncoes;
}
