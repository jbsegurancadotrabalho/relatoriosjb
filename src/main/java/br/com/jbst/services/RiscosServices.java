package br.com.jbst.services;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.jbst.DTO.GetRiscosDTO;
import br.com.jbst.DTO.PostRiscosDTO;
import br.com.jbst.DTO.PostRiscosIncluirFuncaoEspecificaDTO;
import br.com.jbst.DTO.PutRiscosDTO;
import br.com.jbst.entities.Riscos;
import br.com.jbst.entities.RiscosEntity;
import br.com.jbst.repositories.modulo1.IFuncaoEspecificaRepository;
import br.com.jbst.repositories.modulo2.IFuncaoDocRepository;
import br.com.jbst.repositories.modulo2.IRiscosEntityRepository;
import br.com.jbst.repositories.modulo2.IRiscosRepository;
import jakarta.transaction.Transactional;

@Service
public class RiscosServices {

	@Autowired
	IRiscosRepository iriscosRepository;

	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	IRiscosEntityRepository IRiscosEntityRepository;
	
	@Autowired
	IFuncaoDocRepository ifuncaoDocRepository;
	
	@Autowired
	IFuncaoEspecificaRepository IFuncaoEspecificaRepository;
	
	public GetRiscosDTO criarRiscos(PostRiscosDTO  dto) throws Exception {
	    try {
	    	RiscosEntity riscos = modelMapper.map(dto, RiscosEntity.class);
	        
	        // Definindo um novo ID para o exame
	    	riscos.setIdRisco(UUID.randomUUID());
			riscos.setFuncaodoc(ifuncaoDocRepository.findById(dto.getIdFuncaoDoc()).orElse(null));

	        // Salvando o exame com o novo ID
	        RiscosEntity novoRisco = IRiscosEntityRepository.save(riscos);
	        
	        // Mapeando o exame para o DTO de retorno
	        return modelMapper.map(novoRisco, GetRiscosDTO.class);
	    } catch (Exception e) {
	        throw new Exception("Erro ao criar Riscos: " + e.getMessage());
	    }
	}
	
	public GetRiscosDTO criarRiscosIncluirFuncaoEspecifica(PostRiscosIncluirFuncaoEspecificaDTO  dto) throws Exception {
	    try {
	    	RiscosEntity riscos = modelMapper.map(dto, RiscosEntity.class);
	        
	        // Definindo um novo ID para o exame
	    	riscos.setIdRisco(UUID.randomUUID());
			riscos.setFuncaoespecifica(IFuncaoEspecificaRepository.findById(dto.getIdFuncaoEspecifica()).orElse(null));
	        // Salvando o exame com o novo ID
	        RiscosEntity novoRisco = IRiscosEntityRepository.save(riscos);
	        
	        // Mapeando o exame para o DTO de retorno
	        return modelMapper.map(novoRisco, GetRiscosDTO.class);
	    } catch (Exception e) {
	        throw new Exception("Erro ao criar Riscos: " + e.getMessage());
	    }
	}


	public GetRiscosDTO atualizarRiscos(PutRiscosDTO  dto) throws Exception {
	    try {
	    	RiscosEntity riscos = modelMapper.map(dto, RiscosEntity.class);
	        

	        // Salvando o exame com o novo ID
	        RiscosEntity novoRisco = IRiscosEntityRepository.save(riscos);
	        
	        // Mapeando o exame para o DTO de retorno
	        return modelMapper.map(novoRisco, GetRiscosDTO.class);
	    } catch (Exception e) {
	        throw new Exception("Erro ao criar Riscos: " + e.getMessage());
	    }
	}
	
	public GetRiscosDTO buscarPorId1(UUID idRisco) {
		return iriscosRepository.buscarPorId(idRisco);
	}

	public GetRiscosDTO buscarPorId(UUID idRisco) {
        // Busca o risco pelo ID
        Optional<Riscos> optionalRisco = iriscosRepository.findById(idRisco);

        // Verifica se o risco foi encontrado
        if (optionalRisco.isPresent()) {
            // Mapeia o risco para DTO e retorna
            return modelMapper.map(optionalRisco.get(), GetRiscosDTO.class);
        } else {
            // Se o risco não foi encontrado, retorna null ou lança uma exceção
            // Aqui, vamos lançar uma exceção para indicar que o risco não foi encontrado
            throw new NoSuchElementException("Risco não encontrado com o ID fornecido: " + idRisco);
        }
    }
	
	// Método para buscar todas as medições de quadros
		public List<GetRiscosDTO> buscarTodosRiscos() {
			List<RiscosEntity> todosRiscos = IRiscosEntityRepository.findAll();
			return todosRiscos.stream().map(riscos -> modelMapper.map(riscos, GetRiscosDTO.class))
					.collect(Collectors.toList());

		}
		
	@Transactional
	public List<GetRiscosDTO> buscarCamposEspecificos() {
		return iriscosRepository.buscarCamposEspecificos();
	}

	private GetRiscosDTO convertToDto(Riscos risco) {
		return modelMapper.map(risco, GetRiscosDTO.class);
	}

	 @Transactional
	    public void excluirRiscoPorId(UUID idRisco) {
	        iriscosRepository.excluirRiscoPorId(idRisco);
	    }
}
