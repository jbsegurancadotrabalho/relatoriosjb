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
@Table(name = "procedimentos")
public class Procedimentos {

	@Id
	@Column(name = "id_procedimento")
	private UUID idProcedimentos;
	
    @Column(name = "nome_procedimentos", length = 1000, nullable = false)
	private String nome_procedimentos;
    
    @Column(name = "descricao_procedimentos", length = 1000, nullable = false)
	private String descricao_procedimentos;
	
    @ManyToOne
    @JoinColumn(name = "id_inspecao")
    private Inspecao inspecao;
    
    @OneToMany (mappedBy = "procedimentos")
    private List<Perguntas> perguntas;
		
}
