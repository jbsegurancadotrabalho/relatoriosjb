package br.com.jbst.DTO2;

import java.util.UUID;

import lombok.Data;

@Data
public class PostPerguntasCondutoresDTO {
	private String pergunta;
	private UUID idCondutores;
}
