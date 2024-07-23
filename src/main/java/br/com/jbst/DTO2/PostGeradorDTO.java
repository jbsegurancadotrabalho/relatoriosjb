package br.com.jbst.DTO2;

import java.util.UUID;

import lombok.Data;

@Data
public class PostGeradorDTO {
	private String nome_gerador;
	private String caracteristicas_gerador;
    private UUID idInspecao;

}
