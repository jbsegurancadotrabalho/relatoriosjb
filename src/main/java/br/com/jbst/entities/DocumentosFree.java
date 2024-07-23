package br.com.jbst.entities;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "documentosfree")
public class DocumentosFree {
	
	// Campo 1
	@Id
	@Column(name = "id_documentos_free")
	private UUID idDocumentosFree;
	
	//Campo 2
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "datahoracriacao", nullable = true) 
	private Instant dataHoraCriacao;
	
	//Campo 3
	@Column(name = "razaosocial", length = 100, nullable = true)
	private String razaosocial;
	
	//Campo 5
	@Column(name = "cnpj", length = 100, nullable = true)
	private String cnpj;
	
	//Campo 5
	@Column(name = "setor", length = 100, nullable = true)
	private String setor;
	
	//Campo 5
	@Column(name = "cenario", length = 100, nullable = true)
	private String cenario;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "datainicio", nullable = true)
	private Instant datainicio;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "vigencia", nullable = true)
	private Instant vigencia;
	
	//Campo 5
	@Column(name = "endereco", length = 200, nullable = true)
	private String endereco;
	

	
	//Campo 5
	@Column(name = "funcoesenvolvidas", length = 2000, nullable = true)
	private String funcoesenvolvidas;
	
	@Column(name = "equipamentos", length = 2000, nullable = true)
	private String equipamentos;
	
	@Column(name = "responsavel", length = 100, nullable = true)
	private String responsavel;
	
	@Column(name = "emitentes", length = 1000, nullable = true)
	private String emitentes;
	
	@Column(name = "cipa", length = 500, nullable = true)
	private String cipa;
	
	@Column(name = "brigada", length = 500, nullable = true)
	private String brigada;
	
	@Column(name = "resgate", length = 500, nullable = true)
	private String resgate;
	
	@Column(name = "ambulancia", length = 500, nullable = true)
	private String ambulancia;
	
	@Column(name = "seguranca", length = 500, nullable = true)
	private String seguranca;
	
	@Column(name = "local_espaco_confinado", length = 500, nullable = true)
	private String local_espaco_confinado;
	
	@Column(name = "tipo_servico", length = 500, nullable = true)
	private String servico;
	
	@Column(name = "autorizados_vigia", length = 500, nullable = true)
	private String autorizados_vigia;
	
	@Column(name = "autorizados_supervisor", length = 500, nullable = true)
	private String autorizados_supervisor;
	
	@Column(name = "autorizados_resgate", length = 500, nullable = true)
	private String autorizados_resgate;
	
	@Column(name = "numerodocumentofree", nullable = true)
    private Integer numerodocumentofree; // Make sure this attribute exists
	
	@ManyToOne
	@JoinColumn(name = "idempresa")
	private Empresa empresa;
	
	@OneToMany (mappedBy = "documentosfree")
	private List <Etapas> etapas;

}
