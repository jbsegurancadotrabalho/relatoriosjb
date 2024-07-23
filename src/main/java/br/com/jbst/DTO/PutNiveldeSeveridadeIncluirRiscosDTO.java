package br.com.jbst.DTO;

import java.util.UUID;

import lombok.Data;

@Data
public class PutNiveldeSeveridadeIncluirRiscosDTO {
	private UUID idNivelSeveridade;
	private String nivel_risco_MedidasDeControle; 
	private String numero_risco_MedidasDeControle;
	private String descricao_MedidasDeControle; 
	private String nivel_permissao_MedidasDeControle; 
}
