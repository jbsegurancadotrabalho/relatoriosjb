package br.com.jbst.entities;

import java.util.UUID;

import lombok.Data;

@Data
public class PostAvaliaçõesOcupacionaisFuncoesDTO {
	private UUID idFuncaoDoc;
	private String nome_avaliacoes_funcao;
	private String tipo_avaliacoes_funcao;
	private String descricao_avaliacoes_funcao;
}
