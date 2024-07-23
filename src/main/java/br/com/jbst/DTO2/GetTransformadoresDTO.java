package br.com.jbst.DTO2;

import java.util.UUID;

import lombok.Data;

@Data
public class GetTransformadoresDTO {
	private UUID idTransformadores;
	private String nomeTransformador;
	private String descricaoTransformador;
}
