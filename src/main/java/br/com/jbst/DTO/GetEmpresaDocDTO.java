package br.com.jbst.DTO;

import java.util.List;
import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetEmpresaDocDTO {

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
    private List <GetCnaeDto> cnaes;
    private List<GetUnidade3DTO> unidadedoc;

}
