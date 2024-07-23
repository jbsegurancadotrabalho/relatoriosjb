package br.com.jbst.DTO;



import java.util.UUID;

import lombok.Data;

@Data
public class PostPerigoDTO {
    private String nomePerigo;
    private String causasPerigo;
	private String consequencias_perigo;
    private String medidasPreventivas;
    private String classificacao;
	private UUID idFuncaoDoc;

}
