package br.com.jbst.entities;

import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "atendimento")
public class Atendimento {
	@Id
	@Column(name = "idAtendimento")
	private UUID idAtendimento;
	 
    @Column(name = "tipo", length = 100, nullable = false)
    private String tipo;
	// Campo 6
    @Column(name = "status", length = 100, nullable = false)
    private String status;

	// Campo 7
    @Column(name = "link", length = 100, nullable = true)
    private String link;
    
    @Column(name = "profissional", length = 100, nullable = false)
    private String profissional;
    
    @Column(name = "descricao", length = 1000, nullable = false)
    private String descricao;
    
    @Column(name = "numeroatendimento", nullable = true)
    private Integer numeroatendimento;
    
	@ManyToOne
	@JoinColumn(name = "id_agendamento") 
    private Agendamento agendamento;
	
	@OneToOne(mappedBy = "atendimento")
	private Anamnese anamnese;
	
	
	@OneToOne(mappedBy = "atendimento")
	private ASO aso;
}
