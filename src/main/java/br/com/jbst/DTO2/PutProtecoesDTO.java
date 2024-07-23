package br.com.jbst.DTO2;

import java.util.UUID;

import lombok.Data;

@Data
public class PutProtecoesDTO {
	private UUID id_protecoes;
	private String tipos_protecoes;
   	private String condicoes_protecoes;
	private UUID idInspecao;
}
