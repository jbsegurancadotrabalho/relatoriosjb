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
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.Data;

@Data
@Entity
@Table(name = "profissional_saude")
public class ProfissionalSaude {
	@Id // Campo 1
	@Column(name = "id_profissionalsaude")
	private UUID id_profissionalsaude;

	// Campo 2
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "datahoracriacao", nullable = false)
	private Instant dataHoraCriacao;

	// Campo 3
	@Column(name = "nome_profissionalsaude", length = 100, nullable = false)
	private String nome_profissionalsaude;

	// Campo 4
	@Column(name = "rg", length = 100, nullable = true)
	private String rg;

	// Campo 5
	@Column(name = "cpf", length = 100, nullable = true)
	private String cpf;

	// Campo 6
	@Column(name = "telefone_1", length = 100, nullable = false)
	private String telefone_1;

	// Campo 7
	@Column(name = "telefone_2", length = 100, nullable = true)
	private String telefone_2;

	// Campo 8
	@Column(name = "email", length = 100, nullable = true)
	private String email;

	// Campo 9
	@Column(name = "senha_sistema", length = 100, nullable = true)
	private String senha_sistema;

	// Campo 10
	@Column(name = "assinatura", nullable = true)
	private byte[] assinatura;
	
	@ManyToOne
	@JoinColumn(name = "id_credenciados", nullable = true)
	private Credenciados credenciados;
	
	@OneToMany(mappedBy = "profissionalsaude") 
	private List<FormacaoSaude> formacaosaude;
	
	@OneToOne (mappedBy= "profissionalsaude")
	private Agenda agenda;
	
	@OneToOne
	@JoinColumn(name = "id_endereco", nullable = true)
	private Endereco endereco;
}
