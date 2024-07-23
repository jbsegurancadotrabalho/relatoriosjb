package br.com.jbst.DTO;

import java.time.Instant;
import java.util.UUID;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import br.com.jbst.config.InstantSerializer;
import lombok.Data;

@Data
public class GetDocFreeFuncionarioDTO {
	private UUID idDocFreeFuncionario;
	private String nome;
	private String cpf;
    @JsonSerialize(using = InstantSerializer.class)
	private Instant datainicio;
    @JsonSerialize(using = InstantSerializer.class)
	private Instant vigencia;
	private String responsavel;
	private String endereco;
    private Integer numerodocumentofree; // Make sure this attribute exists

}
