package br.com.jbst.dtos.relatorios;

import java.time.Instant;
import java.util.UUID;
import lombok.Data;

@Data
public class GetFuncionarioRelatoriosDTO {
	  private UUID idFuncionario;
	    private Instant dataHoraCriacao;
	    private String nome;
	    private String cpf;
	    private String rg;
	    private String status;
	    private String matricula;
	   private GetAssociacoesRelatoriosFuncionarioDTO associacoes;
		private GetFuncaoRelatoriosDTO funcao;
}
