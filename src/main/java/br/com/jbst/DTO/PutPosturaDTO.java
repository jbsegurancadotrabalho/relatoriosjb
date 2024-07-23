package br.com.jbst.DTO;

import java.util.UUID;

import lombok.Data;

@Data
public class PutPosturaDTO {
	private UUID idPostura;
	private UUID idGhoSetor;
	private String nomePostura;
	private String descricaoPostura;
}
