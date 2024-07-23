package br.com.jbst.DTO.Funcionarios.Documentos;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import br.com.jbst.DTO.Unidades.Funcionarios.GetFuncionarioRelatorioDTO;
import br.com.jbst.config.InstantSerializer;
import lombok.Data;

@Data
public class GetDocFreeFuncionarioDTO1 {
	private UUID idDocFreeFuncionario;
	private String nome;
	private String cpf;
    @JsonSerialize(using = InstantSerializer.class)
	private Instant datainicio;
    @JsonSerialize(using = InstantSerializer.class)
	private Instant vigencia;
	private String responsavel;
	private String endereco;
    private Integer numerodocumentofree;
    private GetFuncionarioDTO1 funcionario;
    

}
