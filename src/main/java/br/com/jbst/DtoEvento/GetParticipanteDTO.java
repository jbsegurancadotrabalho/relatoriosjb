package br.com.jbst.DtoEvento;

import java.util.UUID;

import lombok.Data;

@Data
public class GetParticipanteDTO {
	private UUID idParticipante;
	private String empresa;
	private String cnpj;
	private String nome;
	private String email;
	private String whatsapp;
    private String funcao;
	private String segmento;
}
