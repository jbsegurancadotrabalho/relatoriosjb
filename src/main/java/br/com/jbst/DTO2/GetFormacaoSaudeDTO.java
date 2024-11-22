package br.com.jbst.DTO2;

import java.util.List;
import java.util.UUID;

import lombok.Data;

@Data
public class GetFormacaoSaudeDTO {
	private UUID idFormacaoSaude;
	private String formacao_saude;
	private String conselho;
	private String registro;
	private String estado;
	private List<GetEspecializacaoSaudeDTO> especializacaosaude;
}
