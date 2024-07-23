package br.com.jbst.services;


import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.jbst.DTO.GetAvaliacoesDTO;
import br.com.jbst.DTO.PostAvaliacoesDTO;
import br.com.jbst.DTO.PutAvaliacoesDTO;
import br.com.jbst.entities.Avaliacoes;
import br.com.jbst.repositories.modulo1.IAvaliacoesRepository;

@Service
public class AvaliacoesService {

    @Autowired
    IAvaliacoesRepository avaliacoesRepository;

    @Autowired
    private ModelMapper modelMapper;

    public GetAvaliacoesDTO criarAvaliacoes(PostAvaliacoesDTO dto) {
        // Mapear o DTO para a entidade Avaliacoes
        Avaliacoes avaliacoes = modelMapper.map(dto, Avaliacoes.class);
        
        // Definir o ID da avaliação manualmente
        avaliacoes.setIdAvaliacoes(UUID.randomUUID());

        // Salvar a entidade Avaliacoes
        Avaliacoes savedAvaliacoes = avaliacoesRepository.save(avaliacoes);

        // Retornar o DTO mapeado da entidade salva
        return modelMapper.map(savedAvaliacoes, GetAvaliacoesDTO.class);
    }

    public  GetAvaliacoesDTO editarAvaliacoes(PutAvaliacoesDTO dto) throws Exception {
		try {
					 
			Avaliacoes avaliacoes = modelMapper.map(dto, Avaliacoes.class);
			avaliacoesRepository.save(avaliacoes);
			return modelMapper.map(avaliacoes,  GetAvaliacoesDTO.class);
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}
	    public GetAvaliacoesDTO buscarPorId(UUID id) {
	        Avaliacoes avaliacoes = avaliacoesRepository.findById(id)
	                .orElseThrow(() -> new NoSuchElementException("Avaliação não encontrada com o ID: " + id));
	        return modelMapper.map(avaliacoes, GetAvaliacoesDTO.class);
	    }
	
	    public List<GetAvaliacoesDTO> buscarTodasAvaliacoes() {
	        List<Avaliacoes> avaliacoesList = avaliacoesRepository.findAll();
	        return avaliacoesList.stream()
	                .map(avaliacoes -> modelMapper.map(avaliacoes, GetAvaliacoesDTO.class))
	                .collect(Collectors.toList());
	    }
	
	    public void excluirAvaliacoes(UUID id) {
	        Avaliacoes avaliacoes = avaliacoesRepository.findById(id)
	                .orElseThrow(() -> new NoSuchElementException("Avaliação não encontrada com o ID: " + id));
	        avaliacoesRepository.delete(avaliacoes);
	    }
	    
}
