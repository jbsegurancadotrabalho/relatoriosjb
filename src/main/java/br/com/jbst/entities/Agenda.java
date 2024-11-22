package br.com.jbst.entities;

import java.time.Instant;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.Data;

@Data
@Entity
@Table(name = "agenda")
public class Agenda {

	@Id
	@Column(name = "idAgenda")
	private UUID idAgenda;

	@Column(name = "numeroagenda", nullable = true)
	private Integer numeroagenda;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "dia_hora", nullable = true)
	private Instant dia_hora;

    @Column(name = "observacoes", length = 500, nullable = true)
    private String observacoes_agenda;
    
    @Column(name = "status", length = 50, nullable = true)
    private String status;
    
	@ManyToOne
	@JoinColumn(name = "id_examescredenciados")
	private ExamesCredenciados examescredenciados;

    @JsonIgnore
	@OneToOne(mappedBy = "agenda", cascade = CascadeType.ALL, orphanRemoval = true)
	private Agendamento agendamento;


	@ManyToOne
	@JoinColumn(name = "id_profissionalsaude")
	private ProfissionalSaude profissionalsaude;

	@ManyToOne
	@JoinColumn(name = "id_credenciados")
	private Credenciados credenciados;

}
