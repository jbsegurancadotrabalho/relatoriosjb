package br.com.jbst.DTO;

import java.util.UUID;

import lombok.Data;

@Data
public class PutCursoDeNRsDTO {
	private UUID idCursoNrs;
    private String nomeCurso;
    private String descricaoCurso;
	
}
