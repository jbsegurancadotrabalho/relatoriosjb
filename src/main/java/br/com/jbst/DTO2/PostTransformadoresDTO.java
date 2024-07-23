package br.com.jbst.DTO2;

import java.util.UUID;

import lombok.Data;

@Data
public class PostTransformadoresDTO {
	private String nomeTransformador;
	private String descricaoTransformador;
	private UUID idInspecao;
}
