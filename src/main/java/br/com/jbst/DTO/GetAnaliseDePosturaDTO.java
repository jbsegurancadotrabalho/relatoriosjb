package br.com.jbst.DTO;

import java.util.UUID;

import lombok.Data;
@Data	
public class GetAnaliseDePosturaDTO {
	private UUID idAnalisePostura;
	private String titulo;
	private String pergunta;
	private String resposta;
}
