package br.com.jbst.DTO2;

import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PutQuadros_MedicoesDTO {
	private UUID idQuadros_Medicoes;
	private String tipo_tensao;
	private String nomeQuadroMedicao;
	private String descricaoQuadroMedicao;
	private UUID idInspecao;

}
