package br.com.jbst.DTO2;

import java.util.List;
import java.util.UUID;

import lombok.Data;

@Data
public class GetProfissionalSaudeDTO {

	
	private UUID id_profissionalsaude;
	private String nome_profissionalsaude;
	private String rg;
	private String cpf;
	private String telefone_1;
	private String telefone_2;
	private String email;
	private String senha_sistema;
	private List<GetFormacaoSaudeDTO> formacaosaude;
	
}
