package br.com.jbst.DTO2;

import java.util.List;
import java.util.UUID;

import br.com.jbst.DTO.GetEnderecoDTO;
import br.com.jbst.enuns.Credenciados.Status;
import lombok.Data;

@Data
public class GetCredenciados2DTO {

	private UUID idCredenciado;	
	private String razaosocial;
	private String nomeCredenciado;
	private String cnpj;
	private Status status;
	private String inscricaomunicipal;
	private String responsavel_sistema;
	private String email_usuario;
	private String senha_sistema;
	private String telefone_responsavel;
	private GetEnderecoDTO endereco;
}