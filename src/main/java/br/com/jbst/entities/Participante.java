package br.com.jbst.entities;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "participante")
public class Participante {

	
	@Id
	@Column(name = "id_participante")
	private UUID idParticipante;
	
	// Campo 1
	@Column(name = "empresa", length = 20, nullable = false)
	private String empresa;

	// Campo 2
	@Column(name = "cnpj", length = 100, nullable = false)
	private String cnpj;

	// Campo 3
	@Column(name = "nome", length = 100, nullable = false)
	private String nome;

	// Campo 4
	@Column(name = "email", length = 100, nullable = false)
	private String email;

	// Campo 5
	@Column(name = "whatsapp", length = 100, nullable = false)
	private String whatsapp;
	
	// Campo 6
	@Column(name = "funcao", length = 100, nullable = false)
	private String funcao;

	// Campo 7
	@Column(name = "segmento", length = 100, nullable = false)
	private String segmento;
	
	@ManyToOne
	@JoinColumn(name = "id_evento")
	private Evento evento;
}
