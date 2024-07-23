package br.com.jbst.DTO2;

import java.util.UUID;

import lombok.Data;

@Data
public class PostMaquinas_LaudoEletricoDTO {
	private String tiposMaquinas_LaudoEletrico;
   	private String condicoesMaquinas_LaudoEletrico;
   	private String instalacaoMaquinas_LaudoEletrico;
   	private String acionamentoMaquinas_LaudoEletrico;
    private UUID idInspecao;
}
