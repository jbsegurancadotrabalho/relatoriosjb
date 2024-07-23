package br.com.jbst.DTO;

import java.util.UUID;

import lombok.Data;

@Data
public class GetAvaliaçõesOcupacionaisFuncoesDTO {
	private UUID idAvaliacoes_funcao;
	private String nome_avaliacoes_funcao;
	private String tipo_avaliacoes_funcao;
	private String descricao_avaliacoes_funcao;
	private String valor_obtido;
	private String limite_de_tolerancia;
	private String tempo_de_exposicao;
	private String metodo;
	private String conclusao_da_exposicao;
	private String equipamento_de_medicao;
	private String modelo;
	private String serie;
	private String calibracao;
}
