package br.com.jbst.entities;


import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "perigo")
public class PerigoEntity {
	@Getter
	@Setter
	@Id
	@Column(name = "id_perigo")
	private UUID idPerigo;

	@Column(name = "nomePerigo", length = 100, nullable = true)
	private String nomePerigo;

	@Column(name = "causasPerigo", length = 100, nullable = true)
	private String causasPerigo;

	@Column(name = "consequencias_perigo", length = 100, nullable = true)
	private String consequencias_perigo;

	@Column(name = "medidasPreventivas", length = 100, nullable = true)
	private String medidasPreventivas;

	@Column(name = "classificacao", length = 100, nullable = true)
	private String classificacao;
	
	
	@ManyToOne
	@JoinColumn(name = "id_funcao_doc" , nullable = true)
	private FuncaoDoc funcaodoc;
	
	@ManyToOne
	@JoinColumn(name = "id_funcaoespecifica" , nullable = true)
	private FuncaoEspecifica funcaoespecifica;

}
