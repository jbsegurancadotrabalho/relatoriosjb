package br.com.jbst.DTO.Unidades.Funcionarios;

import java.time.Instant;
import java.util.UUID;

import lombok.Data;

@Data
public class GetEmpresaFuncionarioDTO {
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
