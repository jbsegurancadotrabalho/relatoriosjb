package br.com.jbst.DTO;

import java.util.UUID;

import lombok.Data;

@Data
public class PostAnaliseDePosturaDTO {
	 private UUID idPostura;
		private String titulo;
	    private String pergunta;
	    private String resposta;
}
