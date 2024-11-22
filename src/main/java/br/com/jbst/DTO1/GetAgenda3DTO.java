package br.com.jbst.DTO1;

import java.time.Instant;
import java.util.UUID;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import br.com.jbst.config.InstantSerializer;
import lombok.Data;

@Data
public class GetAgenda3DTO {
	private UUID idAgenda;
    @JsonSerialize(using = InstantSerializer.class)
	private Instant dia_hora;
	private String observacoes_agenda;
	private String status;
}
