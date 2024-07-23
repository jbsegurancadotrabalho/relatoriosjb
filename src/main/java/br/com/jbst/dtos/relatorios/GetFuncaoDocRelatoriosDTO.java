package br.com.jbst.dtos.relatorios;

import java.time.Instant;

import java.util.List;
import java.util.UUID;

import br.com.jbst.DTO.GetCursoDeNRsDTO;
import br.com.jbst.DTO.GetEpiDTO;
import br.com.jbst.DTO.GetExamesDTO;
import br.com.jbst.DTO.GetMaquinasDTO;
import br.com.jbst.DTO.GetPerigoDTO;
import br.com.jbst.DTO.GetRiscosDTO;
import lombok.Data;

@Data
public class GetFuncaoDocRelatoriosDTO {
    private UUID idFuncaoDoc;
    private Instant dataHoraCriacao;
    private String funcao_doc;
	private List<GetRiscosDTO> riscos;
	private List<GetCursoDeNRsDTO> cursosDeNrs;
	private List<GetEpiDTO> epi;
	private List<GetExamesDTO> exames;
	private List<GetMaquinasDTO> maquinas;
	private List <GetPerigoDTO> perigo;
}
