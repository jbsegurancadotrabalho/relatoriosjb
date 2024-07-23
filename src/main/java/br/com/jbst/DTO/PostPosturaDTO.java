package br.com.jbst.DTO;

import java.util.UUID;

import lombok.Data;

@Data
public class PostPosturaDTO {
	private UUID id_gho_setor;
	private String nomePostura;
	private String descricaoPostura;
}
