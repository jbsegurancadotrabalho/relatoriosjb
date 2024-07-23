package br.com.jbst.DTO2;

import java.util.UUID;


import lombok.Data;

@Data
public class GetEspecializacaoSaudeDTO {
	private UUID idEspecializacaoSaude;
	private String nomeEspecializacaoSaude;
	private String descricaoEspecializacao;
}
