package br.com.jbst.entities;


import java.util.UUID;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;


@Data
@Entity
@Table(name = "avaliacoes_credenciados")
public class AvaliacoesCredenciados {

	@Id
	@Column(name = "idAvaliacoesCredenciados")
	private UUID idAvaliacoesCredenciados;
		
	@Column(name = "nome_avaliacoes", length = 500, nullable = true)
	private String nome_avaliacoes;

	
	@Column(name = "valorCredenciado", length = 100, nullable = false)
	private String valorCredenciado;
	
	@Column(name = "valorJB", length = 100, nullable = false)
	private String valorJB;
	
	@ManyToOne
	@JoinColumn (name = "id_avaliacoes")
	private Avaliacoes avaliacoes;
	
	@ManyToOne
	@JoinColumn(name = "id_credenciados")
	private Credenciados credenciados;
	
}
