package br.com.jbst.DTO;

import java.util.UUID;

import lombok.Data;

@Data
public class GetPotencialSeveridadeDanosDTO {

private UUID idPotencialSeveridade;
private String categoria_potencial_severidade; // Categoria: 1
private String tipo;//Tipo: Desprezivel
private String caracteristicas_potencial_severidade;//Caracteristicas: Não degrada o sistema nem seu funcionamento
	    
}
