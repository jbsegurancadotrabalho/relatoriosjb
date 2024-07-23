package br.com.jbst.entities;
import java.time.Instant;
import java.util.List;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
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
@Table(name = "funcao_especifica")
public class FuncaoEspecifica {
	@Id
	@Column(name = "id_funcao_especifica")
	private UUID idFuncaoEspecifica;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "datahoracriacao", nullable = false)
	private Instant dataHoraCriacao;

	@Column(name = "setor_gho", length = 100, nullable = false)
	private String setor_gho;
	
	@Column(name = "nome_da_funcao", length = 100, nullable = false)
	private String nome_da_funcao;
	
	@Column(name = "cenario_funcaodoc", length = 100, nullable = false)
	private String cenario_funcaodoc;
	
	@Column(name = "cbo", length = 100, nullable = false)
	private String cbo;
	
	@Column(name = "descricao", length = 1000, nullable = false)
	private String descricao;
	
	@Column(name = "gro", length = 100, nullable = true)
	private String gro;
	
	@Column(name = "gp", length = 100, nullable = true)
	private String gp;

	@ManyToOne
	@JoinColumn(name = "id_cenario", nullable = true)
	private Cenario cenario;
	
	@OneToMany(mappedBy = "funcaoespecifica")
	private List<PerigoEntity > perigos;
	
	@OneToMany(mappedBy = "funcaoespecifica")
	private List<RiscosEntity> riscos;
	
	@OneToMany(mappedBy = "funcaoespecifica")
	private List<EPI> epi;
	
	@OneToMany(mappedBy = "funcaoespecifica")
	private List<CursoDeNrs> cursodenrs;
	
	@OneToMany(mappedBy = "funcaoespecifica")
	private List<ExamesFuncaodoc> examesfuncaodoc;
	

	@OneToMany(mappedBy = "funcaoespecifica")
	private List<AvaliaçõesOcupacionaisFuncoes> avaliaçõesocupacionaisfuncoes;
}
