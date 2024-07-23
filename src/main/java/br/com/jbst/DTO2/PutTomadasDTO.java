package br.com.jbst.DTO2;

import java.util.UUID;

import lombok.Data;

@Data
public class PutTomadasDTO {
	private UUID idTomadas;
	private String tipos_tomadas;
	private String condicoes_tomadas;
	private UUID idInspecao;
}
