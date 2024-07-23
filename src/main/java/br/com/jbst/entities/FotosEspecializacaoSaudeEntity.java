package br.com.jbst.entities;

import java.time.Instant;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;




@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "fotos")
public class FotosEspecializacaoSaudeEntity {
	@Id
	@Column(name = "id_fotos")
	private UUID idFotos;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "datahoracriacao", nullable = false)
	private Instant dataHoraCriacao;

	@Column(name = "nome_foto", length = 100, nullable = false)
	private String nomeFoto;

	@Column(name = "foto", nullable = true)
	private byte[] foto;
	
	@ManyToOne
	@JoinColumn (name = "id_especializacao", nullable = true)
	private EspecializacaoSaude especializacaosaude;
	
}
