package br.com.jbst.entities;
import java.time.Instant;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;

import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.Data;

@Data
@Entity
@Table(name = "unidade_doc")
public class UnidadeDocEntities {

	// Campo 1
		@Id
		@Column(name = "idunidadeDoc")
		private UUID idunidadeDoc;
		// Campo 2
		@Temporal(TemporalType.TIMESTAMP)
		@Column(name = "datahoracriacao", nullable = false)
		private Instant dataHoraCriacao;

		// Campo 3
		@Column(name = "nome_especifico_unidade", length = 100, nullable = true)
		private String nomeEspecificoUnidade;
		
		// Campo 3
		@Column(name = "unidadeDoc", length = 100, nullable = false)
		private String nomefantasia;

		// Campo 4
		@Column(name = "cnpj", length = 100, nullable = false)
		private String cnpj;

		@Column(name = "horario_empresa", length = 100, nullable = false)
		private String horario_empresa;

		// Campo 5
		@Column(name = "logoempresa", nullable = true)
		private byte[] logoempresa;

		// Campo 6
		@Column(name = "logofornecedor", nullable = true)
		private byte[] logofornecedor;

		

		@ManyToOne
		@JoinColumn(name = "id_empresadoc")  // Renomeado para corresponder ao nome do campo em EmpresaDoc
		private EmpresaDoc empresadoc;
		
		
		@OneToOne
		@JoinColumn(name = "id_endereco")
		private Endereco endereco;

	
	}
