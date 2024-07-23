package br.com.jbst.DTO2;

import java.util.UUID;

import lombok.Data;

@Data
public class PutProcedimentosDTO {
	private UUID idProcedimentos;
	private String nome_procedimentos;
   	private String descricao_procedimentos;
	private UUID idInspecao;
}
