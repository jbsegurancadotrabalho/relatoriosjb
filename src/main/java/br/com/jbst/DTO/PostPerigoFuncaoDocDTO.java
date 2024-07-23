package br.com.jbst.DTO;

import java.util.UUID;

import lombok.Data;

@Data
public class PostPerigoFuncaoDocDTO {
	private String grupo_perigo_funcaodoc;
	private String tipo_perigo_funcaodoc;
	private UUID idFuncaoDoc;
	private UUID idPerigo;
}
