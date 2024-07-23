package br.com.jbst.DTO2;

import java.util.UUID;

import lombok.Data;

@Data
public class GetGeradorDTO {
	private UUID idGerador;
	private String nome_gerador;
	private String caracteristicas_gerador;
}
