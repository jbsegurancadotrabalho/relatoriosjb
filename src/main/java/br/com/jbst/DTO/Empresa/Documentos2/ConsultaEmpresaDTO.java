package br.com.jbst.DTO.Empresa.Documentos2;

import java.util.UUID;

import lombok.Data;

@Data
public class ConsultaEmpresaDTO {
	private UUID idEmpresa;
    private String razaosocial;
    private String nomefantasia;
    private String cnpj;
    private String status;
    private String inscricaoestadual;
    private String inscricaomunicipal;
	private String responsavel_sistema;
    private String email_usuario;
    private String telefone_responsavel;

}
