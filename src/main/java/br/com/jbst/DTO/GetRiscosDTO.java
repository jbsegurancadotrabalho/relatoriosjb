package br.com.jbst.DTO;

import java.util.List;
import java.util.UUID;

import br.com.jbst.entities.Danos;
import br.com.jbst.entities.MedidasDeControle;
import br.com.jbst.entities.Nivel_Severidade;
import br.com.jbst.entities.Potencial_Severidade_Danos;
import br.com.jbst.entities.ProbabilidadeOcorrencia;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class GetRiscosDTO {
	private UUID idRisco;
	private String grupo;// Quimico, Fisico, Biológico
	private String tipo;// Fisico: Ruido / Quimico: Poeiras
	private String fonte_geradora; // AR - Máquinas
	private String meios_de_propagacao; // ar
	private String cor;
	private String meios_de_controles;
	private List<GetDanosDTO> danos; 
	private GetPotencialSeveridadeDanosDTO potencial_severidade_danos;
	private List<GetMedidasDeControleDTO> medidasdecontrole;
	private GetProbabilidadeOcorrenciaDTO probabilidadeocorrencia;
	private GetNivelSeveridadeDTO nivel_severidade;
}
