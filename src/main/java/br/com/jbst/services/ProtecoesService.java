package br.com.jbst.services;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.jbst.entities.LaudoEletrico.Quadros_Paineis;
import br.com.jbst.repositories.modulo2.IInspecaoRepository;
import br.com.jbst.repositories.modulo2.IProtecoesRepository;
import br.com.jbst.DTO2.GetProtecoesDTO;
import br.com.jbst.DTO2.GetQuadros_PaineisDTO;
import br.com.jbst.DTO2.PostProtecoesDTO;
import br.com.jbst.DTO2.PostQuadros_PaineisDTO;
import br.com.jbst.DTO2.PutProtecoesDTO;
import br.com.jbst.DTO2.PutQuadros_PaineisDTO;
import br.com.jbst.entities.LaudoEletrico.Protecoes;

@Service
public class ProtecoesService {
	@Autowired
	private ModelMapper modelMapper;
	
	
	@Autowired
	IProtecoesRepository iprotecoesRepository;
	
	
	@Autowired
	IInspecaoRepository inspecaoRepository;
	
	
	 public GetProtecoesDTO criarProtecoes(PostProtecoesDTO dto) {
	        try {
	        	Protecoes protecoes = modelMapper.map(dto, Protecoes.class);
	        	protecoes.setId_protecoes(UUID.randomUUID());
	        	protecoes.setInspecao(inspecaoRepository.findById(dto.getIdInspecao()).orElse(null));
	        	Protecoes protecao = iprotecoesRepository.save(protecoes);
	            return modelMapper.map(protecao, GetProtecoesDTO.class);
	        } catch (Exception e) {
	            // Handle exception here
	            throw new RuntimeException("Falha ao criar Proteções", e);
	        }
	    }
	   
	   public GetProtecoesDTO editarProtecoes(PutProtecoesDTO dto) {
	        try {
	        	Protecoes protecoes = modelMapper.map(dto, Protecoes.class);
	        	protecoes.setInspecao(inspecaoRepository.findById(dto.getIdInspecao()).orElse(null));
	        	Protecoes protecao = iprotecoesRepository.save(protecoes);
	            return modelMapper.map(protecao, GetProtecoesDTO.class);
	        } catch (Exception e) {
	            // Handle exception here
	            throw new RuntimeException("Falha ao criar Proteções", e);
	        }
	    }
	   
	   public GetProtecoesDTO consultarProtecoesPorId(UUID id) {
	        Protecoes protecao = iprotecoesRepository.findById(id)
	                .orElseThrow(() -> new IllegalArgumentException("Proteções não encontradas com o id: " + id));
	        return modelMapper.map(protecao, GetProtecoesDTO.class);
	    }

	    public List<GetProtecoesDTO> consultarTodasAsProtecoes() {
	        List<Protecoes> protecoesList = iprotecoesRepository.findAll();
	        return protecoesList.stream()
	                .map(protecao -> modelMapper.map(protecao, GetProtecoesDTO.class))
	                .collect(Collectors.toList());
	    }
	    
	    public void excluirProtecoesPorId(UUID id) {
	        Protecoes protecao = iprotecoesRepository.findById(id)
	                .orElseThrow(() -> new IllegalArgumentException("Proteções não encontradas com o id: " + id));
	        iprotecoesRepository.delete(protecao);
	    }
}
