package br.com.jbst.DTO.Cenarios.Documentos;

import java.util.List;
import java.util.UUID;

import br.com.jbst.DTO.GetEtapasDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetCenario3DTO {
	private UUID idCenario;
	private String nomeAtividade;
	private String descricaoAtividade;
	private String area;
	private GetSetor2DTO  gho_setor;
	private List<GetEtapasDTO> etapas;

}
