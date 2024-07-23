package br.com.jbst.DTO2;

import java.util.UUID;

import lombok.Data;

@Data
public class PostExamesCredenciadosDTO {

	private String valorCredenciado;
	private String valorJb;
	private UUID idExames;
	private UUID idCredenciado;	
}
