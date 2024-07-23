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
@Table(name = "transformadores")
public class Transformadores {

	
    @Id
    @Column(name = "id_transformadores")
    private UUID idTransformadores;
	
    @Column(name = "nomeTransformador", length = 100, nullable = false)
	private String nomeTransformador;
    
    @Column(name = "descricaoTransformador", length = 100, nullable = false)
 	private String descricaoTransformador;
    
    @ManyToOne 
    @JoinColumn (name = "id_inspecao")
    private Inspecao inspecao;
}
