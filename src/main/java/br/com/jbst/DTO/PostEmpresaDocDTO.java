package br.com.jbst.DTO;

import java.util.UUID;

import lombok.Data;

@Data
public class PostEmpresaDocDTO {

	private UUID idEmpresa;
	private String razaosocial;
	private String nomefantasia;
	private String cnpj;
	private String status;
	private String inscricaoestadual;
	private String inscricaomunicipal;
	private String responsavel_sistema;
	private String email_usuario;
	private String senha_sistema;
	private String telefone_responsavel;
}
