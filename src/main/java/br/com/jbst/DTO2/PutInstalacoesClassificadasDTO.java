package br.com.jbst.DTO2;

import java.util.UUID;

import lombok.Data;

@Data
public class PutInstalacoesClassificadasDTO {
	private UUID idInstalacoesClassificadas;
	private String existeInstalacoesClassificadas;  
	private String necessidadeInstalacoesClassificadas; 
   	private String conservacaoInstalacoesClassificadas;
  	private UUID idInspecao;
}
