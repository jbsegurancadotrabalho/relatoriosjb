package br.com.jbst.services;

import java.util.List;

import java.util.UUID;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.jbst.DTO.GetPerigoDTO;
import br.com.jbst.DTO.GetPerigoFuncaoDTO;
import br.com.jbst.DTO.PostPerigoDTO;
import br.com.jbst.DTO.PostPerigoIncluirFuncaoEspecificaCenarioDTO;
import br.com.jbst.DTO.PutPerigoDTO;
import br.com.jbst.entities.PerigoEntity;
import br.com.jbst.repositories.modulo1.IPerigoEntityRepository;
import br.com.jbst.repositories.modulo2.IFuncaoDocRepository;
import br.com.jbst.repositories.modulo2.IPerigoRepository;
import br.com.jbst.repositories.modulo1.IFuncaoEspecificaRepository;

@Service
public class PerigoServices {

	@Autowired
	IPerigoEntityRepository iperigorepository;
	
	@Autowired
	IPerigoRepository Iperigorepository;
	
	@Autowired
	IFuncaoDocRepository ifuncaoDocRepository;

	@Autowired
	IFuncaoEspecificaRepository IFuncaoEspecificaRepository;
	
	@Autowired
	ModelMapper modelMapper;
	
	 public GetPerigoDTO criarPerigo(PostPerigoDTO dto) {
	        // Mapear o DTO para a entidade Perigo
		 PerigoEntity perigo = modelMapper.map(dto, PerigoEntity.class);
	        
	        // Definir o ID do perigo manualmente (ou de outra forma, como preferir)
	        perigo.setIdPerigo(UUID.randomUUID());
			perigo.setFuncaodoc(ifuncaoDocRepository.findById(dto.getIdFuncaoDoc()).orElse(null));

	        // Salvar o perigo no banco de dados
	        perigo = iperigorepository.save(perigo);
	        
	        // Retornar o DTO mapeado
	        return modelMapper.map(perigo, GetPerigoDTO.class);
	    }
	 
	 public GetPerigoDTO criarPerigoFuncaoEspecifica(PostPerigoIncluirFuncaoEspecificaCenarioDTO dto) {
	        // Mapear o DTO para a entidade Perigo
		 PerigoEntity perigo = modelMapper.map(dto, PerigoEntity.class);
	        
	        // Definir o ID do perigo manualmente (ou de outra forma, como preferir)
	        perigo.setIdPerigo(UUID.randomUUID());
			perigo.setFuncaoespecifica(IFuncaoEspecificaRepository.findById(dto.getIdFuncaoEspecifica()).orElse(null));

	        // Salvar o perigo no banco de dados
	        perigo = iperigorepository.save(perigo);
	        
	        // Retornar o DTO mapeado
	        return modelMapper.map(perigo, GetPerigoDTO.class);
	    }
	 
	 public GetPerigoDTO editarPerigo(PutPerigoDTO dto) {
	        // Verifica se o DTO contém um ID válido
	        UUID idPerigo = dto.getIdPerigo();
	        if (idPerigo == null) {
	            return null; // Retorna null se o ID não for fornecido
	        }

	        // Busca o perigo no banco de dados com base no ID fornecido no DTO
	        PerigoEntity perigoExistente = iperigorepository.findById(idPerigo).orElse(null);

	        // Se o perigo não for encontrado, retorna null
	        if (perigoExistente == null) {
	            return null;
	        }

	        // Atualiza os dados do perigo existente com base nas informações fornecidas no DTO
	        perigoExistente.setNomePerigo(dto.getNomePerigo());
	        perigoExistente.setCausasPerigo(dto.getCausasPerigo());
	        perigoExistente.setConsequencias_perigo(dto.getConsequencias_perigo());
	        perigoExistente.setMedidasPreventivas(dto.getMedidasPreventivas());
	        perigoExistente.setClassificacao(dto.getClassificacao());

	        // Salva o perigo atualizado no banco de dados
	        PerigoEntity perigoAtualizado = iperigorepository.save(perigoExistente);

	        // Retorna o perigo atualizado convertido para DTO
	        return modelMapper.map(perigoAtualizado, GetPerigoDTO.class);
	    }
	 
	 
	 
	 public GetPerigoDTO buscarPorId(UUID idPerigo) {
	        // Busca o perigo no banco de dados com base no ID fornecido
	        return Iperigorepository.findById(idPerigo)
	            .map(perigo -> modelMapper.map(perigo, GetPerigoDTO.class))
	            .orElse(null);
	    }

	    public List<GetPerigoDTO> buscarTodos() {
	        // Busca todos os perigos no banco de dados
	        List<PerigoEntity> perigos = iperigorepository.findAll();
	        
	        // Converte a lista de perigos para uma lista de DTOs
	        return perigos.stream()
	            .map(perigo -> modelMapper.map(perigo, GetPerigoDTO.class))
	            .collect(Collectors.toList());
	    }

	    
	    public List<GetPerigoFuncaoDTO> buscarPerigosFuncao() {
	        // Busca todos os perigos no banco de dados
	        List<PerigoEntity> perigos = iperigorepository.findAll();
	        
	        // Converte a lista de perigos para uma lista de DTOs
	        return perigos.stream()
	            .map(perigo -> modelMapper.map(perigo, GetPerigoFuncaoDTO.class))
	            .collect(Collectors.toList());
	    }
	    
	    public boolean excluirPorId(UUID idPerigo) {
	        // Verifica se o perigo existe no banco de dados
	        if (iperigorepository.existsById(idPerigo)) {
	            // Exclui o perigo do banco de dados e retorna true
	            iperigorepository.deleteById(idPerigo);
	            return true;
	        } else {
	            // Retorna false se o perigo não for encontrado
	            return false;
	        }
	    }
	
	}
