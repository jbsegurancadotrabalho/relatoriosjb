package br.com.jbst.DTO;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

import br.com.jbst.dtos.relatorios.GetFuncionarioRelatoriosDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetEmpresaDTO {
	private UUID idEmpresa;
    private Instant dataHoraCriacao;
    private String razaosocial;
    private String nomefantasia;
    private String cnpj;
    private String status;
    private String inscricaoestadual;
    private String inscricaomunicipal;
    private String responsavelSistema;
    private String emailUsuario;
    private String senhaSistema;
    private String telefoneResponsavel;
    private List <GetDocumentosFree> documentosfree;
   private GetEmpresaDocDTO empresadoc;
   private List<GetFuncionarioRelatoriosDTO> funcionarios;
}
