package br.com.jbst.DTO;

import java.util.List;
import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetPerigoDTO {
	private UUID idPerigo;
	private String nomePerigo;
	private String causasPerigo;
	private String consequencias_perigo;
	private String medidasPreventivas;
	private String classificacao;
	private List<GetDanosDTO> danos; 
	private GetPotencialSeveridadeDanosDTO potencial_severidade_danos;
	private List<GetMedidasDeControleDTO> medidasdecontrole;
	private GetProbabilidadeOcorrenciaDTO probabilidadeocorrencia;
	private GetNivelSeveridadeDTO nivel_severidade;
}
