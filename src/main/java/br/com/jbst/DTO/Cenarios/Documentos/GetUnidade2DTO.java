package br.com.jbst.DTO.Cenarios.Documentos;

import java.util.UUID;

import br.com.jbst.DTO.GetEnderecoDTO;
import lombok.Data;

@Data
public class GetUnidade2DTO {
	private UUID idunidadeDoc;
	private String nomeEspecificoUnidade;
	private String nomefantasia;
	private String cnpj;
	private String horario_empresa;
	private GetEnderecoDTO endereco;
}
