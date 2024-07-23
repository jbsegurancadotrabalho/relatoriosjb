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
@Table(name = "luminarias")
public class Luminarias {

	@Id
	@Column(name = "id_luminarias")
	private UUID idLuminarias;
	
    @Column(name = "tiposLuminarias", length = 500, nullable = false)
	private String tiposLuminarias;
	
    @Column(name = "condicoesLuminarias", length = 500, nullable = false)
	private String condicoesLuminarias;
	
    @ManyToOne
    @JoinColumn(name = "id_inspecao")
    private Inspecao inspecao;
    
    @OneToMany (mappedBy = "luminarias")
    private List<Perguntas> perguntas;
}