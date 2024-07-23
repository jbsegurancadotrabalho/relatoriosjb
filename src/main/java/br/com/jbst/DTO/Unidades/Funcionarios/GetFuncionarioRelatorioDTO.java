package br.com.jbst.DTO.Unidades.Funcionarios;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import br.com.jbst.DTO.GetDocFreeFuncionarioDTO;
import br.com.jbst.DTO2.GetAssociacoesUnidadeDocDTO;
import br.com.jbst.config.InstantSerializer;
import lombok.Data;

@Data
public class GetFuncionarioRelatorioDTO {
	  private UUID idFuncionario;
	    @JsonSerialize(using = InstantSerializer.class)
	    private Instant dataHoraCriacao;
	    private String nome;
	    private String cpf;
	    private String rg;
	    private String status;
	    private String matricula;
	    private GetEmpresaFuncionarioDTO empresa;
	    private GetFuncaoRelatoriosDTO funcao;
	    private List<GetAssociacoesUnidadeDocDTO> associacoes;
	    private List< GetDocFreeFuncionarioDTO> docfreefuncionario;
	    
}
