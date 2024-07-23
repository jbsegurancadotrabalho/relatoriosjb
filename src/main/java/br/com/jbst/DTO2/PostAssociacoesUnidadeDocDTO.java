package br.com.jbst.DTO2;

import java.math.BigDecimal;
import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PostAssociacoesUnidadeDocDTO {
    private String venda;
    private BigDecimal valor;
    private String status;
    private String tipo_de_pagamento;
    private String observacoes;
	private UUID idunidadeDoc;
	private UUID idRelatorios;

}
