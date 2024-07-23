package br.com.jbst.entities.LaudoEletrico;

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
@Table(name = "resposta")
public class Resposta {

	@Id
	@Column(name = "id_resposta")
	private UUID id_resposta;
	
    @Column(name = "Resposta", length = 500, nullable = false)
	private String Resposta;
    
    @ManyToOne
    @JoinColumn ( name = "id_perguntas", nullable = true)
    private Perguntas perguntas;
}
