package br.com.jbst.DTO;

import java.util.UUID;

import lombok.Data;

@Data
public class GetUnidade3DTO {
	private UUID idunidadeDoc;
	private String nomeEspecificoUnidade;
	private String nomefantasia;
	private String cnpj;
	private String horario_empresa;
}
