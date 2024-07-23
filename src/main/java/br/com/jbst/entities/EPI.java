package br.com.jbst.entities;

import java.util.List;

import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "epi")
public class EPI {

	@Getter
	@Setter
	@Id
	@Column(name = "id_epi")
	private UUID idEpi;

	@Column(name = "nome_epi", length = 100, nullable = false)
	private String nome_epi;

	@Column(name = "descricao_epi", length = 500, nullable = false)
	private String descricao_epi;

	@Column(name = "ca", length = 100, nullable = false)
	private String ca;
	
	@ManyToOne 
	@JoinColumn(name = "id_funcaodoc")
	private FuncaoDoc funcaodoc;
	
	@ManyToOne 
	@JoinColumn(name = "id_funcao_especifica")
	private FuncaoEspecifica funcaoespecifica;

}
