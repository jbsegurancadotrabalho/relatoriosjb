package br.com.jbst.services;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import br.com.jbst.DTO.GetExamesDTO;
import br.com.jbst.DTO.PostExamesDTO;
import br.com.jbst.DTO.PutExamesDTO;
import br.com.jbst.entities.Exames;
import br.com.jbst.repositories.modulo1.IExamesRepository;

@Service
public class ExamesServices {

	
	@Autowired
	IExamesRepository iexamesRepository;
	
	@Autowired
	private ModelMapper modelMapper;
	
	public GetExamesDTO criarExames(PostExamesDTO dto) throws Exception {
	    try {
	        Exames exames = modelMapper.map(dto, Exames.class);
	        
	        // Definindo um novo ID para o exame
	        exames.setIdExames(UUID.randomUUID());

	        // Salvando o exame com o novo ID
	        Exames novoExame = iexamesRepository.save(exames);
	        
	        // Mapeando o exame para o DTO de retorno
	        return modelMapper.map(novoExame, GetExamesDTO.class);
	    } catch (Exception e) {
	        throw new Exception("Erro ao criar exame: " + e.getMessage());
	    }
	}

	
	public GetExamesDTO editarExames(PutExamesDTO dto) throws Exception {
        if (dto == null) {
            throw new IllegalArgumentException("DTO de edição de exame não pode ser nulo.");
        }

        if (dto.getIdExames() == null) {
            throw new IllegalArgumentException("ID do exame não pode ser nulo.");
        }

        Exames exameExistente = iexamesRepository.findById(dto.getIdExames())
                .orElseThrow(() -> new Exception("Exame não encontrado com o ID: " + dto.getIdExames()));

        modelMapper.map(dto, exameExistente);

        Exames exameAtualizado = iexamesRepository.save(exameExistente);

        return modelMapper.map(exameAtualizado, GetExamesDTO.class);
    }

	public List<GetExamesDTO> consultarExames() {
        List<Exames> exames = iexamesRepository.findAll();
        return exames.stream()
                .map(exame -> modelMapper.map(exame, GetExamesDTO.class))
                .collect(Collectors.toList());
    }

    public GetExamesDTO consultarExamePorId(UUID idExames) throws Exception {
        Exames exame = iexamesRepository.findById(idExames)
                .orElseThrow(() -> new Exception("Exame não encontrado com o ID: " + idExames));
        return modelMapper.map(exame, GetExamesDTO.class);
    }
    
    public GetExamesDTO excluirExame(UUID idExames) throws Exception {
        try {
            Exames exameExcluido = iexamesRepository.findById(idExames)
                    .orElseThrow(() -> new Exception("Exame não encontrado com o ID: " + idExames));

            iexamesRepository.deleteById(idExames);

            return modelMapper.map(exameExcluido, GetExamesDTO.class);
        } catch (EmptyResultDataAccessException e) {
            throw new Exception("Exame não encontrado com o ID: " + idExames);
        }
    }
}
