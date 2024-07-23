package br.com.jbst.DTO.Funcionarios.Documentos;

import java.time.Instant;
import java.util.UUID;

import br.com.jbst.DTO.GetEmpresaDTO;
import br.com.jbst.DTO.GetFuncaoDTO;
import lombok.Data;

@Data
public class GetFuncionarioDTO1 {
	private UUID idFuncionario;
	private Instant dataHoraCriacao;
	private String nome;
	private String cpf;
	private String rg;
	private String status;
	private GetFuncaoDTO funcao;
	private GetEmpresa1DTO empresa;
}
