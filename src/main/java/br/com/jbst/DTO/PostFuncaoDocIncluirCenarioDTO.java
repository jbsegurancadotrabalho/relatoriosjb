package br.com.jbst.DTO;



import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PostFuncaoDocIncluirCenarioDTO {

	    private String funcao_doc;
	    private String setor_gho;
	    private String cenario_funcaodoc;
	    private UUID idCenario;
	    private UUID idFuncao;
    
}