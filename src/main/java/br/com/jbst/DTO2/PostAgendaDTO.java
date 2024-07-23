package br.com.jbst.DTO2;

import java.time.Instant;

import java.util.UUID;

import lombok.Data;

@Data
public class PostAgendaDTO {
	private Instant dia_hora;
	private String observacoes_agenda;
	private UUID idExameCredenciado;
	private UUID id_profissionalsaude;
	private UUID idCredenciado;
}
