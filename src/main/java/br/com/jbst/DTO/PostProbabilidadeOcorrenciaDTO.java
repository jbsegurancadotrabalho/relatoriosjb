package br.com.jbst.DTO;

import java.util.UUID;

import lombok.Data;

@Data
public class PostProbabilidadeOcorrenciaDTO {
	private String categoria;
	private String denominacao;
	private String descricao;
	private UUID idPerigo;
}
