package br.com.jbst.DTO;

import java.time.Instant;
import java.util.UUID;

import lombok.Data;

@Data
public class PostDocumentosFree {
	private String razaosocial;
	private String cnpj;
	private String setor;
	private String cenario;
	private Instant datainicio;
	private Instant vigencia;
	private String endereco;
	private String servico;
	private String funcoesenvolvidas;
	private String equipamentos;
	private String responsavel;
	private String emitentes;
	private String cipa;
	private String brigada;
	private String resgate;
	private String ambulancia;
	private String seguranca;
	private String local_espaco_confinado;
	private String autorizados_vigia;
	private String autorizados_supervisor;
	private String autorizados_resgate;
    private UUID idEmpresa;

}
