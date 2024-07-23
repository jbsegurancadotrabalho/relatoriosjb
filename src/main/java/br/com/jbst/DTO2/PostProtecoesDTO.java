package br.com.jbst.DTO2;

import java.util.UUID;

import lombok.Data;

@Data
public class PostProtecoesDTO {
	private String tipos_protecoes;
   	private String condicoes_protecoes;
	private UUID idInspecao;
}
