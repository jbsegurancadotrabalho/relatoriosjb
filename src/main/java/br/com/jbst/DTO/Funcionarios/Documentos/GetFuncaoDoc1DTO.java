package br.com.jbst.DTO.Funcionarios.Documentos;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

import br.com.jbst.DTO.GetAvaliaçõesOcupacionaisFuncoesDTO;
import br.com.jbst.DTO.GetCursoDeNRsDTO;
import br.com.jbst.DTO.GetEpiDTO;
import br.com.jbst.DTO.GetExamesFuncaoDocDTO;
import br.com.jbst.DTO.GetPerigoDTO;
import br.com.jbst.DTO.GetRiscosDTO;
import lombok.Data;

@Data
public class GetFuncaoDoc1DTO {
	private UUID idFuncaoDoc;
	private Instant dataHoraCriacao;
	private String nome_da_funcao;
	private String cenario_funcaodoc;
	private String setor_gho;
	private List<GetPerigoDTO> perigo;
	private List<GetRiscosDTO> riscos;
	private List<GetEpiDTO> epi;
	private List<GetCursoDeNRsDTO> cursodenrs;
	private List<GetExamesFuncaoDocDTO> exames_funcaodoc;
	private List<GetAvaliaçõesOcupacionaisFuncoesDTO> avaliacoesocupacionaisfuncoes;
  
}
