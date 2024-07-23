package br.com.jbst.DTO;

import java.util.UUID;


import lombok.Data;

@Data
public class PostGho_SetorDTO {
    private UUID idunidadeDoc;
	private String nome_gho_setor;
	private String descricao_gho_setor;
	
}
