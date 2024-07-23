package br.com.jbst.services;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.jbst.repositories.modulo2.IInspecaoRepository;
import br.com.jbst.repositories.modulo2.IMaquinas_LaudoEletricoServiceRepository;
import br.com.jbst.DTO2.GetMaquinas_LaudoEletricoDTO;
import br.com.jbst.DTO2.PostMaquinas_LaudoEletricoDTO;
import br.com.jbst.DTO2.PutMaquinas_LaudoEletricoDTO;
import br.com.jbst.entities.LaudoEletrico.Maquinas_LaudoEletrico;
@Service
public class Maquinas_LaudoEletricoService {
	
	@Autowired
	private ModelMapper modelMapper;
	
	
	@Autowired
	IMaquinas_LaudoEletricoServiceRepository imaquinas_laudoeletricoServiceRepository;
	
	
	@Autowired
	IInspecaoRepository inspecaoRepository;
	
	public GetMaquinas_LaudoEletricoDTO criarMaquinas_LaudoEletrico(PostMaquinas_LaudoEletricoDTO dto) {
        try {
        	Maquinas_LaudoEletrico maquinas_laudoeletrico = modelMapper.map(dto, Maquinas_LaudoEletrico.class);
        	maquinas_laudoeletrico.setIdMaquinas_LaudoEletrico(UUID.randomUUID());
        	maquinas_laudoeletrico.setInspecao(inspecaoRepository.findById(dto.getIdInspecao()).orElse(null));
        	Maquinas_LaudoEletrico maquinas_laudoeletricos  = imaquinas_laudoeletricoServiceRepository.save(maquinas_laudoeletrico);
            return modelMapper.map(maquinas_laudoeletricos, GetMaquinas_LaudoEletricoDTO.class);
        } catch (Exception e) {
            // Handle exception here
            throw new RuntimeException("Falha ao criar Máquinas", e);
        }
    }
   
   public GetMaquinas_LaudoEletricoDTO editarMaquinas_LaudoEletrico(PutMaquinas_LaudoEletricoDTO dto) {
	   try {
       	Maquinas_LaudoEletrico maquinas_laudoeletrico = modelMapper.map(dto, Maquinas_LaudoEletrico.class);
       	maquinas_laudoeletrico.setInspecao(inspecaoRepository.findById(dto.getIdInspecao()).orElse(null));
       	Maquinas_LaudoEletrico maquinas_laudoeletricos  = imaquinas_laudoeletricoServiceRepository.save(maquinas_laudoeletrico);
           return modelMapper.map(maquinas_laudoeletricos, GetMaquinas_LaudoEletricoDTO.class);
       } catch (Exception e) {
           // Handle exception here
           throw new RuntimeException("Falha ao criar Máquinas", e);
       }
    }
   public GetMaquinas_LaudoEletricoDTO consultarMaquinas_LaudoEletricoPorId(UUID id) {
       Maquinas_LaudoEletrico maquina = imaquinas_laudoeletricoServiceRepository.findById(id)
               .orElseThrow(() -> new IllegalArgumentException("Máquina não encontrada com o ID: " + id));
       return modelMapper.map(maquina, GetMaquinas_LaudoEletricoDTO.class);
   }

   public List<GetMaquinas_LaudoEletricoDTO> consultarTodasAsMaquinas_LaudoEletrico() {
       List<Maquinas_LaudoEletrico> maquinasList = imaquinas_laudoeletricoServiceRepository.findAll();
       return maquinasList.stream()
               .map(maquina -> modelMapper.map(maquina, GetMaquinas_LaudoEletricoDTO.class))
               .collect(Collectors.toList());
   }
   public void excluirMaquinas_LaudoEletricoPorId(UUID id) {
       imaquinas_laudoeletricoServiceRepository.findById(id)
               .orElseThrow(() -> new IllegalArgumentException("Máquina não encontrada com o ID: " + id));
       imaquinas_laudoeletricoServiceRepository.deleteById(id);
   }
}
