package br.com.jbst.DTO.Empresa.Documentos;

import java.util.List;
import java.util.UUID;

import br.com.jbst.DTO2.GetAssociacoesUnidadeDocDTO;
import lombok.Data;

@Data
public class ConsultaUnidadeComAssociaçãoDTO {
	private UUID idunidadeDoc;
	private String nomeEspecificoUnidade;
	private String nomefantasia;
	private String cnpj;
	private String horario_empresa;
	private List<ConsultaAssociacoesDTO> associacoes;

}
