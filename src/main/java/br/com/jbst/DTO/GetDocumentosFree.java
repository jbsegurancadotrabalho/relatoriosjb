package br.com.jbst.DTO;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import br.com.jbst.config.InstantSerializer;
import lombok.Data;

@Data
public class GetDocumentosFree {
	private UUID idDocumentosFree;
    private Integer numerodocumentofree; // Make sure this attribute exists
	private String razaosocial;
	private String cnpj;
	private String setor;
	private String cenario;
    @JsonSerialize(using = InstantSerializer.class)
	private Instant datainicio;
    @JsonSerialize(using = InstantSerializer.class)
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
	private List <GetEtapasDTO> etapas;

}
