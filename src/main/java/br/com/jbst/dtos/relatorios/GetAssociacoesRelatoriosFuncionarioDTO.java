package br.com.jbst.dtos.relatorios;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.UUID;

import br.com.jbst.DTO.GetUnidadeDTO;
import br.com.jbst.DTO2.GetRelatoriosDTO;
import lombok.Data;


@Data	
public class GetAssociacoesRelatoriosFuncionarioDTO {
	private UUID idAssociacoes;
	private Instant dataHoraCriacao;
	private String venda;
	private BigDecimal valor;
	private Integer numeroAssociacoes;
	private String status;
	private String tipo_de_pagamento;
	private String observacoes;
	private GetRelatoriosDTO relatorios;
	private GetFuncionarioRelatoriosDTO funcionario; 
}