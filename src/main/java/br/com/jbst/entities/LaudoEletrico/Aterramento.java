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
@Table(name = "aterramento")
public class Aterramento {
	
	
	@Id
	@Column(name = "id_aterramento")
	private UUID idAterramento;
	
    @Column(name = "tipos_aterramento", length = 500, nullable = false)
	private String tipos_aterramento;
    
    @Column(name = "condicoes_aterramento", length = 500, nullable = false)
	private String condicoesAterramento;
	
    @ManyToOne
    @JoinColumn (name = "id_inspecao")
	private Inspecao inspecao;
    
    @OneToMany (mappedBy = "aterramento")
    private List<Perguntas> perguntas;
}
