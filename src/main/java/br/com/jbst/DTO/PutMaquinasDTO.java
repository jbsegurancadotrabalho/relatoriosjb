package br.com.jbst.DTO;

import java.util.UUID;

import lombok.Data;

@Data
public class PutMaquinasDTO {
	private UUID idMaquina;
	private String nome_maquina;
	private String local_maquina;
	private String  descricao_maquina;
	private String  descricao_funcao_maquina;
	private String  patrimonio_maquina;
	private String fabricante_maquina;
	private String fabricacao_maquina;
	private String serie_maquina;
	private String modelo_maquina;
	private String posicaotrabalho_maquina;
	private String peso_maquina;
	private String historico_acidente_maquina;
	private String voltagem_maquina;
	private String  comando_maquina;
	private String potencia_maquina;
	private String qtdOperadores_maquina;
	private String turno_maquina;
	private String dias_turno_maquina;
	private String capacidade_maquina;
	private String carga_maquina;
	private String descarga_maquina;
	private String arranjo_fisico;
	private String instalaçoes;
	private String dispositivos_eletricos;
	private String aterramento_maquinas;
	private String condutores_maquinas;
	private String dispositivos_partida;
	private String sistema_seguranca;
	
}
