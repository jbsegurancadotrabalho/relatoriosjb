package br.com.jbst.DTO;

import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PutFuncaoDocDTO {
	private UUID idFuncaoDoc;
    private String nome_da_funcao;
	private String cenario_funcaodoc;
	private String setor_gho;

}
