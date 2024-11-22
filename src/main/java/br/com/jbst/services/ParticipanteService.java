package br.com.jbst.services;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.jbst.DtoEvento.GetParticipanteDTO;
import br.com.jbst.DtoEvento.PostParticipanteDTO;
import br.com.jbst.DtoEvento.PutParticipanteDTO;
import br.com.jbst.entities.Participante;
import br.com.jbst.repositories.modulo1.IEventoRepository;
import br.com.jbst.repositories.modulo1.IParticipanteRepository;

@Service
public class ParticipanteService {
	@Autowired
	private IEventoRepository  ieventoRepository;
	
	@Autowired
	private IParticipanteRepository  iparticipanteRepository;
	
	@Autowired
	private ModelMapper modelMapper;
	
	public GetParticipanteDTO criarParticipante(PostParticipanteDTO dto) throws Exception {
        try {
        	Participante participante = modelMapper.map(dto, Participante.class);
        	participante.setIdParticipante(UUID.randomUUID());
        	participante.setEvento(ieventoRepository.findById(dto.getIdEvento()).orElse(null));
        	 iparticipanteRepository.save(participante);
            return modelMapper.map(participante, GetParticipanteDTO.class);
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }
	
	public GetParticipanteDTO editarParticipante(PutParticipanteDTO dto) throws Exception {
	    try {
	        // Retrieve the existing Participante by ID
	        Participante participante = iparticipanteRepository.findById(dto.getIdParticipante())
	                .orElseThrow(() -> new Exception("Participante not found"));

	        // Use ModelMapper to map the fields from the DTO to the existing entity
	        modelMapper.map(dto, participante);

	        // Save the updated Participante back to the repository
	        participante = iparticipanteRepository.save(participante);

	        // Map the updated Participante entity to the GetParticipanteDTO
	        return modelMapper.map(participante, GetParticipanteDTO.class);
	    } catch (Exception e) {
	        e.printStackTrace();
	        throw e;
	    }
}
	public GetParticipanteDTO getParticipanteById(UUID idParticipante) throws Exception {
	    try {
	        // Retrieve the Participante by ID
	        Participante participante = iparticipanteRepository.findById(idParticipante)
	                .orElseThrow(() -> new Exception("Participante not found"));

	        // Map the Participante entity to GetParticipanteDTO
	        return modelMapper.map(participante, GetParticipanteDTO.class);
	    } catch (Exception e) {
	        e.printStackTrace();
	        throw e;
	    }
	}
	public List<GetParticipanteDTO> getAllParticipantes() {
	    // Retrieve all Participantes from the repository
	    List<Participante> participantes = iparticipanteRepository.findAll();

	    // Map the list of Participante entities to a list of GetParticipanteDTO
	    return participantes.stream()
	            .map(participante -> modelMapper.map(participante, GetParticipanteDTO.class))
	            .collect(Collectors.toList());
	}
	
	 public GetParticipanteDTO excluirParticipante(UUID idParticipante) {
		    // Verificar se o evento existe
		 Participante participante = iparticipanteRepository.findById(idParticipante)
		            .orElseThrow(() -> new RuntimeException("Evento não encontrado"));

		    // Mapear o evento para GetEventoDTO antes de excluí-lo
		 GetParticipanteDTO participanteDTO = modelMapper.map(participante, GetParticipanteDTO.class);

		    // Excluir o evento
		 iparticipanteRepository.delete(participante);

		    // Retornar o DTO do evento que foi excluído
		    return participanteDTO;
		}
}
