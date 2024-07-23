package br.com.jbst.services;

import java.time.Instant;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.jbst.DTO.GetFuncaoDocDTO;
import br.com.jbst.DTO.PostFuncaoDocDTO;
import br.com.jbst.DTO.PutFuncaoDocDTO;
import br.com.jbst.DTO.Funcionarios.Documentos.GetFuncaoDoc1DTO;
import br.com.jbst.entities.Funcao;
import br.com.jbst.entities.FuncaoDoc;
import br.com.jbst.entities.FuncaoDocEntity;
import br.com.jbst.repositories.modulo1.ICenarioRepository;
import br.com.jbst.repositories.modulo1.ICursoDeNrsRepository;
import br.com.jbst.repositories.modulo1.IEPIRepository;
import br.com.jbst.repositories.modulo1.IExamesRepository;
import br.com.jbst.repositories.modulo1.IFuncaoDocEntityRepository;
import br.com.jbst.repositories.modulo1.IMaquinasRepository;
import br.com.jbst.repositories.modulo2.IFuncaoDocRepository;
import br.com.jbst.repositories.modulo2.IFuncaoRepository;
import br.com.jbst.repositories.modulo2.IPerigoRepository;
import br.com.jbst.repositories.modulo2.IRiscosRepository;

@Service
public class FuncaoDocServices {

	@Autowired
	IFuncaoDocEntityRepository ifuncaoDocEntityRepository;

	@Autowired
	IExamesRepository iexamesRepository;

	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	IFuncaoDocRepository funcaoDocRepository;

	@Autowired
	IRiscosRepository iriscosRepository;

	@Autowired
	ICursoDeNrsRepository icursoDeNrsRepository;

	@Autowired
	ICenarioRepository icenarioRepository;

	@Autowired
	IFuncaoRepository funcaoRepository;

	@Autowired
	private IMaquinasRepository maquinasRepository;

	@Autowired
	IEPIRepository iepiRepository;

	@Autowired
	IPerigoRepository iperigorepository;


	public GetFuncaoDocDTO criarFuncaoDoc(PostFuncaoDocDTO dto) {
		// Verificar se o ID da função é nulo ou inválido
		UUID idFuncao = dto.getIdFuncao();
		if (idFuncao == null) {
			throw new IllegalArgumentException("O ID da função é nulo ou inválido.");
		}

		// Verificar se a função existe
		Funcao funcao = funcaoRepository.findById(idFuncao).orElseThrow(
				() -> new IllegalArgumentException("Função não encontrada com o ID fornecido: " + idFuncao));

		// Mapear o DTO para a entidade FuncaoDoc
		FuncaoDocEntity funcaoDoc = modelMapper.map(dto, FuncaoDocEntity.class);

		// Definir o ID da FuncaoDoc manualmente
		funcaoDoc.setIdFuncaoDoc(UUID.randomUUID());
		funcaoDoc.setFuncao(funcao);

		// Definir a data e hora de criação
		funcaoDoc.setDataHoraCriacao(Instant.now());

		// Salvar a entidade FuncaoDoc
		FuncaoDocEntity savedFuncaoDoc = ifuncaoDocEntityRepository.save(funcaoDoc);

		// Retornar o DTO mapeado
		return modelMapper.map(savedFuncaoDoc, GetFuncaoDocDTO.class);
	}

	public GetFuncaoDocDTO editarFuncaoDoc(PutFuncaoDocDTO dto) throws Exception {
		try {
			FuncaoDocEntity funcaoDoc = modelMapper.map(dto, FuncaoDocEntity.class);
			funcaoDoc.setDataHoraCriacao(Instant.now());
			ifuncaoDocEntityRepository.save(funcaoDoc);
			return modelMapper.map(funcaoDoc, GetFuncaoDocDTO.class);
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}



	public GetFuncaoDocDTO buscarFuncaoDocPorId(UUID id) {
		Optional<FuncaoDoc> optionalFuncaoDoc = funcaoDocRepository.findById(id);
		if (optionalFuncaoDoc.isPresent()) {
			FuncaoDoc funcaoDoc = optionalFuncaoDoc.get();
			return modelMapper.map(funcaoDoc, GetFuncaoDocDTO.class);
		} else {
			return null; // ou lançar uma exceção, dependendo do seu cenário
		}
	}

	public List<GetFuncaoDocDTO> getAllFuncoesDoc() throws Exception {
		List<FuncaoDoc> funcoes = funcaoDocRepository.findAll();
		return mapToDTOList(funcoes);
	}

	private List<GetFuncaoDocDTO> mapToDTOList(List<FuncaoDoc> funcoes) {
		return modelMapper.map(funcoes, new TypeToken<List<GetFuncaoDocDTO>>() {
		}.getType());
	}
	
	public GetFuncaoDocDTO excluirFuncaoDoc(UUID id) throws Exception {
		try {
			// Verificar se a FuncaoDoc existe antes de excluí-la
			Optional<FuncaoDoc> optionalFuncaoDoc = funcaoDocRepository.findById(id);
			if (optionalFuncaoDoc.isEmpty()) {
				throw new Exception("FuncaoDoc não encontrada com o ID fornecido: " + id);
			}

			// Excluir a FuncaoDoc
			funcaoDocRepository.deleteById(id);

			// Retornar o DTO mapeado da FuncaoDoc excluída
			return modelMapper.map(optionalFuncaoDoc.get(), GetFuncaoDocDTO.class);
		} catch (Exception e) {
			// Lidar com exceções específicas de acesso a dados, como falha na conexão com o
			// banco de dados
			throw new Exception("Erro ao acessar o banco de dados", e);
		}
	}

	
	
	
	  public List<GetFuncaoDoc1DTO> consultarFuncaodocGerarRelatorio() throws Exception {
			List<FuncaoDoc> funcao = funcaoDocRepository.findAll();
			List<GetFuncaoDoc1DTO> lista = modelMapper.map(funcao, new TypeToken<List<GetFuncaoDoc1DTO>>() {
			}.getType());
			return lista;
		}
	    
	

}
