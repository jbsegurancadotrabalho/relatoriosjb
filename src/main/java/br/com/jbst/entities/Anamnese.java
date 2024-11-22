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
@Table(name = "anamnese")
public class Anamnese {

	@Id
	@Column(name = "idanamnese")
	private UUID idAnamnese;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "dia_hora", nullable = true)
	private Instant dia_hora;
	
	//Campo 2
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "datahoracriacao", nullable = false) 
	private Instant dataHoraCriacao;
	
	@Column(name = "numeroanamnese", nullable = true)
	private Integer numeroanamnese;
	
	@Column(name = "empresa", length = 300, nullable = false)
	private String empresa;
	
	@Column(name = "nome", length = 300, nullable = false)
	private String nome;
	
	@Column(name = "telefone", length = 300, nullable = false)
	private String telefone;
	
	@Column(name = "data_admissao", length = 300, nullable = false)
	private String data_admissao;
	
	@Column(name = "data_nascimento", length = 300, nullable = false)
	private String data_nascimento;
	
	@Column(name = "cpf", length = 300, nullable = false)
	private String cpf;
	
	@Column(name = "rg", length = 300, nullable = false)
	private String rg;
	
	@Column(name = "orgaoemissor", length = 300, nullable = false)
	private String orgaoemissor;
	
	@Column(name = "setor", length = 300, nullable = false)
	private String setor;
	
	@Column(name = "funcao", length = 300, nullable = false)
	private String funcao;
	
	@Column(name = "tipo_aso", length = 300, nullable = false)
	private String tipo_aso;
	
	@Column(name = "tubercolose", length = 300, nullable = false)
	private String tubercolose;
	
	@Column(name = "pressaoalta", length = 100, nullable = false)
	private String pressaoalta;
	
	@Column(name = "alcoolismo", length = 300, nullable = false)
	private String alcoolismo;
	
	@Column(name = "cancer", length = 300, nullable = false)
	private String cancer;
	
	@Column(name = "diabetes", length = 300, nullable = false)
	private String diabetes;
	
	@Column(name = "infarto", length = 300, nullable = false)
	private String infarto;
	
	@Column(name = "doencao_nervosa", length = 300, nullable = false)
	private String doencao_nervosa;
	
	@Column(name = "doenca_da_tireoide", length = 300, nullable = false)
	private String doenca_da_tireoide;
	
	@Column(name = "alergias", length = 300, nullable = false)
	private String alergias;
	
	@Column(name = "bebidas_alcoolicas", length = 300, nullable = false)
	private String bebidas_alcoolicas;
	
	@Column(name = "atividade_fisica", length = 300, nullable = false)
	private String atividade_fisica;
	
	@Column(name = "tabagismo", length = 300, nullable = false)
	private String tabagismo;
	
	@Column(name = "transporte", length = 300, nullable = false)
	private String transporte;
	
	@Column(name = "tempo_deslocamento", length = 300, nullable = false)
	private String tempo_deslocamento;
	
	@Column(name = "leitura", length = 300, nullable = false)
	private String leitura;
	
	@Column(name = "cinema_teatro", length = 300, nullable = false)
	private String cinema_teatro;
	
	@Column(name = "videogame", length = 300, nullable = false)
	private String videogame;
	
	@Column(name = "isntrumentos", length = 300, nullable = false)
	private String isntrumentos;
	
	@Column(name = "canto", length = 300, nullable = false)
	private String canto;
	
	@Column(name = "tv", length = 300, nullable = false)
	private String tv;
	
	@Column(name = "basquete_volei", length = 300, nullable = false)
	private String basquete_volei;
	
	@Column(name = "modelagem", length = 300, nullable = false)
	private String modelagem;
	
	@Column(name = "musica_com_head_fone", length = 300, nullable = false)
	private String musica_com_head_fone;
	
	@Column(name = "musica_sem_head_fone", length = 300, nullable = false)
	private String musica_sem_head_fone;
	
	@Column(name = "futebol", length = 300, nullable = false)
	private String futebol;
	
	@Column(name = "lutas", length = 300, nullable = false)
	private String lutas;
	
	@Column(name = "musculacao", length = 300, nullable = false)
	private String musculacao;
	
	@Column(name = "jardinagem", length = 300, nullable = false)
	private String jardinagem;
	
	@Column(name = "tenis", length = 300, nullable = false)
	private String tenis;
	
	@Column(name = "jogos_computador", length = 300, nullable = false)
	private String jogos_computador;
	
	@Column(name = "navegar_internet", length = 300, nullable = false)
	private String navegar_internet;
	
	@Column(name = "outros", length = 300, nullable = false)
	private String outros;
	
	@Column(name = "historia_ocup1", length = 300, nullable = false)
	private String historia_ocup1;
	
	@Column(name = "historia_ocup2", length = 300, nullable = false)
	private String historia_ocup2;
	
	@Column(name = "historia_ocup3", length = 300, nullable = false)
	private String historia_ocup3;
	
	@Column(name = "historia_ocup4", length = 300, nullable = false)
	private String historia_ocup4;
	
	@Column(name = "historia_ocup5", length = 300, nullable = false)
	private String historia_ocup5;
	
	@Column(name = "historia_ocup6", length = 300, nullable = false)
	private String historia_ocup6;
	
	@Column(name = "oculos", length = 300, nullable = false)
	private String oculos;
	
	@Column(name = "corrigir_miopia", length = 300, nullable = false)
	private String corrigir_miopia;
	
	
	@Column(name = "corrigir_antigmatismo", length = 300, nullable = false)
	private String corrigir_antigmatismo;
	
	@Column(name = "corrigir_hipermetropia", length = 300, nullable = false)
	private String corrigir_hipermetropia;
	
	@Column(name = "corrigir_vista_cansada", length = 300, nullable = false)
	private String corrigir_vista_cansada;
	
	@Column(name = "corrigir_nao_sei", length = 300, nullable = false)
	private String corrigir_nao_sei;
	
	@Column(name = "cistite", length = 300, nullable = false)
	private String cistite;
	
	@Column(name = "diabete", length = 300, nullable = false)
	private String diabete;
	
	@Column(name = "tonteiras", length = 300, nullable = false)
	private String tonteiras;
	
	@Column(name = "renite_sinusite", length = 300, nullable = false)
	private String renite_sinusite;
	
	@Column(name = "varises", length = 300, nullable = false)
	private String varises;
	
	@Column(name = "dor_de_cabeca", length = 300, nullable = false)
	private String dor_de_cabeca;
	
	@Column(name = "prisao_ventre", length = 300, nullable = false)
	private String prisao_ventre;
	
	@Column(name = "hemorraidas", length = 300, nullable = false)
	private String hemorraidas;
	
	@Column(name = "pressao_alta", length = 300, nullable = false)
	private String pressao_alta;
	
	@Column(name = "hernia", length = 300, nullable = false)
	private String hernia;
	
	@Column(name = "dor_no_peito", length = 300, nullable = false)
	private String dor_no_peito;
	
	@Column(name = "dor_nas_pernas", length = 300, nullable = false)
	private String dor_nas_pernas;
	
	@Column(name = "resfriados", length = 300, nullable = false)
	private String resfriados;
	
	@Column(name = "problemas_tireoides", length = 300, nullable = false)
	private String problemas_tireoides;
	
	@Column(name = "epilepsia_convulcoes", length = 300, nullable = false)
	private String epilepsia_convulcoes;
	
	@Column(name = "asma_bronquite", length = 300, nullable = false)
	private String asma_bronquite;
	
	@Column(name = "tubercolose1", length = 300, nullable = false)
	private String tubercolose1;
	
	@Column(name = "ulcera", length = 300, nullable = false)
	private String ulcera;
	
	@Column(name = "tosse_cronica", length = 300, nullable = false)
	private String tosse_cronica;
	
	@Column(name = "pedra_nos_rins", length = 300, nullable = false)
	private String pedra_nos_rins;
	
	@Column(name = "remedios", length = 300, nullable = false)
	private String remedios;
	
	@Column(name = "hepatite_a", length = 300, nullable = false)
	private String hepatite_a;
	
	@Column(name = "hepatite_b", length = 300, nullable = false)
	private String hepatite_b;
	
	@Column(name = "hepatite_c", length = 300, nullable = false)
	private String hepatite_c;
	
	@Column(name = "hepatite_nao_sei", length = 300, nullable = false)
	private String hepatite_nao_sei;
	
	@Column(name = "operacao", length = 300, nullable = false)
	private String operacao;
	
	@Column(name = "tipo_operacao", length = 300, nullable = false)
	private String tipo_operacao;
	
	@Column(name = "ano_operacao", length = 300, nullable = false)
	private String ano_operacao;
	
	@Column(name = "quebra_osso", length = 300, nullable = false)
	private String quebra_osso;
	
	@Column(name = "quebra_osso_onde", length = 300, nullable = false)
	private String quebra_osso_onde;
	
	@Column(name = "dor_dedo_direito", length = 300, nullable = false)
	private String dor_dedo_direito;
	
	@Column(name = "dor_dedo_esquerdo", length = 300, nullable = false)
	private String dor_dedo_esquerdo;
	
	@Column(name = "dor_maos_direito", length = 300, nullable = false)
	private String dor_maos_direito;
	
	@Column(name = "dor_maos_esquerdo", length = 300, nullable = false)
	private String dor_maos_esquerdo;
	
	@Column(name = "dor_punho_direito", length = 300, nullable = false)
	private String dor_punho_direito;
	
	@Column(name = "dor_punho_esquerdo", length = 300, nullable = false)
	private String dor_punho_esquerdo;
	
	@Column(name = "dor_cotovelo_direito", length = 300, nullable = false)
	private String dor_cotovelo_direito;
	
	@Column(name = "dor_cotovelo_esquerdo", length = 300, nullable = false)
	private String dor_cotovelo_esquerdo;
	
	@Column(name = "dor_braco_direito", length = 300, nullable = false)
	private String dor_braco_direito;
	
	@Column(name = "dor_braco_esquerdo", length = 300, nullable = false)
	private String dor_braco_esquerdo;
	
	@Column(name = "dor_ombro_direito", length = 300, nullable = false)
	private String dor_ombro_direito;
	
	@Column(name = "dor_ombro_esquerdo", length = 300, nullable = false)
	private String dor_ombro_esquerdo;
	
	@Column(name = "dor_joelho_direito", length = 300, nullable = false)
	private String dor_joelho_direito;
	
	@Column(name = "dor_joelho_esquerdo", length = 300, nullable = false)
	private String dor_joelho_esquerdo;
	
	@Column(name = "dor_pescoco", length = 300, nullable = false)
	private String dor_pescoco;
	
	@Column(name = "dor_costas_superior", length = 300, nullable = false)
	private String dor_costas_superior;
	
	@Column(name = "dor_costas_medias", length = 300, nullable = false)
	private String dor_costas_medias;
	
	@Column(name = " dor_costas_inferior", length = 300, nullable = false)
	private String dor_costas_inferior;
	
	@Column(name = "doenca_sexualmente_transmissivel", length = 300, nullable = false)
	private String doenca_sexualmente_transmissivel;
	
	@Column(name = "prostata", length = 300, nullable = true)
	private String prostata;
	
	@Column(name = "anticosepsional", length = 300, nullable = true)
	private String anticosepsional;
	
	@Column(name = "aborto", length = 300, nullable = true)
	private String aborto;
	
	@Column(name = "preventivo_no_ano", length = 300, nullable = true)
	private String preventivo_no_ano;
	
	@Column(name = "data_mestruacao", length = 300, nullable = true)
	private String data_mestruacao;
	
	@Column(name = "tensao_mestruacao", length = 300, nullable = true)
	private String tensao_mestruacao;
	
	@Column(name = "status_mestruacao", length = 300, nullable = true)
	private String status_mestruacao;
	
	@Column(name = "colicas", length = 300, nullable = true)
	private String colicas;
	
	@Column(name = "gravidez", length = 300, nullable = true)
	private String gravidez;
	
	@Column(name = "pressao_arterial", length = 300, nullable = true)
	private String pressao_arterial;
	
	@Column(name = "frequencia_cardiaca", length = 300, nullable = true)
	private String frequencia_cardiaca;
	
	@Column(name = "batimentos_por_minuto", length = 300, nullable = true)
	private String batimentos_por_minuto;
	
	@Column(name = "icm", length = 300, nullable = true)
	private String icm;
	
	@Column(name = "altura", length = 300, nullable = false)
	private String altura;
	
	@Column(name = "peso", length = 300, nullable = false)
	private String peso;
	
	@Column(name = "ganglios", length = 300, nullable = false)
	private String ganglios;
	
	@Column(name = "tireoide", length = 300, nullable = true)
	private String tireoide;
	
	@Column(name = "dentes", length = 300, nullable = false)
	private String dentes;
	
	@Column(name = "orofaringe", length = 300, nullable = true)
	private String orofaringe;
	
	@Column(name = "acuidade_visual", length = 300, nullable = false)
	private String acuidade_visual;
	
	@Column(name = "ritimo_cardiaco", length = 300, nullable = true)
	private String ritimo_cardiaco;
	
	@Column(name = "ritimo_cardiaco_obs", length = 300, nullable = true)
	private String ritimo_cardiaco_obs;
	
	@Column(name = "sopros", length = 300, nullable = true)
	private String sopros;
	
	@Column(name = "pulmoes", length = 300, nullable = true)
	private String pulmoes;
	
	@Column(name = "abdomen", length = 300, nullable = false)
	private String abdomen;
	
	@Column(name = "maos_punhos", length = 300, nullable = false)
	private String maos_punhos;
	
	@Column(name = "perda_forca_de_preensao", length = 300, nullable = false)
	private String perda_forca_de_preensao;
	
	@Column(name = "tinel", length = 300, nullable = false)
	private String tinel;
	
	@Column(name = "phalen", length = 300, nullable = false)
	private String phalen;
	
	@Column(name = "filkenstein", length = 300, nullable = false)
	private String filkenstein;
	
	@Column(name = "cotovelo_direito", length = 300, nullable = false)
	private String cotovelo_direito;
	
	@Column(name = "cotovelo_esquerdo", length = 300, nullable = false)
	private String cotovelo_esquerdo;
	
	@Column(name = "ombro_direito", length = 300, nullable = false)
	private String ombro_direito;
	
	@Column(name = "ombro_esquerdo", length = 300, nullable = false)
	private String ombro_esquerdo;
	
	@Column(name = "coluna", length = 300, nullable = false)
	private String coluna;
	
	@Column(name = "coluna_outros", length = 300, nullable = false)
	private String coluna_outros;
	
	@Column(name = "aparelho_muscular", length = 300, nullable = false)
	private String aparelho_muscular;
	
	@Column(name = "membros_inferiores", length = 300, nullable = false)
	private String membros_inferiores;
	
	@Column(name = "membros_inferiores_obs", length = 300, nullable = false)
	private String membros_inferiores_obs;
	
	@Column(name = "joelho_direito", length = 300, nullable = false)
	private String joelho_direito;
	
	@Column(name = "joelho_esquerdo", length = 300, nullable = false)
	private String joelho_esquerdo;
	
	@Column(name = "exames_complementares", length = 500, nullable = true)
	private String exames_complementares;
	
	@Column(name = "risco_quimico", length = 500, nullable = true)
	private String risco_quimico;
	
	@Column(name = "risco_fisico", length = 500, nullable = true)
	private String risco_fisico;
	
	@Column(name = "risco_biologico", length = 500, nullable = true)
	private String risco_biologico;
	
	@Column(name = "risco_ergonomico", length = 500, nullable = true)
	private String risco_ergonomico;
	
	@Column(name = "risco_especifico", length = 500, nullable = true)
	private String risco_especifico;
	
	@Column(name = "exame_complementar", length = 500, nullable = true)
	private String exame_complementar;
	
	@Column(name = "parecer_especialista", length = 500, nullable = true)
	private String parecer_especialista;
	
	@Column(name = "encaminhamento_perito", length = 300, nullable = true)
	private String encaminhamento_perito;
	
	@Column(name = "cat", length = 300, nullable = true)
	private String cat;
	
	@Column(name = "observacoes", length = 300, nullable = true)
	private String observacoes;
	
	@Column(name = "status_anamnese", length = 300, nullable = true)
	private String status_anamnese;

	
	@OneToOne
	@JoinColumn(name = "id_atendimento") 
    private Atendimento atendimento;
}
