package br.com.jbst.DTO;

import java.util.List;
import java.util.UUID;

import lombok.Data;

@Data
public class GetFuncaoEspecificaDTO {
	private UUID idFuncaoEspecifica;
	private String setor_gho;
	private String nome_da_funcao;
	private String cenario_funcaodoc;
	private String cbo;
	private String descricao;
	private String gro;
	private String gp;
    private List<GetPerigoDTO> perigos;
    private List<GetRiscosDTO> riscos;
    private List<GetEpiDTO> epi;
    private List<GetCursoDeNRsDTO> cursodenrs;
    private List<GetExamesFuncaoDocDTO> examesfuncaodoc;
    private List<GetAvaliaçõesOcupacionaisFuncoesDTO> avaliaçõesocupacionaisfuncoes;
}
