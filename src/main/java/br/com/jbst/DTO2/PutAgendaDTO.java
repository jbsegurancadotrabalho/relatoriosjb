package br.com.jbst.DTO2;

import java.time.Instant;
import java.util.UUID;

import lombok.Data;

@Data
public class PutAgendaDTO {
	private UUID idAgenda;
	private Instant dia_hora;
	private String status;
	private String observacoes_agenda;

}
