package br.com.jbst.services;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.jbst.DTO.GetCnaeDto;
import br.com.jbst.DTO2.GetAgendamentoDTO;
import br.com.jbst.DtoAtendimento.GetAtendimentoDTO;
import br.com.jbst.DtoAtendimento.PostAtendimentoDTO;
import br.com.jbst.DtoAtendimento.PutAtendimentoDTO;
import br.com.jbst.entities.Agendamento;
import br.com.jbst.entities.Atendimento;
import br.com.jbst.entities.Cnae;
import br.com.jbst.repositories.modulo1.IAgendamentoRepository;
import br.com.jbst.repositories.modulo1.IAtendimentoRepository;

@Service
public class AtendimentoService {
	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private IAtendimentoRepository IAtendimentoRepository;
	
	@Autowired
	private IAgendamentoRepository agendamentoRepository;

	public GetAtendimentoDTO criarAtendimento(PostAtendimentoDTO dto) {
	    UUID idAtendimento = UUID.randomUUID();
	    Integer numeroAtendimento = gerarNumeroAtendimento();

	    // Map the DTO to the Atendimento entity
	    Atendimento atendimento = modelMapper.map(dto, Atendimento.class);
	    atendimento.setIdAtendimento(idAtendimento);
	    atendimento.setNumeroatendimento(numeroAtendimento);

	    if (dto.getIdAgendamento() == null) {
	        throw new IllegalArgumentException("ID da agenda não pode ser nulo");
	    }

	    // Fetch the Agendamento entity by ID
	    Agendamento agendamento = agendamentoRepository.findById(dto.getIdAgendamento())
	        .orElseThrow(() -> new IllegalArgumentException("Agenda com o ID fornecido não foi encontrada"));

	    // Check if the Agendamento already has an associated Atendimento
	    if (agendamento.getAtendimento() != null) {
	        for (Atendimento existingAtendimento : agendamento.getAtendimento()) {
	            if (existingAtendimento.getIdAtendimento().equals(idAtendimento)) {
	                throw new IllegalArgumentException("Esta Agenda já possui um Atendimento associado");
	            }
	        }
	    }

	    // Associate the Atendimento with the Agendamento
	    atendimento.setAgendamento(agendamento);
	    
	    // Save the Atendimento entity
	    Atendimento savedAtendimento = IAtendimentoRepository.save(atendimento);
	    
	    // Return the mapped DTO
	    return modelMapper.map(savedAtendimento, GetAtendimentoDTO.class);
	}


	public GetAtendimentoDTO editarAtendimento(PutAtendimentoDTO dto) {
	    // Fetch the existing Atendimento by its ID
	    Atendimento atendimento = IAtendimentoRepository.findById(dto.getIdAtendimento())
	        .orElseThrow(() -> new IllegalArgumentException("Atendimento com o ID fornecido não foi encontrado"));

	    // Map the updated fields from dto to the Atendimento entity
	    modelMapper.map(dto, atendimento);

	    // Save the updated Atendimento entity to the repository
	    Atendimento updatedAtendimento = IAtendimentoRepository.save(atendimento);

	    // Return the updated Atendimento as a GetAtendimentoDTO
	    return modelMapper.map(updatedAtendimento, GetAtendimentoDTO.class);
	}
	
	public GetAtendimentoDTO buscarAtendimento(UUID idAtendimento) {
	    // Fetch the Atendimento by ID
	    Atendimento atendimento = IAtendimentoRepository.findById(idAtendimento)
	        .orElseThrow(() -> new IllegalArgumentException("Atendimento com o ID fornecido não foi encontrado"));

	    // Map the entity to the DTO
	    return modelMapper.map(atendimento, GetAtendimentoDTO.class);
	}

	public List<GetAtendimentoDTO> buscarAtendimentos() {
		List<Atendimento> atendimentos = IAtendimentoRepository.findAll();
		return atendimentos.stream().map(atendimento -> modelMapper.map(atendimento, GetAtendimentoDTO.class)).collect(Collectors.toList());
	}


	private Integer gerarNumeroAtendimento() {
		Integer ultimoNumero = IAtendimentoRepository.findMaxNumeroAtendimento();
		if (ultimoNumero == null) {
			ultimoNumero = 0;
		}
		return ultimoNumero + 1;
	}
}
