package br.com.jbst.DTO;



import lombok.Data;

@Data
public class PostExamesDTO {
	private String nome_exame;
	private String tipo_exame; // Imagens, Laboratorio, Avaliação Clinica
	private String descricao_exame;
}
