package br.com.jbst.DTO2;

import java.util.UUID;

import lombok.Data;

@Data
public class PostEspecializacaoSaudeDTO {
	private String nomeEspecializacaoSaude;
	private String descricaoEspecializacao;
	private UUID idFormacaoSaude;
}
