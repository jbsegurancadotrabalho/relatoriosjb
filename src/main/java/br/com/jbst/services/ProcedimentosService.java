package br.com.jbst.services;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.jbst.DTO2.GetProcedimentosDTO;
import br.com.jbst.DTO2.GetTomadasDTO;
import br.com.jbst.DTO2.PostProcedimentosDTO;
import br.com.jbst.DTO2.PostTomadasDTO;
import br.com.jbst.DTO2.PutProcedimentosDTO;
import br.com.jbst.DTO2.PutTomadasDTO;
import br.com.jbst.entities.LaudoEletrico.Procedimentos;
import br.com.jbst.repositories.modulo2.IInspecaoRepository;
import br.com.jbst.repositories.modulo2.IProcedimentosRepository;

@Service
public class ProcedimentosService {
	@Autowired
	private ModelMapper modelMapper;
	
	
	@Autowired
	IProcedimentosRepository iprocedimentosRepository;
	
	
	@Autowired
	IInspecaoRepository inspecaoRepository;
	
	   public GetProcedimentosDTO criarProcedimentos(PostProcedimentosDTO dto) {
	        try {
	        	Procedimentos procedimentos = modelMapper.map(dto, Procedimentos.class);
	        	procedimentos.setIdProcedimentos(UUID.randomUUID());
	        	procedimentos.setInspecao(inspecaoRepository.findById(dto.getIdInspecao()).orElse(null));
	        	Procedimentos procedimento = iprocedimentosRepository.save(procedimentos);
	            return modelMapper.map(procedimento, GetProcedimentosDTO.class);
	        } catch (Exception e) {
	            // Handle exception here
	            throw new RuntimeException("Falha ao criar procedimentos", e);
	        }
	    }
	   
	   public GetProcedimentosDTO  editarProcedimentos(PutProcedimentosDTO dto) {
		   try {
	        	Procedimentos procedimentos = modelMapper.map(dto, Procedimentos.class);
	        	procedimentos.setInspecao(inspecaoRepository.findById(dto.getIdInspecao()).orElse(null));
	        	Procedimentos procedimento = iprocedimentosRepository.save(procedimentos);
	            return modelMapper.map(procedimento, GetProcedimentosDTO.class);
	        } catch (Exception e) {
	            // Handle exception here
	            throw new RuntimeException("Falha ao criar procedimentos", e);
	        }
	    }
	   
	   public GetProcedimentosDTO buscarProcedimentosPorId(UUID id) throws Exception {
	        Procedimentos procedimentos = iprocedimentosRepository.findById(id)
	                .orElseThrow(() -> new Exception("Procedimentos não encontrados com o ID fornecido."));
	        return modelMapper.map(procedimentos, GetProcedimentosDTO.class);
	    }

	    public List<GetProcedimentosDTO> buscarTodosProcedimentos() {
	        List<Procedimentos> todosProcedimentos = iprocedimentosRepository.findAll();
	        return todosProcedimentos.stream()
	                .map(procedimentos -> modelMapper.map(procedimentos, GetProcedimentosDTO.class))
	                .collect(Collectors.toList());
	    }
	    
	    public void excluirProcedimentosPorId(UUID id) throws Exception {
	        if (iprocedimentosRepository.existsById(id)) {
	            iprocedimentosRepository.deleteById(id);
	        } else {
	            throw new Exception("Procedimentos não encontrados com o ID fornecido.");
	        }
	    }
}
