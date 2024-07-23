package br.com.jbst.entities;

import java.time.Instant;


import java.util.List;
import java.util.UUID;


import br.com.jbst.enuns.Credenciados.Status;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.Data;

@Data
@Entity
@Table(name = "credenciados")
public class Credenciados {
	
	// Campo 1
	@Id
	@Column(name = "idCredenciado")
	private UUID idCredenciado;	

	//Campo 2
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "datahoracriacao", nullable = false) 
	private Instant dataHoraCriacao;

	//Campo 3
	@Column(name = "razaosocial", length = 100, nullable = false)
	private String razaosocial;

	//Campo 4
	@Column(name = "nome_credenciado", length = 20, nullable = false)
	private String nomeCredenciado;

	//Campo 5
	@Column(name = "cnpj", length = 100, nullable = false)
	private String cnpj;

	//Campo 6
	 @Enumerated(EnumType.STRING)
	 private Status status;

	//Campo 7
	@Column(name = "inscricaomunicipal", length = 100, nullable = true)
	private String inscricaomunicipal;

	//Campo 8
	@Column(name = "responsavel_sistema", length = 100, nullable = true)
	private String responsavel_sistema;

	//Campo 9
	@Column(name = "email_usuario", length = 100, nullable = true)
	private String email_usuario;

	//Campo 10
	@Column(name = "senha_sistema", length = 100, nullable = true)
	private String senha_sistema;

	//Campo 11	@Column(name = "telefone_responsavel", length = 100, nullable = true)
	private String telefone_responsavel;

	//Campo 12	
	@Column(name = "assinatura")
	private byte[] logo;
	

	@OneToMany(mappedBy = "credenciados") // 1 Empresa tem muitos Pedidos
	private List<ExamesCredenciados> examescredenciados;
	
	@OneToMany(mappedBy = "credenciados") // 1 Empresa tem muitos Pedidos
	private List<Agenda> agenda;

	@OneToMany(mappedBy = "credenciados") // 1 Empresa tem muitos Pedidos
	private List<ProfissionalSaude> profissionalsaude;	
	
	@OneToMany(mappedBy = "credenciados") // 1 Empresa tem muitos Pedidos
	private List<AvaliacoesCredenciados> avaliacoescredenciados;
	
	@OneToMany(mappedBy = "credenciados") // 1 Empresa tem muitos Pedidos
	private List<Cursos_Credenciados> cursoscredenciados;
	
	@OneToOne
	@JoinColumn(name = "id_endereco")
	private Endereco endereco;
	
	@OneToMany (mappedBy = "credenciados")
	private List<Cursos_Credenciados> cursos_credenciados;
}
