package br.com.jbst.DTO.Cenarios.Documentos;

import java.math.BigDecimal;
import java.util.UUID;

import lombok.Data;

@Data
public class PostAssociacoesCenarioDTO {
	private String venda;
	private BigDecimal valor;
	private String status;
	private String tipo_de_pagamento;
	private String observacoes;
	private UUID idCenario;
	private UUID idRelatorios;
}
