package br.com.jbst.DTO.Empresa.Documentos1;

import java.util.List;
import java.util.UUID;

import lombok.Data;

@Data
public class ConsultarAssociacoesUnidadesDTO {
	private UUID idunidadeDoc;
	private String nomeEspecificoUnidade;
	private String nomefantasia;
	private String cnpj;
	private String horario_empresa;
	private List<ConsultaAssociacoesDTO> associacoes;
}
