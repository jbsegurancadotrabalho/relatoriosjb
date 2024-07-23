package br.com.jbst.DTO.Empresa.Documentos2;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.UUID;

import br.com.jbst.DTO2.GetRelatoriosDTO;
import lombok.Data;

@Data
public class ConsultarAssociacoeComUnidadesDTO {
	private UUID idAssociacoes;
	private Instant dataHoraCriacao;
	private String venda;
	private BigDecimal valor;
	private Integer numeroAssociacoes;
	private String status;
	private String tipo_de_pagamento;
	private String observacoes;
	private GetRelatoriosDTO relatorios;
	private ConsultarUnidadeDeAssociaoDTO unidadedoc;
}
