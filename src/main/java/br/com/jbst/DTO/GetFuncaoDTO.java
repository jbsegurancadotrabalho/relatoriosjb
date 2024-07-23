package br.com.jbst.DTO;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

import br.com.jbst.DTO.Funcionarios.Documentos.GetFuncaoDoc1DTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor 
public class GetFuncaoDTO {	
	private UUID idFuncao;
	private Instant dataHoraCriacao;
	private String nome_da_funcao;
	private String cbo;	
	private String descricao;
	private String gro;
	private String gp;
	private List <GetFuncaoDoc1DTO> funcaodoc;
}
