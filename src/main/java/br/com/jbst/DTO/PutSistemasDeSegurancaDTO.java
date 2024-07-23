package br.com.jbst.DTO;

import java.util.UUID;

import lombok.Data;

@Data
public class PutSistemasDeSegurancaDTO {
	 private UUID idsistemas_de_seguranca;
	   private String nomeSistemaDeSeguranca;
	   private String descricaoSistemaDeSeguranca;
		private UUID idMaquina;
}
