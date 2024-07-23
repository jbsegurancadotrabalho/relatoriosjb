package br.com.jbst.DTO;

import java.util.UUID;

import lombok.Data;

@Data
public class GetCenarioUnidadeDTO {
	private UUID idCenario;
	private String nomeAtividade;
	private String descricaoAtividade;
	private String area;
	GetSetor1DTO gho_setor;
}
