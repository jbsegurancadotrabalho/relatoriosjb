package br.com.jbst.DTO.Cenarios.Documentos;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

import br.com.jbst.DTO.GetCenarioDTO;
import br.com.jbst.DTO.GetPosturaDTO;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.Data;

@Data
public class GetSetor2DTO {
	  private UUID id_gho_setor;  
	    @Temporal(TemporalType.TIMESTAMP)
	    private Instant dataHoraCriacao;
	    private String nome_gho_setor;
	    private String descricao_gho_setor;
		private GetUnidade2DTO unidadedoc;
}
