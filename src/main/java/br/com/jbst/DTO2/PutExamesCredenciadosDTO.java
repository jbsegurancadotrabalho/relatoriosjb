package br.com.jbst.DTO2;

import java.util.UUID;

import lombok.Data;

@Data
public class PutExamesCredenciadosDTO {

	private UUID idExameCredenciado;
	private String valorCredenciado;
	private String valorJb;
	private UUID idExames;
	private UUID idCredenciado;	
}
