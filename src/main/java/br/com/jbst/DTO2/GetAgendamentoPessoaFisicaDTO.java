package br.com.jbst.DTO2;

import java.util.UUID;

import br.com.jbst.DTO1.GetAgenda1DTO;
import lombok.Data;

@Data
public class GetAgendamentoPessoaFisicaDTO {
	private UUID idAgendamento;
	private String venda;
    private String status;
	private String tipo_de_pagamento;
    private String observacoes;
    private GetAgenda1DTO agenda;
    private GetPessoaFisicaDTO pessoafisica;
}
