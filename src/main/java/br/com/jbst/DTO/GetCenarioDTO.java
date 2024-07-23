package br.com.jbst.DTO;

import java.util.List;
import java.util.UUID;

import br.com.jbst.DTO.Cenarios.Documentos.GetAssociacaoCenarioDTO;
import br.com.jbst.DTO2.GetAssociacoesCenarioDTO;
import lombok.Data;

@Data
public class GetCenarioDTO {
	private UUID idCenario;
	private String nomeAtividade;
	private String descricaoAtividade;
	private String area;
	private List<GetEtapasDTO> etapas;
	private List<GetFuncaoDocDTO> funcaodoc;
	private List<GetFuncaoEspecificaDTO> funcaoespecifica;
}
