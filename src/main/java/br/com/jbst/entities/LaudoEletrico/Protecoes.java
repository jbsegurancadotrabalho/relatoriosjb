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
@Table(name = "protecoes")
public class Protecoes {

	@Id
	@Column(name = "id_protecoes")
	private UUID id_protecoes;
	
    @Column(name = "tipos_protecoes", length = 500, nullable = false)
	private String tipos_protecoes;
    
    @Column(name = "condicoes_protecoes", length = 500, nullable = false)
	private String condicoes_protecoes;

    @ManyToOne 
    @JoinColumn (name = "id_inspecao")
    private Inspecao inspecao;
	
    @OneToMany(mappedBy = "protecoes")
	private List<Perguntas> perguntas;	
	
}
