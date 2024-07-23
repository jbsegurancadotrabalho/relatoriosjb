package br.com.jbst.DTO2;

import java.util.UUID;

import lombok.Data;

@Data
public class GetCursos_CredenciadosDTO {
	private UUID idCursosCredenciados;
	private String nomeCursosCredenciados;
	private String statusCursosCredenciados;
	private String valorCredenciado;
	private String valorJB;
	
}
