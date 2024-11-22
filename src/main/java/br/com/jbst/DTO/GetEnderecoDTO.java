package br.com.jbst.DTO;

import java.time.Instant;
import java.util.UUID;

import br.com.jbst.DTO2.GetCredenciadosDTO;
import lombok.Data;


@Data
public class GetEnderecoDTO {
	private UUID idEndereco;
	private Instant dataHoraCriacao;
	private String cep;
	private String logradouro;
	private String complemento;
	private String numero;
	private String bairro;
	private String localidade;
	private String uf;
}
