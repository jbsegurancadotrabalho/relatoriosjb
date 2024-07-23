package br.com.jbst.services;


import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.jbst.DTO.GetNivelSeveridadeDTO;
import br.com.jbst.DTO.PostNivelSeveridadeDTO;
import br.com.jbst.DTO.PostNiveldeSeveridadeIncluirRiscosDTO;
import br.com.jbst.DTO.PutNivelSeveridadeDTO;
import br.com.jbst.DTO.PutNiveldeSeveridadeIncluirRiscosDTO;
import br.com.jbst.entities.Nivel_Severidade;
import br.com.jbst.entities.Perigo;
import br.com.jbst.entities.Riscos;
import br.com.jbst.repositories.modulo1.INivelSeveridadeRepository;
import br.com.jbst.repositories.modulo2.IPerigoRepository;
import br.com.jbst.repositories.modulo2.IRiscosRepository;

@Service
public class NivelSeveridadeService {

    @Autowired
    private INivelSeveridadeRepository nivelSeveridadeRepository;
    
    @Autowired
    private IPerigoRepository perigoRepository;

    @Autowired
    private IRiscosRepository riscosRepository;

    @Autowired
    private ModelMapper modelMapper;

    public GetNivelSeveridadeDTO criarNivelSeveridade(PostNivelSeveridadeDTO dto) {
        Nivel_Severidade nivelSeveridade = modelMapper.map(dto, Nivel_Severidade.class);
        nivelSeveridade.setIdNivelSeveridade(UUID.randomUUID());

        // Busca e associa o perigo com base no ID fornecido, se não for nulo
        if (dto.getIdPerigo() != null) {
            perigoRepository.findById(dto.getIdPerigo()).ifPresent(nivelSeveridade::setPerigo);
        }

     
        
        nivelSeveridade = nivelSeveridadeRepository.save(nivelSeveridade);

        return modelMapper.map(nivelSeveridade, GetNivelSeveridadeDTO.class);
    }
    
    
    public GetNivelSeveridadeDTO criarNivelSeveridadeIncluirRiscos(PostNiveldeSeveridadeIncluirRiscosDTO dto) {
        Nivel_Severidade nivelSeveridade = modelMapper.map(dto, Nivel_Severidade.class);
        nivelSeveridade.setIdNivelSeveridade(UUID.randomUUID());

        // Busca e associa o perigo com base no ID fornecido, se não for nulo
        if (dto.getIdRisco() != null) {
            riscosRepository.findById(dto.getIdRisco()).ifPresent(nivelSeveridade::setRiscos);
        }

     
        
        nivelSeveridade = nivelSeveridadeRepository.save(nivelSeveridade);

        return modelMapper.map(nivelSeveridade, GetNivelSeveridadeDTO.class);
    }
    
    
    public GetNivelSeveridadeDTO editarNivelSeveridade(PutNivelSeveridadeDTO dto) {
        // Verifica se o ID da entidade é válido
        UUID idNivelSeveridade = dto.getIdNivelSeveridade();
        if (idNivelSeveridade == null) {
            return null; // ou lançar uma exceção, dependendo da sua lógica de negócios
        }

        // Busca a entidade no banco de dados com base no ID fornecido
        Nivel_Severidade nivelSeveridadeExistente = nivelSeveridadeRepository.findById(idNivelSeveridade)
                .orElseThrow(() -> new NoSuchElementException("Nível de Severidade não encontrado com o ID: " + idNivelSeveridade));

        // Mapeia os dados do DTO para a entidade existente
        modelMapper.map(dto, nivelSeveridadeExistente);

        // Atualiza o perigo com base no ID fornecido no DTO, se não for nulo
        if (dto.getIdPerigo() != null) {
            Perigo perigo = perigoRepository.findById(dto.getIdPerigo())
                    .orElseThrow(() -> new NoSuchElementException("Perigo não encontrado com o ID: " + dto.getIdPerigo()));
            nivelSeveridadeExistente.setPerigo(perigo);
        } else {
            nivelSeveridadeExistente.setPerigo(null);
        }

        

        // Salva as alterações
        nivelSeveridadeExistente = nivelSeveridadeRepository.save(nivelSeveridadeExistente);

        // Mapeia a entidade atualizada de volta para o DTO de resposta
        return modelMapper.map(nivelSeveridadeExistente, GetNivelSeveridadeDTO.class);
    }

    
    public GetNivelSeveridadeDTO editarNivelSeveridadeIncluirRiscos(PutNiveldeSeveridadeIncluirRiscosDTO dto) {
        // Verifica se o ID da entidade é válido
        UUID idNivelSeveridade = dto.getIdNivelSeveridade();
        if (idNivelSeveridade == null) {
            return null; // ou lançar uma exceção, dependendo da sua lógica de negócios
        }

        // Busca a entidade no banco de dados com base no ID fornecido
        Nivel_Severidade nivelSeveridadeExistente = nivelSeveridadeRepository.findById(idNivelSeveridade)
                .orElseThrow(() -> new NoSuchElementException("Nível de Severidade não encontrado com o ID: " + idNivelSeveridade));

        // Mapeia os dados do DTO para a entidade existente
        modelMapper.map(dto, nivelSeveridadeExistente);

    
        

        // Salva as alterações
        nivelSeveridadeExistente = nivelSeveridadeRepository.save(nivelSeveridadeExistente);

        // Mapeia a entidade atualizada de volta para o DTO de resposta
        return modelMapper.map(nivelSeveridadeExistente, GetNivelSeveridadeDTO.class);
    }

    public GetNivelSeveridadeDTO buscarPorId(UUID id) {
        Nivel_Severidade nivelSeveridade = nivelSeveridadeRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Nível de Severidade não encontrado com o ID: " + id));
        return modelMapper.map(nivelSeveridade, GetNivelSeveridadeDTO.class);
    }

    public List<GetNivelSeveridadeDTO> buscarTodosNiveisSeveridade() {
        List<Nivel_Severidade> niveisSeveridade = nivelSeveridadeRepository.findAll();
        return niveisSeveridade.stream()
                .map(nivelSeveridade -> modelMapper.map(nivelSeveridade, GetNivelSeveridadeDTO.class))
                .collect(Collectors.toList());
    }
    
    public void excluirNivelSeveridade(UUID id) {
        Nivel_Severidade nivelSeveridade = nivelSeveridadeRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Nível de Severidade não encontrado com o ID: " + id));
        nivelSeveridadeRepository.delete(nivelSeveridade);
    }

}
