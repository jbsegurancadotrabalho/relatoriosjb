package br.com.jbst.DTO2;

import java.util.UUID;

import br.com.jbst.entities.LaudoEletrico.Tomadas;
import lombok.Data;

@Data
public class PutPerguntasDTO {
	private UUID idPerguntas;
	private String pergunta;
}
