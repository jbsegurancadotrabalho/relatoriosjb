package br.com.jbst.entities.LaudoEletrico;

import java.util.List;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "tomadas")
public class Tomadas {
	
	@Id
	@Column(name = "id_tomadas")
	private UUID idTomadas;
	
    @Column(name = "tipos_tomadas", length = 500, nullable = false)
	private String tipos_tomadas;
    
    @Column(name = "condicoes_tomadas", length = 500, nullable = false)
	private String condicoes_tomadas;
	
    @ManyToOne 
    @JoinColumn (name = "id_inspecao")
    private Inspecao inspecao;
    
    @OneToMany(mappedBy = "tomadas")
    private List<Perguntas> perguntas;

    
}
