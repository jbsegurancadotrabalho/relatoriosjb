package br.com.jbst.DTO;

import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PostDanosDTO {
	private String nomeDano;
	private String descricaoDano;
	private UUID idPerigo;
}
