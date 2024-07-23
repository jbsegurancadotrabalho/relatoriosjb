package br.com.jbst.DTO2;

import java.util.UUID;

import lombok.Data;

@Data
public class GetSubstacoesDTO {
	private UUID idSubstacoes;
	private String nome_substacoes;
   	private String descricao_substacoes;
}
