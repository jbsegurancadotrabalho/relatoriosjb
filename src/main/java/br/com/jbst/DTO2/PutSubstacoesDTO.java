package br.com.jbst.DTO2;

import java.util.UUID;

import lombok.Data;

@Data
public class PutSubstacoesDTO {
	private UUID idSubstacoes;
	private String nome_substacoes;
   	private String descricao_substacoes;
    private UUID idInspecao;
}
