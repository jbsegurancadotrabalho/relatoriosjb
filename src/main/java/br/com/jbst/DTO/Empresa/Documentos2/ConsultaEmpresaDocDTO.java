package br.com.jbst.DTO.Empresa.Documentos2;

import java.util.List;

import java.util.UUID;

import br.com.jbst.DTO.GetCnaeDto;
import br.com.jbst.DTO.GetUnidadeDTO;
import lombok.Data;

@Data
public class ConsultaEmpresaDocDTO {
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
	private List<GetCnaeDto> cnaes;
   private ConsultaEmpresaDTO empresa;
}
