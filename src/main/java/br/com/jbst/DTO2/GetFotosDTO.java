package br.com.jbst.DTO2;

import java.time.Instant;
import java.util.UUID;
import lombok.Data;


@Data
public class GetFotosDTO {
	private UUID idFotos;
	private Instant dataHoraCriacao;
	private String nomeFoto;
	private byte[] foto;
	
}
