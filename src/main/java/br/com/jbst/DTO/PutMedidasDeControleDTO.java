package br.com.jbst.DTO;

import java.util.UUID;

import lombok.Data;

@Data
public class PutMedidasDeControleDTO {
	private UUID idMedidasDeControle;
	private String valor_MedidasDeControle;	//VALOR - 15
	private String tipo_MedidasDeControle; // Eliminação, Substituição, Controles de Engenharia,
	private String descricao_MedidasDeControle; //Eliminação da Fonte de Risco do Local:

}
