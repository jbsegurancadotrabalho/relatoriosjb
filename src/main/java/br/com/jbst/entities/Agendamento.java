package br.com.jbst.entities;

import java.util.List;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "agendamento")
public class Agendamento {

	@Id
	@Column(name = "idAgendamento")
	private UUID idAgendamento;
	
	@Column(name = "venda", length = 100, nullable = true)
	private String venda;
	 
	// Campo 6
    @Column(name = "status", length = 100, nullable = false)
    private String status;

	// Campo 7
    @Column(name = "tipo_de_pagamento", length = 100, nullable = false)
    private String tipo_de_pagamento;
    
	// Campo 8
    @Column(name = "observacoes", length = 1000, nullable = true)
    private String observacoes;
    
	// Campo 5
    @Column(name = "numeroagendamento", nullable = true)
    private Integer numeroagendamento;
    
    @ManyToOne
    @JoinColumn(name = "id_pessoafisica", nullable = true)	
	private PessoaFisica pessoafisica;	
	
	@ManyToOne
	@JoinColumn(name = "id_funcionario", nullable = true)
	private Funcionario funcionario;
	
    @JsonBackReference
	@OneToOne
	@JoinColumn(name = "id_agenda", unique = true) // Adicione a restrição de unicidade aqui
	private Agenda agenda;
    
    @OneToMany(mappedBy = "agendamento", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Atendimento> atendimento;

	
}
