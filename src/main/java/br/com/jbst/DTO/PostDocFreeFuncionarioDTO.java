package br.com.jbst.DTO;

import java.time.Instant;
import java.util.UUID;

import lombok.Data;

@Data
public class PostDocFreeFuncionarioDTO {
	private String nome;
	private String cpf;
	private Instant datainicio;
	private Instant vigencia;
	private String responsavel;
	private String endereco;
    private UUID idFuncionario;
}
