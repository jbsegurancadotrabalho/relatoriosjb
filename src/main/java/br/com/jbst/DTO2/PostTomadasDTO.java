package br.com.jbst.DTO2;

import java.util.UUID;

import lombok.Data;

@Data
public class PostTomadasDTO {
	private String tipos_tomadas;
	private String condicoes_tomadas;
	private UUID idInspecao;
}
