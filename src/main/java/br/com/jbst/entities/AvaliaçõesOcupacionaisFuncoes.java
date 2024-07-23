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
@Table(name = "avaliacoes_funcoes")
public class AvaliaçõesOcupacionaisFuncoes {

	@Id
	@Column(name = "idAvaliacoes")
	private UUID idAvaliacoes_funcao;
		
	@Column(name = "nome_avaliacoes", length = 500, nullable = true)
	private String nome_avaliacoes_funcao;
	
	@Column(name = "tipo_avaliacoes", length = 500, nullable = true)
	private String tipo_avaliacoes_funcao;
	
	@Column(name = "descricao_avaliacoes", length = 500, nullable = true)
	private String descricao_avaliacoes_funcao;
	
	
	
	@Column(name = "valor_obtido", length = 500, nullable = true)
	private String valor_obtido;
	
	@Column(name = "limite_de_tolerancia", length = 500, nullable = true)
	private String limite_de_tolerancia;
	
	@Column(name = "tempo_de_exposicao", length = 500, nullable = true)
	private String tempo_de_exposicao;
	
	@Column(name = "metodo", length = 500, nullable = true)
	private String metodo;
	
	@Column(name = "equipamento_de_medicao", length = 500, nullable = true)
	private String equipamento_de_medicao;
	
	@Column(name = "modelo", length = 500, nullable = true)
	private String modelo;
	
	@Column(name = "serie", length = 500, nullable = true)
	private String serie;
	
	@Column(name = "calibracao", length = 500, nullable = true)
	private String calibracao;
	
	@Column(name = "conclusao_da_exposicao", length = 500, nullable = true)
	private String conclusao_da_exposicao;
	
	@ManyToOne
	@JoinColumn(name = "id_funcao_doc" , nullable = true)
	private FuncaoDoc funcaodoc;
	
	@ManyToOne
	@JoinColumn(name = "id_funcao_especifica" , nullable = true)
	private FuncaoEspecifica funcaoespecifica;
	
}
