package br.com.jbst.DTO2;

import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data	
@AllArgsConstructor
@NoArgsConstructor
public class PostAgendamentosDTO {
	private String venda;
    private String status;
	private String tipo_de_pagamento;
    private String observacoes;
	private UUID idpessoafisica;
	private UUID idFuncionario;
	private UUID idAgenda;
}
