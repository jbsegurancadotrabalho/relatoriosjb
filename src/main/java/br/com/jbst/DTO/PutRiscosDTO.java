package br.com.jbst.DTO;

import java.util.UUID;

import lombok.Data;

@Data
public class PutRiscosDTO {
	private UUID idRisco;
	private String grupo;//Quimico, Fisico, Biológico
	private String tipo;//Fisico: Ruido / Quimico: Poeiras
	private String fonte_geradora; // AR - Máquinas
	private String meios_de_propagacao; // ar
	private String cor;
	private String meios_de_controles;
	private String controles_existentes;
	private String controles_inexistentes;
}