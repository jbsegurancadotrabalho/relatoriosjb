package br.com.jbst.DTO2;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.UUID;

import br.com.jbst.DTO.Cenarios.Documentos.GetCenario3DTO;
import lombok.Data;

@Data
public class GetAssociacoesCenarioDTO {
	private UUID idAssociacoes;
	private Instant dataHoraCriacao;
	private String venda;
	private BigDecimal valor;
	private Integer numeroAssociacoes;
	private String status;
	private String tipo_de_pagamento;
	private String observacoes;
	public void setRelatorios(GetRelatoriosDTO convertToGetRelatoriosDTO) {
		// TODO Auto-generated method stub
		
	}
	public void setCenario(GetCenario3DTO convertToGetCenario3DTO) {
		// TODO Auto-generated method stub
		
	}
}
