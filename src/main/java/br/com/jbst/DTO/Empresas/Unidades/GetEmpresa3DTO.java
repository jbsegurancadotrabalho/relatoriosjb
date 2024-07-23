package br.com.jbst.DTO.Empresas.Unidades;

import java.time.Instant;

import java.util.UUID;

import lombok.Data;

@Data
public class GetEmpresa3DTO {
	private UUID idEmpresa;
    private String razaosocial;
    private String nomefantasia;
    private String cnpj;
    private GetEmpresaDoc3DTO empresadoc;
}
