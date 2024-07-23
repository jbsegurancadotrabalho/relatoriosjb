package br.com.jbst.entities;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.Data;
@Data
@Entity
@Table(name = "maquinas")
public class Maquinas {
	
	@Id
	@Column(name = "id_maquina")
	private UUID idMaquina;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "datahoracriacao", nullable = false)
	private Instant dataHoraCriacao;
	
	@Column(name = "nome_maquina", length = 200, nullable = true)
	private String nome_maquina;
	
	@Column(name = "local_maquina", length = 200, nullable = true)
	private String local_maquina;
	
	@Column(name = "descricao_maquina", length = 1000, nullable = true)
	private String  descricao_maquina;
	
	@Column(name = "descricao_funcao_maquina", length = 500, nullable = true)
	private String  descricao_funcao_maquina;
	
	@Column(name = "patrimonio", length = 500, nullable = true)
	private String  patrimonio_maquina;
	
	@Column(name = "descricao_gho_setor", length = 200, nullable = true)
	private String fabricante_maquina;
	
	@Column(name = "fabricacao", length = 200, nullable = true)
	private String fabricacao_maquina;
	
	@Column(name = "serie", length = 100, nullable = true)
	private String serie_maquina;
	
	@Column(name = "modelo_maquina", length = 200, nullable = true)
	private String modelo_maquina;
	
	@Column(name = "posicaoTrabalho", length = 200, nullable = true)
	private String posicaotrabalho_maquina;
	
	@Column(name = "peso_maquina", length = 200, nullable = true)
	private String peso_maquina;
	
	@Column(name = "historico_acidente_maquina", length = 500, nullable = true)
	private String historico_acidente_maquina;
	
	@Column(name = "voltagem_maquina", length = 200, nullable = true)
	private String voltagem_maquina;
	
	@Column(name = "comando_maquina", length = 200, nullable = true)
	private String  comando_maquina;
	
	@Column(name = "potencia_maquina", length = 200, nullable = true)
	private String potencia_maquina;
	
	@Column(name = "qtdOperadores_maquina", length = 100, nullable = true)
	private String qtdOperadores_maquina;
	
	@Column(name = "turno_maquina", length = 100, nullable = true)
	private String turno_maquina;
	
	@Column(name = "dias_turno_maquina", length = 200, nullable = true)
	private String dias_turno_maquina;
	
	@Column(name = "capacidade_maquina", length = 500, nullable = true)
	private String capacidade_maquina;
	
	@Column(name = "carga_maquina", length = 500, nullable = true)
	private String carga_maquina;
	
	@Column(name = "descarga_maquina", length = 500, nullable = true)
	private String descarga_maquina;
	
	@Column(name = "arranjo_fisico", length = 500, nullable = true)
	private String arranjo_fisico;

	@Column(name = "instalaçoes", length = 500, nullable = true)
	private String instalaçoes;
	
	@Column(name = "dispositivos_eletricos", length = 500, nullable = true)
	private String dispositivos_eletricos;
	
	@Column(name = "aterramento_maquinas", length = 500, nullable = true)
	private String aterramento_maquinas;
	
	@Column(name = "condutores_maquinas", length = 500, nullable = true)
	private String condutores_maquinas;
	
	@Column(name = "dispositivos_partida", length = 500, nullable = true)
	private String dispositivos_partida;
	
	@Column(name = "sistema_seguranca", length = 500, nullable = true)
	private String sistema_seguranca;
	
	@ManyToOne
	@JoinColumn (name = "id_unidadedoc")
	private UnidadeDoc unidadedoc;
	
	@OneToMany (mappedBy = "maquinas")
	private List<SistemasDeSeguranca> sistemasdeseguranca;
	
	@OneToMany (mappedBy = "maquinas")
	private List<Fotos> fotos;
}
