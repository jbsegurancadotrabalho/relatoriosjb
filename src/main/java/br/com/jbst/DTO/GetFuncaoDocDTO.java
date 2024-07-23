package br.com.jbst.DTO;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetFuncaoDocDTO {
	private UUID idFuncaoDoc;
	private Instant dataHoraCriacao;
	private String nome_da_funcao;
	private String cenario_funcaodoc;
	private String setor_gho;
	private GetFuncaoDTO funcao;
	private List<GetPerigoDTO> perigo;
	private List<GetRiscosDTO> riscos;
	private List<GetEpiDTO> epi;
	private List<GetCursoDeNRsDTO> cursodenrs;
	private List<GetExamesFuncaoDocDTO> exames_funcaodoc;
	private List<GetAvaliaçõesOcupacionaisFuncoesDTO> avaliacoesocupacionaisfuncoes;
  

	
}
