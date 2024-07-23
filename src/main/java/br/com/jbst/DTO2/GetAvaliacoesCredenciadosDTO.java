package br.com.jbst.DTO2;

import java.util.UUID;

import lombok.Data;

@Data
public class GetAvaliacoesCredenciadosDTO {
	private UUID idAvaliacoesCredenciados;
	private String nome_avaliacoes;
	private String valorCredenciado;
	private String valorJB;
	
}
