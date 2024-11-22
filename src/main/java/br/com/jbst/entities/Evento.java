package br.com.jbst.entities;

import java.time.Instant;
import java.util.List;
import java.util.UUID;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
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
@Table(name = "evento")
public class Evento {
	
	@Id
	@Column(name = "idEvento")
	private UUID idEvento;

	@Column(name = "numeroevento", nullable = true)
	private Integer numeroevento;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "dia_hora", nullable = true)
	private Instant dia_hora;

    @Column(name = "nome_evento", length = 500, nullable = true)
    private String nome_evento;
    
    @Column(name = "descricao", length = 500, nullable = true)
    private String descricao;
    
    @Column(name = "carga_horaria", length = 500, nullable = true)
    private String carga_horaria;
    
    @Column(name = "organizadores", length = 500, nullable = true)
    private String organizadores;
    
    @Column(name = "palestrante", length = 100, nullable = true)
    private String palestrante;
    
    @Column(name = "valor", length = 50, nullable = true)
    private String valor;
    
    @Column(name = "status", length = 50, nullable = true)
    private String status;
    
    @Column(name = "local", length = 50, nullable = true)
    private String local;
    
    @OneToMany(mappedBy = "evento")
    private List<Participante> participante;

}
