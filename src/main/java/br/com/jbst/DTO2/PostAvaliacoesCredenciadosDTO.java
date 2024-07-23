package br.com.jbst.DTO2;

import java.util.UUID;

import lombok.Data;

@Data
public class PostAvaliacoesCredenciadosDTO {
	private String nome_avaliacoes;
	private String valorCredenciado;
	private String valorJB;
	private UUID idAvaliacoes;
	private UUID idCredenciado;	

}
