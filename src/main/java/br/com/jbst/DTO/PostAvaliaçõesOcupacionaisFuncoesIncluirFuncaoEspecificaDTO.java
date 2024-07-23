package br.com.jbst.DTO;

import java.util.UUID;

import lombok.Data;

@Data
public class PostAvaliaçõesOcupacionaisFuncoesIncluirFuncaoEspecificaDTO {
	private UUID idFuncaoEspecifica;
	private String nome_avaliacoes_funcao;
	private String tipo_avaliacoes_funcao;
	private String descricao_avaliacoes_funcao;
}
