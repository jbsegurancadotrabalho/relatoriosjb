package br.com.jbst.DTO;

import java.util.UUID;

import lombok.Data;

@Data
public class GetEpiDTO {
	private UUID idEpi;
	private String nome_epi;
	private String descricao_epi;
    private String ca;
}
