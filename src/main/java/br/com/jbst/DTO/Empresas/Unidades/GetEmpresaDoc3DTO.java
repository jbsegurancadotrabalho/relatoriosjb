package br.com.jbst.DTO.Empresas.Unidades;

import java.util.List;
import java.util.UUID;

import br.com.jbst.DTO.GetCnaeDto;
import lombok.Data;

@Data
public class GetEmpresaDoc3DTO {
	private UUID idEmpresa_doc;
	private String razaosocial;
	private String nomefantasia;
	private String cnpj;
	private String status;
	private List<GetUnidade3DTO> unidadedoc;
}
