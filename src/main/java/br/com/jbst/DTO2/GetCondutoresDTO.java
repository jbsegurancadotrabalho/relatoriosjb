package br.com.jbst.DTO2;

import java.util.UUID;

import lombok.Data;

@Data
public class GetCondutoresDTO {

	private UUID idCondutores;
	private String tipos_condutores;
   	private String condicoes_condutores;
}
