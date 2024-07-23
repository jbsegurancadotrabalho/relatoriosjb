package br.com.jbst.DTO;

import java.util.UUID;

import lombok.Data;

@Data
public class PutExamesDTO {
	private UUID idExames;
	private String nome_exame;
	private String tipo_exame; // Imagens, Laboratorio, Avaliação Clinica
	private String descricao_exame;
}
