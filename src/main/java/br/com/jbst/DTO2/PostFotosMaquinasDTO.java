package br.com.jbst.DTO2;

import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Data;


@AllArgsConstructor
@Data
public class PostFotosMaquinasDTO {
	private String nomeFoto;
	private UUID idMaquina;

}
