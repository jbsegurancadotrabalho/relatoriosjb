package br.com.jbst.DTO;



import java.util.UUID;

import lombok.Data;

@Data
public class PostCursoDeNRsDTO {
    private String nomeCurso;
    private String descricaoCurso;
	private UUID idFuncaoDoc;

}
