package br.com.jbst.DTO2;

import java.util.UUID;

import lombok.Data;

@Data
public class GetAnexoDTO {
	private UUID idAnexos;
	private String nomeAnexo;
	private String descricaoAnexo;
}
