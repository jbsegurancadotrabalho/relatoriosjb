package br.com.jbst.DTO;

import java.util.UUID;

import lombok.Data;

@Data
public class GetSistemasDeSegurancaDTO {
	private UUID idsistemas_de_seguranca;
	private String nomeSistemaDeSeguranca;
	private String descricaoSistemaDeSeguranca;
}
