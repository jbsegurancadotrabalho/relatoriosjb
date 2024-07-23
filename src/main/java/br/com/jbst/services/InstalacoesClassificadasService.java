package br.com.jbst.services;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.jbst.DTO2.GetInstalacoesClassificadasDTO;
import br.com.jbst.DTO2.GetQuadros_PaineisDTO;
import br.com.jbst.DTO2.PostInstalacoesClassificadasDTO;
import br.com.jbst.DTO2.PostQuadros_PaineisDTO;
import br.com.jbst.DTO2.PutInstalacoesClassificadasDTO;
import br.com.jbst.DTO2.PutQuadros_PaineisDTO;
import br.com.jbst.entities.LaudoEletrico.InstalacoesClassificadas;
import br.com.jbst.entities.LaudoEletrico.Quadros_Paineis;
import br.com.jbst.repositories.modulo2.IInspecaoRepository;
import br.com.jbst.repositories.modulo2.IInstalacoesClassificadasRepository;

@Service
public class InstalacoesClassificadasService {

	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	IInstalacoesClassificadasRepository iinstalacoesClassificadasRepository;
	
	@Autowired
	IInspecaoRepository inspecaoRepository ;
	
	 public GetInstalacoesClassificadasDTO criarInstalacoesClassificadas(PostInstalacoesClassificadasDTO dto) {
	        try {
	        	InstalacoesClassificadas instalacoesClassificadas = modelMapper.map(dto, InstalacoesClassificadas.class);
	        	instalacoesClassificadas.setIdInstalacoesClassificadas(UUID.randomUUID());
	        	instalacoesClassificadas.setInspecao(inspecaoRepository.findById(dto.getIdInspecao()).orElse(null));
	        	InstalacoesClassificadas instalacoesClassificada = iinstalacoesClassificadasRepository.save(instalacoesClassificadas);
	            return modelMapper.map(instalacoesClassificada, GetInstalacoesClassificadasDTO.class);
	        } catch (Exception e) {
	            // Handle exception here
	            throw new RuntimeException("Falha ao criar Instalações Classificadas", e);
	        }
	    }
	   
	   public GetInstalacoesClassificadasDTO editarInstalacoesClassificadas(PutInstalacoesClassificadasDTO dto) {
		   try {
	        	InstalacoesClassificadas instalacoesClassificadas = modelMapper.map(dto, InstalacoesClassificadas.class);
	        	instalacoesClassificadas.setInspecao(inspecaoRepository.findById(dto.getIdInspecao()).orElse(null));
	        	InstalacoesClassificadas instalacoesClassificada = iinstalacoesClassificadasRepository.save(instalacoesClassificadas);
	            return modelMapper.map(instalacoesClassificada, GetInstalacoesClassificadasDTO.class);
	        } catch (Exception e) {
	            // Handle exception here
	            throw new RuntimeException("Falha ao criar Instalações Classificadas", e);
	        }
	    }
	   public GetInstalacoesClassificadasDTO buscarInstalacoesClassificadasPorId(UUID id) throws Exception {
	        InstalacoesClassificadas instalacoesClassificadas = iinstalacoesClassificadasRepository.findById(id)
	                .orElseThrow(() -> new Exception("Instalações Classificadas não encontrada com o ID fornecido."));
	        return modelMapper.map(instalacoesClassificadas, GetInstalacoesClassificadasDTO.class);
	    }

	    public List<GetInstalacoesClassificadasDTO> buscarTodasInstalacoesClassificadas() {
	        List<InstalacoesClassificadas> todasInstalacoesClassificadas = iinstalacoesClassificadasRepository.findAll();
	        return todasInstalacoesClassificadas.stream()
	                .map(instalacoesClassificadas -> modelMapper.map(instalacoesClassificadas, GetInstalacoesClassificadasDTO.class))
	                .collect(Collectors.toList());
	    }
	    public void excluirInstalacoesClassificadasPorId(UUID id) throws Exception {
	        if (iinstalacoesClassificadasRepository.existsById(id)) {
	            iinstalacoesClassificadasRepository.deleteById(id);
	        } else {
	            throw new Exception("Instalações Classificadas não encontrada com o ID fornecido.");
	        }
	    }
}
