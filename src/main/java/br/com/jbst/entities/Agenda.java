package br.com.jbst.entities;

import java.time.Instant;
import java.util.UUID;


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
    
	@OneToOne
	@JoinColumn(name = "id_examescredenciados")
	private ExamesCredenciados examescredenciados;

	@OneToOne(mappedBy = "agenda")
	private Agendamento agendamento;

	@OneToOne
	@JoinColumn(name = "id_profissionalsaude")
	private ProfissionalSaude profissionalsaude;

	@ManyToOne
	@JoinColumn(name = "id_credenciados")
	private Credenciados credenciados;

}
