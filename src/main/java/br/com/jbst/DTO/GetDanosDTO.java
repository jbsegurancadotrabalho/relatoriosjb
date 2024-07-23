package br.com.jbst.DTO;

import java.util.UUID;

import lombok.Data;

@Data	
public class GetDanosDTO {
	private UUID idDanos;
    private String nomeDano;
    private String descricaoDano;
    
}
