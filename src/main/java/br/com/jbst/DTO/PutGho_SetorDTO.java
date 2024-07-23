package br.com.jbst.DTO;


import java.util.UUID;


import lombok.Data;

@Data
public class PutGho_SetorDTO {
	   private UUID id_gho_setor;  
	    private String nome_gho_setor;
	    private String descricao_gho_setor;
	 
}
