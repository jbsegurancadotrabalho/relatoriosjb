package br.com.jbst.DtoAtendimento;

import java.util.UUID;

import lombok.Data;

@Data
public class PostAtendimentoDTO {
	private UUID idAgendamento;
    private String tipo;
    private String status;
    private String link;
    private String profissional;
    private String descricao;
    
}
