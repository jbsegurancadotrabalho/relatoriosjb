package br.com.jbst.DTO2;

import java.util.UUID;

import lombok.Data;

@Data
public class PostLuminariasDTO {
	private String tiposLuminarias;
	private String condicoesLuminarias;
	private UUID idInspecao;
}
