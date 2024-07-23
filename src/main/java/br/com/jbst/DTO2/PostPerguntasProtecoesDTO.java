package br.com.jbst.DTO2;

import java.util.UUID;

import lombok.Data;

@Data
public class PostPerguntasProtecoesDTO {
	private String pergunta;
	private UUID id_protecoes;
}
