package br.com.jbst.DTO2;

import java.time.Instant;
import java.util.UUID;

import lombok.Data;

@Data
public class GetAgendaDTO {
	private UUID idAgenda;
	private Instant dia_hora;
	private String observacoes_agenda;

}
