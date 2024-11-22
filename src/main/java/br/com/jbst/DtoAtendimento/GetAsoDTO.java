package br.com.jbst.DtoAtendimento;

import java.time.Instant;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import br.com.jbst.config.InstantSerializer;
import lombok.Data;

@Data
public class GetAsoDTO {
	private UUID id_aso;
	private String razaosocial;
	
	
    @JsonSerialize(using = InstantSerializer.class)
	private Instant dia_hora;
    
	private String nomefantasia;
	private String cnpj;
	private String cep;
	private String logradouro;
	private String complemento;
	private String numero;
	private String bairro;
	private String localidade;
	private String uf;
	private String nome;
    @JsonSerialize(using = InstantSerializer.class)
	private Instant data_nascimento_funcionario;
	private String cpf;
	private String rg;
	private String funcao;
	private String setor;	
	private String nome_profissionalsaude;
	private String cpf1;	
	private String formacao_saude;
	private String conselho;
	private String registro;
	private String estado;
	private String medico_trabalho;
	private String crm_medico_trabalho;
	private String cpf_medico_trabalho;
	private String tipo_aso;
	private String exame_clinico;
    @JsonSerialize(using = InstantSerializer.class)
	private Instant data_exame_clinico;	
	private String acuidadevisual;
    @JsonSerialize(using = InstantSerializer.class)
	private Instant data_acuidadevisual;	
	private String avaliacao_psicossocial;
    @JsonSerialize(using = InstantSerializer.class)
	private Instant data_avaliacao_psicossocial;
	private String eletrocardiograma;
    @JsonSerialize(using = InstantSerializer.class)
	private Instant data_eletrocardiograma;
	private String eletroencefalograma;
    @JsonSerialize(using = InstantSerializer.class)
	private Instant data_eletroencefalograma;
	private String espirometria;
    @JsonSerialize(using = InstantSerializer.class)
	private Instant data_espirometria;
	private String glicemia;
    @JsonSerialize(using = InstantSerializer.class)
	private Instant data_glicemia;
	private String audiometria;
    @JsonSerialize(using = InstantSerializer.class)
	private Instant data_audiometria;
	private String fator_rh;
    @JsonSerialize(using = InstantSerializer.class)
	private Instant data_fator_rh;
	private String grupo_sanguineo;
    @JsonSerialize(using = InstantSerializer.class)
	private Instant data_grupo_sanguineo;
	private String hemograma_completo;
    @JsonSerialize(using = InstantSerializer.class)
	private Instant data_hemograma_completo;
	private String testeromberg;
	
    @JsonSerialize(using = InstantSerializer.class)
	private Instant data_testeromberg;
	private String raio_x_lombo_sacra;
    @JsonSerialize(using = InstantSerializer.class)
	private Instant data_raio_x_lombo_sacra;
	private String raio_x_joelho_direito;
    @JsonSerialize(using = InstantSerializer.class)
	private Instant data_raio_x_joelho_direito;
	private String raio_x_joelho_esquerdo;
    @JsonSerialize(using = InstantSerializer.class)
	private Instant data_raio_x_joelho_esquerdo;
	private String raio_x_joelho_coxofemorais_direito;
    @JsonSerialize(using = InstantSerializer.class)
	private Instant data_raio_x_joelho_coxofemorais_direito;
	private String raio_x_joelho_coxofemorais_esquerdo;
    @JsonSerialize(using = InstantSerializer.class)
	private Instant data_raio_x_joelho_coxofemorais_esquerdo;
	private String raio_x_ombro;
    @JsonSerialize(using = InstantSerializer.class)
	private Instant data_raio_x_ombro;
	private String acido_s_fenil_mercapturico;
    @JsonSerialize(using = InstantSerializer.class)
	private Instant data_acido_s_fenilmercapturico;
	private String acido_s_acido_hipurico;
    @JsonSerialize(using = InstantSerializer.class)
	private Instant data_acido_hipurico;
	private String acido_trans_muconico;
    @JsonSerialize(using = InstantSerializer.class)
	private Instant data_acido_trans_muconico;
	private String parecer_medico;
	private String parecer_medico_altura;
	private String parecer_medico_confinado;
	private String parecer_medico_atividade_subaquatica;
	private String observacoes;
	private byte[] assinatura_medico;
	private byte[] assinatura_colaborador;
}
