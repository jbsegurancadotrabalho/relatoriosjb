package br.com.jbst.DTO;

import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class PostEtapasIncluirCenarioDTO {
    private UUID idCenario;
    private String numero_etapa;
    private String nome_etapa;
    private String descricao_etapa;
    private String acoes;
	
}
