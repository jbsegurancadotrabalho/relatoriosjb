package br.com.jbst.DTO2;

import java.util.UUID;

import lombok.Data;

@Data
public class GetQuadros_PaineisDTO {
	private UUID idquadros_paineis;
	private String nome_quadros_paineis;
	private String descricao_quadros_paineis;
}
