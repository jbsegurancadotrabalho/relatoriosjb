package br.com.jbst.entities;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
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
@Table(name = "empresa_doc")
public class EmpresaDoc {

	@Id
	@Column(name = "idempresa_doc")
	private UUID idEmpresa_doc;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "datahoracriacao", nullable = false)
	private Instant dataHoraCriacao;

	@Column(name = "razaosocial", length = 100, nullable = false)
	private String razaosocial;

	@Column(name = "nomefantasia", length = 20, nullable = false)
	private String nomefantasia;

	@Column(name = "cnpj", length = 100, nullable = false)
	private String cnpj;

	// Campo 6
	@Column(name = "status", length = 100, nullable = false)
	private String status;

	// Campo 7
	@Column(name = "inscricaoestadual", length = 100, nullable = true)
	private String inscricaoestadual;

	// Campo 8
	@Column(name = "inscricaomunicipal", length = 100, nullable = true)
	private String inscricaomunicipal;

	// Campo 9
	@Column(name = "responsavel_sistema", length = 100, nullable = true)
	private String responsavel_sistema;

	// Campo 10
	@Column(name = "email_usuario", length = 100, nullable = true)
	private String email_usuario;

	// Campo 11
	@Column(name = "senha_sistema", length = 100, nullable = true)
	private String senha_sistema;

	// Campo 12
	@Column(name = "telefone_responsavel", length = 100, nullable = true)
	private String telefone_responsavel;

	// Campo 13
	@Column(name = "assinatura")
	private byte[] logo;

	@OneToMany(mappedBy = "empresadoc")
	private List<UnidadeDoc> unidadedoc;

	@OneToOne
	@JoinColumn(name = "id_empresa")
	private Empresa empresa;

	@OneToMany(mappedBy = "empresadoc")
	private List<Cnae> cnaes;

}
