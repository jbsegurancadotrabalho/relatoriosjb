package br.com.jbst.DTO2;

import java.util.UUID;

import lombok.Data;

@Data
public class PutFormacaoSaudeDTO {
	private UUID idFormacaoSaude;
	private String formacao_saude;
	private String conselho;
	private String registro;
	private String estado;
}
