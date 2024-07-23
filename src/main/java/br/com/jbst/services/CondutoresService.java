package br.com.jbst.services;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.jbst.entities.LaudoEletrico.Quadros_Paineis;
import br.com.jbst.repositories.modulo1.ICondutoresRepository;
import br.com.jbst.repositories.modulo2.IInspecaoRepository;
import br.com.jbst.repositories.modulo2.IQuadros_PaineisRepository;
import br.com.jbst.DTO2.GetCondutoresDTO;
import br.com.jbst.DTO2.GetQuadros_PaineisDTO;
import br.com.jbst.DTO2.PostCondutoresDTO;
import br.com.jbst.DTO2.PostQuadros_PaineisDTO;
import br.com.jbst.DTO2.PutCondutoresDTO;
import br.com.jbst.DTO2.PutQuadros_PaineisDTO;
import br.com.jbst.entities.LaudoEletrico.Condutores;
@Service
public class CondutoresService {
	@Autowired
	private ModelMapper modelMapper;
	
	
	@Autowired
	ICondutoresRepository icondutoresRepository;
	
	
	@Autowired
	IInspecaoRepository inspecaoRepository;
	
	
	 public GetCondutoresDTO criarCondutores(PostCondutoresDTO dto) {
	        try {
	        	Condutores condutores = modelMapper.map(dto, Condutores.class);
	        	condutores.setIdCondutores(UUID.randomUUID());
	        	condutores.setInspecao(inspecaoRepository.findById(dto.getIdInspecao()).orElse(null));
	        	Condutores condutor = icondutoresRepository.save(condutores);
	            return modelMapper.map(condutor, GetCondutoresDTO.class);
	        } catch (Exception e) {
	            // Handle exception here
	            throw new RuntimeException("Falha ao criar Quadros/Painéis", e);
	        }
	    }
	   
	   public GetCondutoresDTO editarCondutores(PutCondutoresDTO dto) {
		   try {
	        	Condutores condutores = modelMapper.map(dto, Condutores.class);
	        	condutores.setInspecao(inspecaoRepository.findById(dto.getIdInspecao()).orElse(null));
	        	Condutores condutor = icondutoresRepository.save(condutores);
	            return modelMapper.map(condutor, GetCondutoresDTO.class);
	        } catch (Exception e) {
	            // Handle exception here
	            throw new RuntimeException("Falha ao criar Quadros/Painéis", e);
	        }
	    }
	   public GetCondutoresDTO consultarCondutoresPorId(UUID id) {
	        Condutores condutor = icondutoresRepository.findById(id)
	                .orElseThrow(() -> new IllegalArgumentException("Condutores não encontrados com o ID: " + id));
	        return modelMapper.map(condutor, GetCondutoresDTO.class);
	    }

	    public List<GetCondutoresDTO> consultarTodosOsCondutores() {
	        List<Condutores> condutoresList = icondutoresRepository.findAll();
	        return condutoresList.stream()
	                .map(condutor -> modelMapper.map(condutor, GetCondutoresDTO.class))
	                .collect(Collectors.toList());
	    }
	    
	    public void excluirCondutoresPorId(UUID id) {
	        Condutores condutor = icondutoresRepository.findById(id)
	                .orElseThrow(() -> new IllegalArgumentException("Condutores não encontrados com o ID: " + id));
	        icondutoresRepository.delete(condutor);
	    }
}
