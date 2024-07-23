package br.com.jbst.DTO2;

import java.util.UUID;

import lombok.Data;

@Data
public class PostFormacaoSaudeDTO {
	private String formacao_saude;
	private String conselho;
	private String registro;
	private String estado;
	private UUID id_profissionalsaude;
}
