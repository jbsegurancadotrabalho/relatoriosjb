package br.com.jbst.services;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.jbst.DTO2.GetTomadasDTO;
import br.com.jbst.DTO2.PostTomadasDTO;
import br.com.jbst.DTO2.PutTomadasDTO;
import br.com.jbst.entities.LaudoEletrico.Tomadas;
import br.com.jbst.repositories.modulo2.IInspecaoRepository;
import br.com.jbst.repositories.modulo2.ITomadasRepository;
@Service
public class TomadasService {
	@Autowired
	private ModelMapper modelMapper;
	
	
	@Autowired
	ITomadasRepository itomadasRepository;
	
	
	@Autowired
	IInspecaoRepository inspecaoRepository;
	
	   public GetTomadasDTO criarQuadros_Paineis(PostTomadasDTO dto) {
	        try {
	        	Tomadas tomadas = modelMapper.map(dto, Tomadas.class);
	        	tomadas.setIdTomadas(UUID.randomUUID());
	        	tomadas.setInspecao(inspecaoRepository.findById(dto.getIdInspecao()).orElse(null));
	        	Tomadas tomada = itomadasRepository.save(tomadas);
	            return modelMapper.map(tomada, GetTomadasDTO.class);
	        } catch (Exception e) {
	            // Handle exception here
	            throw new RuntimeException("Falha ao criar tomadas", e);
	        }
	    }
	   
	   public GetTomadasDTO editarQuadros_Paineis(PutTomadasDTO dto) {
		   try {
	        	Tomadas tomadas = modelMapper.map(dto, Tomadas.class);
	        	tomadas.setInspecao(inspecaoRepository.findById(dto.getIdInspecao()).orElse(null));
	        	Tomadas tomada = itomadasRepository.save(tomadas);
	            return modelMapper.map(tomada, GetTomadasDTO.class);
	        } catch (Exception e) {
	            // Handle exception here
	            throw new RuntimeException("Falha ao criar tomadas", e);
	        }
	    }
	   public GetTomadasDTO consultarTomadasPorId(UUID id) {
	        Tomadas tomada = itomadasRepository.findById(id)
	                .orElseThrow(() -> new IllegalArgumentException("Tomadas não encontradas com o ID: " + id));
	        return modelMapper.map(tomada, GetTomadasDTO.class);
	    }

	    public List<GetTomadasDTO> consultarTodasAsTomadas() {
	        List<Tomadas> tomadasList = itomadasRepository.findAll();
	        return tomadasList.stream()
	                .map(tomada -> modelMapper.map(tomada, GetTomadasDTO.class))
	                .collect(Collectors.toList());
	    } 
	    
	    public void excluirTomadasPorId(UUID id) {
	        itomadasRepository.findById(id)
	                .orElseThrow(() -> new IllegalArgumentException("Tomadas não encontradas com o ID: " + id));
	        itomadasRepository.deleteById(id);
	    }
}
