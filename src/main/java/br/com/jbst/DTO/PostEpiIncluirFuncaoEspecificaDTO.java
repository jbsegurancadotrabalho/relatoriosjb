package br.com.jbst.DTO;

import java.util.UUID;

import lombok.Data;

@Data
public class PostEpiIncluirFuncaoEspecificaDTO {
	private String nome_epi;
	private String descricao_epi;
    private String ca;
	private UUID idFuncaoEspecifica;
}
