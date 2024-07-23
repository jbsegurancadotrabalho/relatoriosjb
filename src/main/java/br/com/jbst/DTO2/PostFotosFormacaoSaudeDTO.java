package br.com.jbst.DTO2;

import java.util.UUID;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PostFotosFormacaoSaudeDTO {

	private String nomeFoto;
	private UUID idFormacaoSaude;

}
