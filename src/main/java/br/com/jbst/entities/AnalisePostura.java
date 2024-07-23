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
@Table(name = "analise_postura")
public class AnalisePostura {
	
	@Id
	@Column(name = "id_analise_postura")
	private UUID idAnalisePostura;
	
	@Column(name = "titulo", length = 500, nullable = true)
	private String titulo;
	
	@Column(name = "pergunta", length = 2000, nullable = true)
	private String pergunta;
	
	@Column(name = "resposta", length = 500, nullable = true)
	private String resposta;
	
	@ManyToOne
	@JoinColumn (name = "id_postura")
	private Postura postura;

}
