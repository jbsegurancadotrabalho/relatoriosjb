package br.com.jbst.DTO;

import java.util.UUID;

import lombok.Data;

@Data
public class GetExamesFuncaoDocDTO {
    private UUID id_exames_funcaodoc;
	private String nome_exames_funcaodoc;
	private String descricao_exames_funcaodoc;
}
