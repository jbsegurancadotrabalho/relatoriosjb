package br.com.jbst.DTO2;

import java.util.UUID;

import lombok.Data;

@Data
public class PostAterramentoDTO {
	private String tipos_aterramento; 
	private String condicoesAterramento;
 	private UUID idInspecao;

}
