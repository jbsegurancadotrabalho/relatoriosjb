package br.com.jbst.DTO;

import java.util.UUID;

import lombok.Data;

@Data
public class PutAvaliacoesDTO {
	private UUID idAvaliacoes;
	private String nome_avaliacoes;
	private String descricao_avaliacoes;
}
