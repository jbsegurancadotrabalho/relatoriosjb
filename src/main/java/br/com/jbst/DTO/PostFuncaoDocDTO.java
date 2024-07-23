package br.com.jbst.DTO;

import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class PostFuncaoDocDTO {
	private String nome_da_funcao;
    private String setor_gho;
    private String cenario_funcaodoc;
    private UUID idFuncao;

}
