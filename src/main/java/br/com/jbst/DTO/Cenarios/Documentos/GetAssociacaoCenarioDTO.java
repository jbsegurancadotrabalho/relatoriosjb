package br.com.jbst.DTO.Cenarios.Documentos;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.UUID;

import br.com.jbst.DTO.GetCenarioDTO;
import br.com.jbst.DTO2.GetRelatoriosDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class GetAssociacaoCenarioDTO {
	 private UUID idAssociacoes;
	    private Instant dataHoraCriacao;
	    private String venda;
	    private BigDecimal valor;
	    private Integer numeroAssociacoes;
	    private String status;
	    private String tipo_de_pagamento;
	    private String observacoes;
		private GetCenario3DTO cenario;
	    private GetRelatoriosDTO relatorios;
}
