package br.com.jbst.DtoAtendimento;

import java.time.Instant;
import java.util.UUID;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import br.com.jbst.config.InstantSerializer;
import lombok.Data;


@Data
public class GetAnamneseDTO1 {
	private UUID idAnamnese;
    @JsonSerialize(using = InstantSerializer.class)
	private Instant dia_hora;
	private String empresa;
	private String nome;
	private String telefone;
	private String cpf;
	private String rg;
	private String funcao;
	private String tipo_aso;
}
