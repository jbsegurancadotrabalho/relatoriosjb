package br.com.jbst.DTO;

import java.time.Instant;
import java.util.UUID;

import br.com.jbst.DTO.Empresa.Documentos.ConsultaUnidadeComAssociaçãoDTO;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.Data;

@Data
public class GetSetor1DTO {
	  private UUID id_gho_setor;  
	    @Temporal(TemporalType.TIMESTAMP)
	    private Instant dataHoraCriacao;
	    private String nome_gho_setor;
	    private String descricao_gho_setor;
	    private ConsultaUnidadeComAssociaçãoDTO unidadedoc;
}
