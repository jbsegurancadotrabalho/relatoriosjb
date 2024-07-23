package br.com.jbst.DTO;

import java.util.UUID;
import lombok.Data;

@Data
public class PostCenarioDTO {
	private UUID id_gho_setor;
    private String nomeAtividade;
    private String descricaoAtividade;
    private String area;
}
