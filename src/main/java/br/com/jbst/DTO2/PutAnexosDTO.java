package br.com.jbst.DTO2;

import java.util.UUID;

import br.com.jbst.entities.Associacoes;
import lombok.Data;

@Data
public class PutAnexosDTO {
	private UUID idAnexos;
	private String nomeAnexo;
	private String descricaoAnexo;
	private UUID idAssociacoes;
}
