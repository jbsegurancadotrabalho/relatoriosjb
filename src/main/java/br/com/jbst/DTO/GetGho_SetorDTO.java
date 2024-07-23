package br.com.jbst.DTO;
import java.time.Instant;

import java.util.List;
import java.util.UUID;

import br.com.jbst.entities.Cenario;

import br.com.jbst.entities.Postura;

import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetGho_SetorDTO {   
    private UUID id_gho_setor;  
    @Temporal(TemporalType.TIMESTAMP)
    private Instant dataHoraCriacao;
    private String nome_gho_setor;
    private String descricao_gho_setor;
	private List<GetCenarioDTO> cenario;	
	private List<GetPosturaDTO> postura;
}

