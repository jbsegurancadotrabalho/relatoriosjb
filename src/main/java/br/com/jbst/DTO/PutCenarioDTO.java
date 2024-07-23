package br.com.jbst.DTO;

import java.util.UUID;

import lombok.Data;

@Data
public class PutCenarioDTO {
	private UUID idCenario;
    private String nomeAtividade;
    private String descricaoAtividade;
    private String area;
}
