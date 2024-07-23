
package br.com.jbst.entities;

import java.util.List;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
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
public class Perigo {
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

	@OneToMany(mappedBy = "perigo")
	private List<Danos> danos;

	@OneToMany(mappedBy = "perigo")
	private List<MedidasDeControle> medidasdecontrole;

	@OneToOne(mappedBy = "perigo")
	private Potencial_Severidade_Danos potencial_severidade_danos;

	@OneToOne(mappedBy = "perigo")
	private ProbabilidadeOcorrencia probabilidadedeocorrencia;

	@OneToOne(mappedBy = "perigo")
	private Nivel_Severidade nivel_severidade;
	
	@ManyToOne
	@JoinColumn(name = "id_funcao_doc" , nullable = true)
	private FuncaoDoc funcaodoc;


}
