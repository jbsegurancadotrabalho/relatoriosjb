package br.com.jbst.DTO2;

import java.util.UUID;

import lombok.Data;

@Data
public class PostProfissionalSaudeDTO {
	private String nome_profissionalsaude;
	private String rg;
	private String cpf;
	private String telefone_1;
	private String telefone_2;
	private String email;
	private String senha_sistema;
	private UUID idCredenciado;	
    private UUID idEndereco;
}
