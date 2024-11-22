package br.com.jbst.DtoEvento;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import br.com.jbst.config.InstantSerializer;
import lombok.Data;

@Data
public class GetEventoDTO {
	private UUID idEvento;
    @JsonSerialize(using = InstantSerializer.class)
	private Instant dia_hora;
    private String nome_evento;  
    private String descricao;   
    private String carga_horaria;   
    private String organizadores;    
    private String palestrante;    
    private String valor;
    private String status;
    private String local;
    private List<GetParticipanteDTO> participante;
}
