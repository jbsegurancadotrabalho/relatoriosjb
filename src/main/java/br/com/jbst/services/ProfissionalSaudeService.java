package br.com.jbst.services;

import java.time.Instant;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.jbst.DTO2.GetProfissionalSaudeDTO;
import br.com.jbst.DTO2.PostProfissionalSaudeDTO;
import br.com.jbst.DTO2.PutProfissionalSaudeDTO;
import br.com.jbst.entities.ProfissionalSaude;
import br.com.jbst.repositories.modulo1.ICredenciadosRepository;
import br.com.jbst.repositories.modulo1.IEnderecoRepository;
import br.com.jbst.repositories.modulo2.IProfissionalSaudeRepository;

@Service
public class ProfissionalSaudeService {

	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	IProfissionalSaudeRepository iprofissionalSaudeRepository;

	@Autowired
	ICredenciadosRepository icredenciadoRepository;

	@Autowired
	IEnderecoRepository ienderecoRepository;

	public GetProfissionalSaudeDTO criarProfissionalSaude(PostProfissionalSaudeDTO dto) {
	    ProfissionalSaude profissionalsaude = modelMapper.map(dto, ProfissionalSaude.class);
	    profissionalsaude.setId_profissionalsaude(UUID.randomUUID());
	    profissionalsaude.setDataHoraCriacao(Instant.now());
	    
	    // Verificar se o ID do endereço foi fornecido e, se sim, buscar o endereço correspondente
	    if (dto.getIdEndereco() != null) {
	        profissionalsaude.setEndereco(ienderecoRepository.findById(dto.getIdEndereco()).orElse(null));
	    }

	    // Verificar se o ID do credenciado foi fornecido e, se sim, buscar o credenciado correspondente
	    if (dto.getIdCredenciado() != null) {
	        profissionalsaude.setCredenciados(icredenciadoRepository.findById(dto.getIdCredenciado()).orElse(null));
	    }

	    profissionalsaude = iprofissionalSaudeRepository.save(profissionalsaude);
	    return modelMapper.map(profissionalsaude, GetProfissionalSaudeDTO.class);
	}


	public GetProfissionalSaudeDTO atualizarProfissionalSaude(PutProfissionalSaudeDTO dto) {
	    // Verifique se o DTO contém um ID para identificar o objeto a ser atualizado
	    if (dto.getId_profissionalsaude() == null) {
	        throw new IllegalArgumentException("ID do ProfissionalSaude não fornecido para atualização.");
	    }

	    // Recuperar o objeto ProfissionalSaude com base no ID fornecido
	    ProfissionalSaude profissionalsaude = iprofissionalSaudeRepository.findById(dto.getId_profissionalsaude())
	            .orElseThrow(() -> new RuntimeException(
	                    "ProfissionalSaude não encontrado com id: " + dto.getId_profissionalsaude()));

	    // Atualizando os campos com os dados do DTO
	    modelMapper.map(dto, profissionalsaude);

	    // Verificar se o ID do endereço foi fornecido e, se sim, buscar o endereço correspondente
	    if (dto.getIdEndereco() != null) {
	        profissionalsaude.setEndereco(ienderecoRepository.findById(dto.getIdEndereco()).orElse(null));
	    } else {
	        profissionalsaude.setEndereco(null); // Define como nulo se o ID do endereço não foi fornecido
	    }

	    // Verificar se o ID do credenciado foi fornecido e, se sim, buscar o credenciado correspondente
	    if (dto.getIdCredenciado() != null) {
	        profissionalsaude.setCredenciados(icredenciadoRepository.findById(dto.getIdCredenciado()).orElse(null));
	    } else {
	        profissionalsaude.setCredenciados(null); // Define como nulo se o ID do credenciado não foi fornecido
	    }

	    // Salvando as alterações no banco de dados
	    profissionalsaude = iprofissionalSaudeRepository.save(profissionalsaude);

	    // Mapeando a entidade atualizada de volta para DTO
	    return modelMapper.map(profissionalsaude, GetProfissionalSaudeDTO.class);
	}

	public List<GetProfissionalSaudeDTO> buscarTodosProfissionaisSaude() {
	    List<ProfissionalSaude> profissionalList = iprofissionalSaudeRepository.findAll();
	    return profissionalList.stream()
	            .map(profissional -> {
	                GetProfissionalSaudeDTO dto = modelMapper.map(profissional, GetProfissionalSaudeDTO.class);
	                
	                return dto;
	            })
	            .collect(Collectors.toList());
	}

	public GetProfissionalSaudeDTO buscarProfissionalSaudePorId(UUID id) {
		ProfissionalSaude profissionalSaude = iprofissionalSaudeRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("Profissional não encontrado com o ID: " + id));
		return modelMapper.map(profissionalSaude, GetProfissionalSaudeDTO.class);
	}

	public void excluirProfissionalSaudePorId(UUID id) {
		// Verificar se o curso credenciado existe antes de excluí-lo
		if (!iprofissionalSaudeRepository.existsById(id)) {
			throw new RuntimeException("Exame Credenciado não encontrado com o ID: " + id);
		}
		iprofissionalSaudeRepository.deleteById(id);
	}
}
