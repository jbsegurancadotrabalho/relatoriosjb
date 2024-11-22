package br.com.jbst.DTO1;

import java.util.List;
import java.util.UUID;

import lombok.Data;

@Data
public class GetExames2DTO {
	private UUID idExames;
	private String nome_exame;
	private String tipo_exame; // Imagens, Laboratorio, Avaliação Clinica
	private String descricao_exame;
    private List<GetExamesCredenciados4DTO> examescredenciados;
	
}
