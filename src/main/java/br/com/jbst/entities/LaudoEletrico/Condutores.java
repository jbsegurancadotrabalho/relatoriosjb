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
@Table(name = "condutores")
public class Condutores {
	

	@Id
	@Column(name = "id_condutores")
	private UUID idCondutores;
	
    @Column(name = "tipos_condutores", length = 500, nullable = false)
	private String tipos_condutores;
    
    @Column(name = "condicoes_condutores", length = 500, nullable = false)
	private String condicoes_condutores;
	
    @ManyToOne
    @JoinColumn(name = "id_inspecao")
	private Inspecao inspecao;
    
    @OneToMany (mappedBy = "condutores")
    private List<Perguntas> perguntas;
}
