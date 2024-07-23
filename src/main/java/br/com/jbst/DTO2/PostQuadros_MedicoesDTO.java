package br.com.jbst.DTO2;

import java.util.UUID;

import lombok.Data;

@Data
public class PostQuadros_MedicoesDTO {
	private String tipo_tensao;
	private String nomeQuadroMedicao;
	private String descricaoQuadroMedicao;
	private UUID idInspecao;

}
