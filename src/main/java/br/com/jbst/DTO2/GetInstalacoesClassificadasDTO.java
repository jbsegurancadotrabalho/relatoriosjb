package br.com.jbst.DTO2;

import java.util.UUID;

import lombok.Data;

@Data
public class GetInstalacoesClassificadasDTO {
	private UUID idInstalacoesClassificadas;
	private String existeInstalacoesClassificadas;  
	private String necessidadeInstalacoesClassificadas; 
   	private String conservacaoInstalacoesClassificadas;
}
