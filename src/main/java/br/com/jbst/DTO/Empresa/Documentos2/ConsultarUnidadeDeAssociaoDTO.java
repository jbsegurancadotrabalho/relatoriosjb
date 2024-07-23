package br.com.jbst.DTO.Empresa.Documentos2;

import java.util.List;
import java.util.UUID;

import br.com.jbst.DTO.GetEnderecoDTO;
import br.com.jbst.DTO.GetFuncaoDocDTO;
import br.com.jbst.DTO.GetGho_SetorDTO;
import lombok.Data;

@Data
public class ConsultarUnidadeDeAssociaoDTO {
	private UUID idunidadeDoc;
	private String nomeEspecificoUnidade;
	private String nomefantasia;
	private String cnpj;
	private String horario_empresa;
	private GetEnderecoDTO endereco;
    private  ConsultaEmpresaDocDTO empresadoc;
    private List<GetFuncaoDocDTO> funcaodoc;
    private List<GetGho_SetorDTO> gho_setor;
}
