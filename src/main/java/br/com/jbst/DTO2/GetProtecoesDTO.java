package br.com.jbst.DTO2;

import java.util.UUID;

import lombok.Data;

@Data
public class GetProtecoesDTO {
	private UUID id_protecoes;
	private String tipos_protecoes;
   	private String condicoes_protecoes;
}
