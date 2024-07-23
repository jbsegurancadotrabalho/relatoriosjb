package br.com.jbst.DTO;

import java.util.List;

import java.util.UUID;

import br.com.jbst.entities.AnalisePostura;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetPosturaDTO {
	private UUID idPostura;
	private String nomePostura;
	private String descricaoPostura;
	private List<GetAnaliseDePosturaDTO> analisepostura;

}
