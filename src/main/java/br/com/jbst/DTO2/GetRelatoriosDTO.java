package br.com.jbst.DTO2;

import java.time.Instant;
import java.util.UUID;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import br.com.jbst.config.InstantSerializer;
import lombok.Data;

@Data
public class GetRelatoriosDTO {
	private UUID idRelatorios;
	private String nome_relatorio;
	private String descricao_relatorio;
	private Integer numeroRelatorio;
    @JsonSerialize(using = InstantSerializer.class)
	private Instant datainicio;
    @JsonSerialize(using = InstantSerializer.class)
	private Instant vigencia;
}
