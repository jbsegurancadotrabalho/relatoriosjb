package br.com.jbst.DTO2;

import java.util.Map;
import java.util.UUID;

import lombok.Data;

@Data
public class GetAgendamentoDTO {
	private UUID idAgendamento;
	private String venda;
    private String status;
	private String tipo_de_pagamento;
    private String observacoes;

	
}