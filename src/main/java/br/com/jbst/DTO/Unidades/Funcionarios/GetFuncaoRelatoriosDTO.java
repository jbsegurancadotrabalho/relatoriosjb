package br.com.jbst.DTO.Unidades.Funcionarios;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

import lombok.Data;

@Data
public class GetFuncaoRelatoriosDTO {
	private UUID idFuncao;
	private Instant dataHoraCriacao;
	private String nome_da_funcao;
	private String cbo;	
	private String descricao;
	private String gro;
	private String gp;
	private List <GetFuncaoDocRelatoriosDTO> funcaodoc;
}
