package br.com.jbst.DTO.Cenarios.Documentos;

import java.util.UUID;

import lombok.Data;

@Data
public class GetEmpresaDoc2DTO {
	private UUID idEmpresa_doc;
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
