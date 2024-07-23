package br.com.jbst.DTO2;

import java.time.Instant;

import lombok.Data;

@Data
public class PostRelatoriosDTO {
	private String nome_relatorio;
	private String descricao_relatorio;
	private Instant datainicio;
	private Instant vigencia;
}
