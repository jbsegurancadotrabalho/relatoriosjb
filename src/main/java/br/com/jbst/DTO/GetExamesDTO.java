package br.com.jbst.DTO;

import java.util.UUID;

import br.com.jbst.DTO2.GetCredenciadosDTO;
import lombok.Data;

@Data
public class GetExamesDTO {
	private UUID idExames;
	private String nome_exame;
	private String tipo_exame; // Imagens, Laboratorio, Avaliação Clinica
	private String descricao_exame;

}
