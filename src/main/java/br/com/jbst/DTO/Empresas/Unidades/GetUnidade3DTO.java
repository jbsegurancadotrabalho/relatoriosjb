package br.com.jbst.DTO.Empresas.Unidades;

import java.util.List;
import java.util.UUID;

import br.com.jbst.DTO.Cenarios.Documentos.GetAssociacaoCenario1DTO;
import lombok.Data;

@Data
public class GetUnidade3DTO {
	private UUID idunidadeDoc;
	private String nomeEspecificoUnidade;
	private String nomefantasia;
	private String cnpj;
	private String horario_empresa;
	private List <GetAssociacaoCenario1DTO> associacoes;
}
