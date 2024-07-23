package br.com.jbst.entities;

import java.time.Instant;
import java.util.List;
import java.util.UUID;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.Data;

@Data
@Entity
@Table(name = "funcao")
public class Funcao {

	@Id
	@Column(name = "id_funcao")
	private UUID idFuncao;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "datahoracriacao", nullable = false)
	private Instant dataHoraCriacao;

	@Column(name = "funcao", length = 100, nullable = false)
	private String nome_da_funcao;

	@Column(name = "cbo", length = 100, nullable = false)
	private String cbo;

	@Column(name = "descricao", length = 1000, nullable = false)
	private String descricao;

	@Column(name = "gro", length = 100, nullable = true)
	private String gro;

	@Column(name = "gp", length = 100, nullable = true)
	private String gp;

	@OneToMany(mappedBy = "funcao")
	private List<FuncaoDoc> funcaodoc;

	
	@OneToMany(mappedBy = "funcao")
	private List<Funcionario> funcionario;
}