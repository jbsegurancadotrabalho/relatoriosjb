package br.com.jbst.DTO;

import java.util.UUID;

import lombok.Data;

@Data
public class PostCnaeDTO {
	private UUID idEmpresa_doc;
	private String codigo;
	private String denominacao;
	private String grau_de_risco;
}
