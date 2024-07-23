package br.com.jbst.DTO;

import java.util.UUID;

import lombok.Data;

@Data
public class PostSistemasDeSegurancaDTO {
	private String nomeSistemaDeSeguranca;
	private String descricaoSistemaDeSeguranca;
	private UUID idMaquina;
}
