package br.com.jbst.DTO;

import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PostUnidadeDTO {
	private String nomeEspecificoUnidade;
    private String nomefantasia;
    private String cnpj;
    private String horario_empresa;
    private UUID idEmpresa_doc; 
    private UUID idEndereco;    
}
