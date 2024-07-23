package br.com.jbst.services;

import java.util.List;

import java.util.UUID;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.jbst.DTO2.GetQuadros_PaineisDTO;
import br.com.jbst.DTO2.PostQuadros_PaineisDTO;
import br.com.jbst.DTO2.PutQuadros_PaineisDTO;
import br.com.jbst.entities.LaudoEletrico.Quadros_Paineis;
import br.com.jbst.repositories.modulo2.IInspecaoRepository;
import br.com.jbst.repositories.modulo2.IQuadros_PaineisRepository;

@Service
public class Quadros_PaineisService {

	@Autowired
	private ModelMapper modelMapper;
	
	
	@Autowired
	IQuadros_PaineisRepository iQuadros_PaineisRepository;
	
	
	@Autowired
	IInspecaoRepository inspecaoRepository;
	
	   public GetQuadros_PaineisDTO criarQuadros_Paineis(PostQuadros_PaineisDTO dto) {
	        try {
	            Quadros_Paineis quadros_paineis = modelMapper.map(dto, Quadros_Paineis.class);
	            quadros_paineis.setIdquadros_paineis(UUID.randomUUID());
	            quadros_paineis.setInspecao(inspecaoRepository.findById(dto.getIdInspecao()).orElse(null));
	            Quadros_Paineis quadros_paineiss = iQuadros_PaineisRepository.save(quadros_paineis);
	            return modelMapper.map(quadros_paineiss, GetQuadros_PaineisDTO.class);
	        } catch (Exception e) {
	            // Handle exception here
	            throw new RuntimeException("Falha ao criar Quadros/Painéis", e);
	        }
	    }
	   
	   public GetQuadros_PaineisDTO editarQuadros_Paineis(PutQuadros_PaineisDTO dto) {
	        try {
	            Quadros_Paineis quadros_paineis = modelMapper.map(dto, Quadros_Paineis.class);
	            quadros_paineis.setInspecao(inspecaoRepository.findById(dto.getIdInspecao()).orElse(null));
	            Quadros_Paineis quadros_paineiss = iQuadros_PaineisRepository.save(quadros_paineis);
	            return modelMapper.map(quadros_paineiss, GetQuadros_PaineisDTO.class);
	        } catch (Exception e) {
	            // Handle exception here
	            throw new RuntimeException("Falha ao criar Quadros/Painéis", e);
	        }
	    }
	   
	   public GetQuadros_PaineisDTO consultarQuadros_PaineisPorId(UUID id) {
	        Quadros_Paineis quadros_paineis = iQuadros_PaineisRepository.findById(id)
	                .orElseThrow(() -> new IllegalArgumentException("Quadros/Painéis not found with id: " + id));
	        return modelMapper.map(quadros_paineis, GetQuadros_PaineisDTO.class);
	    }

	   public List<GetQuadros_PaineisDTO> consultarTodosOsQuadros_Paineis() {
	        List<Quadros_Paineis> quadros_paineisList = iQuadros_PaineisRepository.findAll();
	        return quadros_paineisList.stream()
	                .map(quadros_paineis -> modelMapper.map(quadros_paineis, GetQuadros_PaineisDTO.class))
	                .collect(Collectors.toList());
	    }
	   
	   public void excluirQuadros_PaineisPorId(UUID id) {
	        Quadros_Paineis quadrosPaineis = iQuadros_PaineisRepository.findById(id)
	                .orElseThrow(() -> new IllegalArgumentException("Quadros/Painéis not found with id: " + id));
	        iQuadros_PaineisRepository.delete(quadrosPaineis);
	    }
}

