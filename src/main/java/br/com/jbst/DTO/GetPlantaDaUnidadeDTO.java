package br.com.jbst.DTO;

import java.util.UUID;

import lombok.Data;

@Data
public class GetPlantaDaUnidadeDTO {
	private UUID idPlanta;
	private String Planta_unidade;
	private String Localização_planta_unidade;
	private String Construção;
	private String Dimensões;
	private String Ocupação;
	private String População; 
	private String HorárioFuncionamento;
	private String deficientes;
	private String RecursosHumanos;
	private String RecursosMateriais;
}
