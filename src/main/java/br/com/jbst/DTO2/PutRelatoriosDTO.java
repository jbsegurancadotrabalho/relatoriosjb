package br.com.jbst.DTO2;

import java.time.Instant;
import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class PutRelatoriosDTO {
	private UUID idRelatorios;
	private String nome_relatorio;
	private String descricao_relatorio;
	private Instant datainicio;
	private Instant vigencia;
	
}
