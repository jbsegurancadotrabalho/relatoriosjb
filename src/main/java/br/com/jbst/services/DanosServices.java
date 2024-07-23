package br.com.jbst.services;

import java.time.Instant;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.jbst.DTO.GetDanosDTO;
import br.com.jbst.DTO.PostDanosDTO;
import br.com.jbst.DTO.PostDanosRiscosDTO;
import br.com.jbst.DTO.PutDanosDTO;
import br.com.jbst.entities.Danos;
import br.com.jbst.repositories.modulo2.IDanosRepository;
import br.com.jbst.repositories.modulo2.IPerigoRepository;
import br.com.jbst.repositories.modulo2.IRiscosRepository;

@Service
public class DanosServices {

	@Autowired
	private IDanosRepository danosRepository;

	@Autowired
	private IPerigoRepository perigoRepository;

	@Autowired
	private IRiscosRepository riscosRepository;

	@Autowired
	private ModelMapper modelMapper;

	public GetDanosDTO criarDanos(PostDanosDTO dto) {
		Danos danos = modelMapper.map(dto, Danos.class);
		danos.setDataHoraCriacao(Instant.now());

		// Busca e associa o perigo com base no ID fornecido, se não for nulo
		if (dto.getIdPerigo() != null) {
			perigoRepository.findById(dto.getIdPerigo()).ifPresent(danos::setPerigo);
		}
		danos.setIdDanos(UUID.randomUUID());
		danos = danosRepository.save(danos);
		return modelMapper.map(danos, GetDanosDTO.class);
	}
	
	public GetDanosDTO criarDanosRiscos(PostDanosRiscosDTO dto) {
		Danos danos = modelMapper.map(dto, Danos.class);
		danos.setDataHoraCriacao(Instant.now());
		// Busca e associa o perigo com base no ID fornecido, se não for nulo
		if (dto.getIdRisco() != null) {
			riscosRepository.findById(dto.getIdRisco()).ifPresent(danos::setRiscos);
		}	
		danos.setIdDanos(UUID.randomUUID());
		danos = danosRepository.save(danos);
		return modelMapper.map(danos, GetDanosDTO.class);
	}
	
	
	 public GetDanosDTO editarDanos(PutDanosDTO dto) {
	        // Verifica se o ID do dano é válido
	        UUID idDanos = dto.getIdDanos();
	        if (idDanos == null) {
	            return null;
	        }

	        // Busca o dano no banco de dados com base no ID fornecido
	        Danos danosExistente = danosRepository.findById(idDanos).orElse(null);

	        // Se o dano não for encontrado, retorna null
	        if (danosExistente == null) {
	            return null;
	        }

	        // Atualiza os dados do dano existente com base nas informações fornecidas no DTO
	        modelMapper.map(dto, danosExistente);

	        // Define a data e hora de criação como a data e hora atuais
	        danosExistente.setDataHoraCriacao(Instant.now());

	        // Salva o dano atualizado no banco de dados
	        Danos danosAtualizado = danosRepository.save(danosExistente);

	        // Retorna o dano atualizado convertido para DTO
	        return modelMapper.map(danosAtualizado, GetDanosDTO.class);
	    }
	 public GetDanosDTO buscarPorId(UUID idDanos) {
	        Danos danos = danosRepository.findById(idDanos).orElse(null);
	        return danos != null ? modelMapper.map(danos, GetDanosDTO.class) : null;
	    }

	    public List<GetDanosDTO> buscarTodos() {
	        List<Danos> todosDanos = danosRepository.findAll();
	        return todosDanos.stream()
	            .map(danos -> modelMapper.map(danos, GetDanosDTO.class))
	            .collect(Collectors.toList());
	    }

	    public boolean excluirDanos(UUID idDanos) {
	        if (danosRepository.existsById(idDanos)) {
	            danosRepository.deleteById(idDanos);
	            return true;
	        } else {
	            return false;
	        }
	    }
}
