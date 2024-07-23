package br.com.jbst.DTO;

import java.util.UUID;
import lombok.Data;

@Data
public class PutProbabilidadeOcorrenciaIncluirRiscosDTO {
	private UUID idprobabilidadeOcorrencia;
	private String categoria;// 1, 2, 3, 4, 5
	private String denominacao;
	private String descricao;
}
