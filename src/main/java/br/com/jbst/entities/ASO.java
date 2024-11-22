	package br.com.jbst.entities;

import java.time.Instant;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.Data;

@Data
@Entity
@Table(name = "aso")
public class ASO {

	@Id
	@Column(name = "id_aso")
	private UUID id_aso;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "datahoracriacao", nullable = false) 
	private Instant dataHoraCriacao;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "dia_hora", nullable = true)
	private Instant dia_hora;
	
	
	
	//Campo 5
	@Column(name = "cnpj", length = 300, nullable = false)
	private String cnpj;
	
	@Column(name = "razaosocial", length = 300, nullable = false)
	private String razaosocial;
	
	@Column(name = "numeroaso", nullable = true)
	private Integer numeroaso;

	//Campo 4
	@Column(name = "nomefantasia", length = 100, nullable = false)
	private String nomefantasia;
	
	@Column(name = "cep", length = 30, nullable = false)
	private String cep;
	
    // Campo 4
	@Column(name = "logradouro", length = 100, nullable = false)
	private String logradouro;
	
    // Campo 5
	@Column(name = "complemento", length = 100, nullable = false)
	private String complemento;
	
    // Campo 6
	@Column(name = "numero", length = 100, nullable = false)
	private String numero;
	
    // Campo 7
	@Column(name = "bairro", length = 100, nullable = false)
	private String bairro;
	
    // Campo 8
	@Column(name = "localidade", length = 100, nullable = false)
	private String localidade;
	
    // Campo 9
	@Column(name = "uf", length = 100, nullable = false)
	private String uf;
	
	@Column(name = "nome", length = 100, nullable = false)
	private String nome;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "data_nascimento", nullable = true)
	private Instant data_nascimento_funcionario;
	
	// Campo 4
	@Column(name = "cpf", length = 100, nullable = true)
	private String cpf;
	
	// Campo 5
	@Column(name = "rg", length = 100, nullable = false)
	private String rg;
	
	@Column(name = "funcao", length = 100, nullable = false)
	private String funcao;
	
	@Column(name = "setor", length = 100, nullable = false)
	private String setor;
	
	@Column(name = "medico_trabalho", length = 100, nullable = true)
	private String medico_trabalho;
	
	@Column(name = "crm_medico_trabalho", length = 100, nullable = true)
	private String crm_medico_trabalho;
	
	@Column(name = "cpf_medico_trabalho", length = 100, nullable = true)
	private String cpf_medico_trabalho;
	
	@Column(name = "nome_profissionalsaude", length = 100, nullable = false)
	private String nome_profissionalsaude;

	// Campo 5
	@Column(name = "cpf1", length = 100, nullable = true)
	private String cpf1;
	
	@Column(name = "formacao_saude", length = 100, nullable = false)
	private String formacao_saude;

	@Column(name = "conselho", length = 50, nullable = true)
	private String conselho;

	@Column(name = "registro", length = 100, nullable = true)
	private String registro;

	@Column(name = "estado", length = 100, nullable = true)
	private String estado;
	
	@Column(name = "tipo_aso", length = 100, nullable = true)
	private String tipo_aso;
	
	@Column(name = "exame_clinico", length = 100, nullable = true)
	private String exame_clinico;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "data_exame_clinico", nullable = true)
	private Instant data_exame_clinico;	
	
	@Column(name = "acuidadevisual", length = 100, nullable = true)
	private String acuidadevisual;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "data_acuidadevisual", nullable = true)
	private Instant data_acuidadevisual;	
	
	@Column(name = "avaliacao_psicossocial", length = 100, nullable = true)
	private String avaliacao_psicossocial;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "data_avaliacao_psicossocial", nullable = true)
	private Instant data_avaliacao_psicossocial;
	
	@Column(name = "eletrocardiograma", length = 100, nullable = true)
	private String eletrocardiograma;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "data_eletrocardiograma", nullable = true)
	private Instant data_eletrocardiograma;
	
	@Column(name = "eletroencefalograma", length = 100, nullable = true)
	private String eletroencefalograma;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "data_eletroencefalograma", nullable = true)
	private Instant data_eletroencefalograma;
	
	@Column(name = "espirometria", length = 100, nullable = true)
	private String espirometria;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "data_espirometria", nullable = true)
	private Instant data_espirometria;
	
	@Column(name = "glicemia", length = 100, nullable = true)
	private String glicemia;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "data_glicemia", nullable = true)
	private Instant data_glicemia;
	
	@Column(name = "audiometria", length = 100, nullable = true)
	private String audiometria;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "data_audiometria", nullable = true)
	private Instant data_audiometria;
	
	
	@Column(name = "fator_rh", length = 100, nullable = true)
	private String fator_rh;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "data_fator_rh", nullable = true)
	private Instant data_fator_rh;
	
	
	@Column(name = "grupo_sanguineo", length = 100, nullable = true)
	private String grupo_sanguineo;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "data_grupo_sanguineo", nullable = true)
	private Instant data_grupo_sanguineo;
	
	@Column(name = "hemograma_completo", length = 100, nullable = true)
	private String hemograma_completo;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "data_hemograma_completo", nullable = true)
	private Instant data_hemograma_completo;
	
	@Column(name = "testeromberg", length = 100, nullable = true)
	private String testeromberg;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "data_testeromberg", nullable = true)
	private Instant data_testeromberg;
	
	@Column(name = "raio_x_lombo_sacra", length = 100, nullable = true)
	private String raio_x_lombo_sacra;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "data_raio_x_lombo_sacra", nullable = true)
	private Instant data_raio_x_lombo_sacra;
	
	@Column(name = "raio_x_joelho_direito", length = 100, nullable = true)
	private String raio_x_joelho_direito;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "data_raio_x_joelho_direito", nullable = true)
	private Instant data_raio_x_joelho_direito;
	
	@Column(name = "raio_x_joelho_esquerdo", length = 100, nullable = true)
	private String raio_x_joelho_esquerdo;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "data_raio_x_joelho_esquerdo", nullable = true)
	private Instant data_raio_x_joelho_esquerdo;
	
	@Column(name = "raio_x_joelho_coxofemorais_direito", length = 100, nullable = true)
	private String raio_x_joelho_coxofemorais_direito;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "data_raio_x_joelho_coxofemorais_direito", nullable = true)
	private Instant data_raio_x_joelho_coxofemorais_direito;
	
	@Column(name = "raio_x_joelho_coxofemorais_esquerdo", length = 100, nullable = true)
	private String raio_x_joelho_coxofemorais_esquerdo;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "data_raio_x_joelho_coxofemorais_esquerdo", nullable = true)
	private Instant data_raio_x_joelho_coxofemorais_esquerdo;
	
	@Column(name = "raio_x_ombro", length = 100, nullable = true)
	private String raio_x_ombro;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "data_raio_x_ombro", nullable = true)
	private Instant data_raio_x_ombro;
	
	@Column(name = "acido_s_fenil_mercapturico", length = 100, nullable = true)
	private String acido_s_fenil_mercapturico;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "data_acido_s_fenilmercapturico", nullable = true)
	private Instant data_acido_s_fenilmercapturico;
	
	@Column(name = "acido_s_acido_hipurico", length = 100, nullable = true)
	private String acido_s_acido_hipurico;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "data_acido_hipurico", nullable = true)
	private Instant data_acido_hipurico;
	
	@Column(name = "acido_trans_muconico", length = 100, nullable = true)
	private String acido_trans_muconico;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "data_acido_trans_muconico", nullable = true)
	private Instant data_acido_trans_muconico;
	
	@Column(name = "parecer_medico", length = 1000, nullable = true)
	private String parecer_medico;
	
	@Column(name = "parecer_medico_altura", length = 500, nullable = true)
	private String parecer_medico_altura;
	
	@Column(name = "parecer_medico_confinado", length = 500, nullable = true)
	private String parecer_medico_confinado;
	
	@Column(name = "parecer_medico_atividade_subaquatica", length = 500, nullable = true)
	private String parecer_medico_atividade_subaquatica;
	
	@Column(name = "observacoes", length = 1000, nullable = true)
	private String observacoes;
	
	@Column(name = "assinatura_medico", length = 100, nullable = true)
	private byte[] assinatura_medico;
	
	@Column(name = "assinatura_colaborador", length = 100, nullable = true)
	private byte[] assinatura_colaborador;
	
	@OneToOne
	@JoinColumn(name = "id_atendimento") 
    private Atendimento atendimento;
	
}
