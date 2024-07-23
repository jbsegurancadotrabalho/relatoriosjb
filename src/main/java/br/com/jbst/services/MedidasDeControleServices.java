package br.com.jbst.services;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.jbst.DTO.GetMedidasDeControleDTO;
import br.com.jbst.DTO.PostMedidasDeControleDTO;
import br.com.jbst.DTO.PostMedidasDeControleIncluirRiscosDTO;
import br.com.jbst.DTO.PutMedidasDeControleDTO;
import br.com.jbst.DTO.PutMedidasDeControleIncluirRiscosDTO;
import br.com.jbst.entities.MedidasDeControle;
import br.com.jbst.repositories.modulo2.IMedidasDeControleRepository;
import br.com.jbst.repositories.modulo2.IPerigoRepository;
import br.com.jbst.repositories.modulo2.IRiscosRepository;

@Service
public class MedidasDeControleServices {

	@Autowired
	private IMedidasDeControleRepository medidasDeControleRepository;
	
	@Autowired
	private IPerigoRepository perigoRepository;

	@Autowired
	private IRiscosRepository riscosRepository;
	@Autowired
	private ModelMapper modelMapper;

	public GetMedidasDeControleDTO criarMedidasDeControle(PostMedidasDeControleDTO dto) {
		MedidasDeControle medidasDeControle = modelMapper.map(dto, MedidasDeControle.class);
		medidasDeControle.setIdMedidasDeControle(UUID.randomUUID());
		// Busca e associa o perigo com base no ID fornecido, se não for nulo
		if (dto.getIdPerigo() != null) {
			perigoRepository.findById(dto.getIdPerigo()).ifPresent(medidasDeControle::setPerigo);
		}

	
		
		medidasDeControle = medidasDeControleRepository.save(medidasDeControle);

		return modelMapper.map(medidasDeControle, GetMedidasDeControleDTO.class);
	}
	
	public GetMedidasDeControleDTO criarMedidasDeControleIncluirRiscos(PostMedidasDeControleIncluirRiscosDTO dto) {
		MedidasDeControle medidasDeControle = modelMapper.map(dto, MedidasDeControle.class);
		medidasDeControle.setIdMedidasDeControle(UUID.randomUUID());
		// Busca e associa o perigo com base no ID fornecido, se não for nulo
		if (dto.getIdRisco() != null) {
			riscosRepository.findById(dto.getIdRisco()).ifPresent(medidasDeControle::setRiscos);
		}

	
		
		medidasDeControle = medidasDeControleRepository.save(medidasDeControle);

		return modelMapper.map(medidasDeControle, GetMedidasDeControleDTO.class);
	}
	
	public GetMedidasDeControleDTO editarMedidasDeControle(PutMedidasDeControleDTO dto) {
        // Verifica se o ID das medidas de controle é válido
        UUID idMedidasDeControle = dto.getIdMedidasDeControle();
        if (idMedidasDeControle == null) {
            return null;
        }

        // Busca as medidas de controle no banco de dados com base no ID fornecido
        MedidasDeControle medidasDeControleExistente = medidasDeControleRepository.findById(idMedidasDeControle)
                .orElseThrow();

        // Mapeia os dados do DTO para a entidade existente
        modelMapper.map(dto, medidasDeControleExistente); 

        // Salva as alterações
        medidasDeControleExistente = medidasDeControleRepository.save(medidasDeControleExistente);

        // Mapeia a entidade atualizada de volta para o DTO de resposta
        return modelMapper.map(medidasDeControleExistente, GetMedidasDeControleDTO.class);
    }
	
	
	
	 public GetMedidasDeControleDTO buscarPorId(UUID id) {
	        MedidasDeControle medidasDeControle = medidasDeControleRepository.findById(id)
	                .orElseThrow(() -> new NoSuchElementException("Medidas de Controle não encontradas com o ID: " + id));
	        return modelMapper.map(medidasDeControle, GetMedidasDeControleDTO.class);
	    }

	    public List<GetMedidasDeControleDTO> buscarTodasMedidas() {
	        List<MedidasDeControle> medidasDeControleList = medidasDeControleRepository.findAll();
	        return medidasDeControleList.stream()
	                .map(medidasDeControle -> modelMapper.map(medidasDeControle, GetMedidasDeControleDTO.class))
	                .collect(Collectors.toList());
	    }

	    public void excluirMedidasDeControle(UUID id) {
	        MedidasDeControle medidasDeControle = medidasDeControleRepository.findById(id)
	                .orElseThrow(() -> new NoSuchElementException(
	                        "Medidas de Controle não encontradas com o ID: " + id));
	        medidasDeControleRepository.delete(medidasDeControle);
	    }


}
