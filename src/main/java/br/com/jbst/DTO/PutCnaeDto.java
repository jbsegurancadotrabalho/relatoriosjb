package br.com.jbst.DTO;

import java.util.UUID;

import lombok.Data;

@Data
public class PutCnaeDto {
	private UUID idCnae;
	private String codigo;
	private String denominacao;
	private String grau_de_risco;
}
