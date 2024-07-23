package br.com.jbst.DTO;

import java.time.Instant;
import java.util.UUID;

import lombok.Data;

@Data
public class GetCursoDTO {
	private UUID idcurso;
	private Instant dataHoraCriacao;
	private String curso;
	private Integer codigo;
	private String descricao;
	private String conteudo;
	private String modelo_certificado;
	private String campo_especifico;
	
}
