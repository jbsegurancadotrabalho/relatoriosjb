package br.com.jbst.DTO.Funcionarios.Documentos;

import java.time.Instant;
import java.util.UUID;

import lombok.Data;

@Data
public class GetEmpresa1DTO {
	private UUID idEmpresa;
    private Instant dataHoraCriacao;
    private String razaosocial;
    private String nomefantasia;
    private String cnpj;
}
