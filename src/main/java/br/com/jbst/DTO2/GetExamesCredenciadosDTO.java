package br.com.jbst.DTO2;

import java.util.UUID;

import lombok.Data;

@Data
public class GetExamesCredenciadosDTO {

	private UUID idExameCredenciado;
	private String valorCredenciado;
	private String valorJb;
}
