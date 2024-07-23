package br.com.jbst.DTO2;

import java.util.UUID;

import lombok.Data;

@Data
public class PostCursos_CredenciadosDTO {
	private String nomeCursosCredenciados;
	private String statusCursosCredenciados;
	private String valorCredenciado;
	private String valorJB;
	private UUID idcurso;
	private UUID idCredenciado;	
}
