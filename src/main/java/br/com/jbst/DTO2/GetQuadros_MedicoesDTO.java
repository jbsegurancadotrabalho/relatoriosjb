package br.com.jbst.DTO2;

import java.util.UUID;

import lombok.Data;

@Data
public class GetQuadros_MedicoesDTO {
	private UUID idQuadros_Medicoes;
	private String tipo_tensao;
	private String nomeQuadroMedicao;
	private String descricaoQuadroMedicao;

}
