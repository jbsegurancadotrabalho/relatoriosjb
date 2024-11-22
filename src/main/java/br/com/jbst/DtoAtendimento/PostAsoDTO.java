package br.com.jbst.DtoAtendimento;

import java.time.Instant;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

@Data
public class PostAsoDTO {
	private UUID idAtendimento;//feito
	private Instant dia_hora;	//feito
	private String razaosocial;//feito
	private String nomefantasia;//feito
	private String cnpj;//feito
	private String cep;//feito
	private String logradouro;//feito
	private String complemento;//feito
	private String numero;//feito
	private String bairro;//feito
	private String localidade;//feito
	private String uf;//feito
	private String nome;//feito
	private Instant data_nascimento_funcionario;
	private String cpf;//feito
	private String rg;//feito
	private String funcao;//feito
	private String setor;
	private String nome_profissionalsaude;
	private String cpf1;
	private String formacao_saude;
	private String conselho;
	private String registro;
	private String estado;
	private String tipo_aso;
	private String exame_clinico;
	private Instant data_exame_clinico;
	private String acuidadevisual;
	private Instant data_acuidadevisual;
	private String avaliacao_psicossocial;
	private Instant data_avaliacao_psicossocial;
	private String eletrocardiograma;
	private Instant data_eletrocardiograma;
	private String eletroencefalograma;;
	private Instant data_eletroencefalograma;
	private String espirometria;;
	private Instant data_espirometria;
	private String glicemia;;
	private Instant data_glicemia;
	private String audiometria;
	private Instant data_audiometria;
	private String fator_rh;
	private Instant data_fator_rh;
	private String grupo_sanguineo;
	private Instant data_grupo_sanguineo;
	private String hemograma_completo;
	private Instant data_hemograma_completo;
	private String testeromberg;
	private Instant data_testeromberg;
	private String raio_x_lombo_sacra;
	private Instant data_raio_x_lombo_sacra;
	private String raio_x_joelho_direito;
	private Instant data_raio_x_joelho_direito;
	private String raio_x_joelho_esquerdo;
	private Instant data_raio_x_joelho_esquerdo;
	private String raio_x_joelho_coxofemorais_direito;
	private Instant data_raio_x_joelho_coxofemorais_direito;
	private String raio_x_joelho_coxofemorais_esquerdo;
	private Instant data_raio_x_joelho_coxofemorais_esquerdo;
	private String raio_x_ombro;
	private Instant data_raio_x_ombro;
	private String acido_s_fenil_mercapturico;
	private Instant data_acido_s_fenilmercapturico;
	private String acido_s_acido_hipurico;
	private Instant data_acido_hipurico;
	private String acido_trans_muconico;
	private Instant data_acido_trans_muconico;
	private String parecer_medico;
	private String parecer_medico_altura;
	private String parecer_medico_confinado;
	private String parecer_medico_atividade_subaquatica;
	private String observacoes;

}
