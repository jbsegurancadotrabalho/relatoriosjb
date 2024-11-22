package br.com.jbst.DTO2;

import java.time.Instant;
import java.util.UUID;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import br.com.jbst.config.InstantSerializer;
import lombok.Data;

@Data
public class GetAgendaDTO {
	private UUID idAgenda;
    @JsonSerialize(using = InstantSerializer.class)
	private Instant dia_hora;
	private String observacoes_agenda;
	private String status;
	private GetExamesCredenciadosDTO examescredenciados;
    private GetCredenciados1DTO credenciados;
}
