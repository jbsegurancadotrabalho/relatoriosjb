package br.com.jbst.DtoEvento;

import java.time.Instant;
import java.util.UUID;

import lombok.Data;

@Data
public class PutEventoDTO {
	private UUID idEvento;
	private Instant dia_hora;
    private String nome_evento;  
    private String descricao;   
    private String carga_horaria;   
    private String organizadores;    
    private String palestrante;    
    private String valor;
    private String status;
    private String local;
}
