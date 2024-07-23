package br.com.jbst.DTO;

import java.util.UUID;

import lombok.Data;

@Data
public class PostExamesFuncaoDocDTO {
    private UUID idFuncaoDoc;
	private String nome_exames_funcaodoc;
	private String descricao_exames_funcaodoc;
}
