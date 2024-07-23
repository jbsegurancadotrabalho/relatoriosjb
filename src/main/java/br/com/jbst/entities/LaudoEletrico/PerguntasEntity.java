package br.com.jbst.entities.LaudoEletrico;

import java.util.List;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "perguntas")
public class PerguntasEntity {
	@Id
	@Column(name = "id_perguntas")
	private UUID idPerguntas;
	
    @Column(name = "pergunta", length = 500, nullable = false)
	private String pergunta;
}
