package br.com.jbst.DTO.Cenarios.Documentos;

import java.util.List;
import java.util.UUID;

import br.com.jbst.DTO.GetEtapasDTO;
import lombok.Data;

@Data
public class GetCenario4DTO {
	private UUID idCenario;
	private String nomeAtividade;
	private String descricaoAtividade;
	private String area;
	private List <GetAssociacaoCenario1DTO> associacoes;
	private List<GetEtapasDTO> etapas;

}
