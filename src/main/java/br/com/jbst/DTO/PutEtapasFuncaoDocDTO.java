package br.com.jbst.DTO;

import java.util.UUID;

import lombok.Data;

@Data
public class PutEtapasFuncaoDocDTO {
    private UUID idEtapas;
    private String numero_etapa;
    private String nome_etapa;
    private String descricao_etapa;
    private String acoes;
}
