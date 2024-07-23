package br.com.jbst.DTO;

import java.util.UUID;

import lombok.Data;

@Data
public class PutFuncaoEspecificaDTO {

	
	private UUID idFuncaoEspecifica;
	private String setor_gho;
	private String nome_da_funcao;
	private String cenario_funcaodoc;
	private String cbo;
	private String descricao;
	private String gro;
	private String gp;
	private UUID idCenario;


}
