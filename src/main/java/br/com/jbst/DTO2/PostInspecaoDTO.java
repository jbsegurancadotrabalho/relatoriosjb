package br.com.jbst.DTO2;

import java.util.UUID;

import lombok.Data;

@Data
public class PostInspecaoDTO {
    private String extensao;
    private String mediaTensao;
    private String baixaTensao;
    private String fn;
    private String ffn;
    private String fffn;
    private String transformadores;
    private String substacoes;
    private UUID idunidadeDoc;

}
