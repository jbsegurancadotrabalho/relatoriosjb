package br.com.jbst.services;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;
import java.util.stream.Collectors;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.jbst.DTO.GetProbabilidadeOcorrenciaDTO;
import br.com.jbst.DTO.PostProbabilidadeOcorrenciaDTO;
import br.com.jbst.DTO.PostProbabilidadeOcorrenciaIncluirRiscosDTO;
import br.com.jbst.DTO.PutProbabilidadeOcorrenciaDTO;
import br.com.jbst.DTO.PutProbabilidadeOcorrenciaIncluirRiscosDTO;
import br.com.jbst.entities.ProbabilidadeOcorrencia;
import br.com.jbst.repositories.modulo2.IPerigoRepository;
import br.com.jbst.repositories.modulo2.IProbabilidadeOcorrenciaRepository;
import br.com.jbst.repositories.modulo2.IRiscosRepository;

@Service
public class ProbabilidadeOcorrenciaService {

    @Autowired
    private IProbabilidadeOcorrenciaRepository probabilidadeOcorrenciaRepository;
    
    @Autowired
    private IPerigoRepository perigoRepository;

    @Autowired
    private IRiscosRepository riscosRepository;

    @Autowired
    private ModelMapper modelMapper;

    public GetProbabilidadeOcorrenciaDTO criarProbabilidadeOcorrencia(PostProbabilidadeOcorrenciaDTO dto) {
        ProbabilidadeOcorrencia probabilidadeOcorrencia = modelMapper.map(dto, ProbabilidadeOcorrencia.class);
        probabilidadeOcorrencia.setIdprobabilidadeOcorrencia(UUID.randomUUID());

        // Busca e associa o perigo com base no ID fornecido, se não for nulo
        if (dto.getIdPerigo() != null) {
            perigoRepository.findById(dto.getIdPerigo()).ifPresent(probabilidadeOcorrencia::setPerigo);
        }

        // Busca e associa os riscos com base no ID fornecido, se não for nulo
       
        
        probabilidadeOcorrencia = probabilidadeOcorrenciaRepository.save(probabilidadeOcorrencia);

        return modelMapper.map(probabilidadeOcorrencia, GetProbabilidadeOcorrenciaDTO.class);
    }
    
    
    public GetProbabilidadeOcorrenciaDTO criarProbabilidadeOcorrenciaRiscos(PostProbabilidadeOcorrenciaIncluirRiscosDTO dto) {
        ProbabilidadeOcorrencia probabilidadeOcorrencia = modelMapper.map(dto, ProbabilidadeOcorrencia.class);
        probabilidadeOcorrencia.setIdprobabilidadeOcorrencia(UUID.randomUUID());

        // Busca e associa o perigo com base no ID fornecido, se não for nulo
        if (dto.getIdRisco() != null) {
            riscosRepository.findById(dto.getIdRisco()).ifPresent(probabilidadeOcorrencia::setRiscos);
        }

        // Busca e associa os riscos com base no ID fornecido, se não for nulo
       
        
        probabilidadeOcorrencia = probabilidadeOcorrenciaRepository.save(probabilidadeOcorrencia);

        return modelMapper.map(probabilidadeOcorrencia, GetProbabilidadeOcorrenciaDTO.class);
    }


    public GetProbabilidadeOcorrenciaDTO editarProbabilidadeOcorrencia(PutProbabilidadeOcorrenciaDTO dto) {
        // Verifica se o DTO contém o ID da probabilidade de ocorrência
        UUID idProbabilidadeOcorrencia = dto.getIdprobabilidadeOcorrencia();
        if (idProbabilidadeOcorrencia == null) {
            return null; // Ou lança uma exceção de acordo com sua lógica
        }

        // Busca a probabilidade de ocorrência no banco de dados com base no ID fornecido
        ProbabilidadeOcorrencia probabilidadeOcorrenciaExistente = probabilidadeOcorrenciaRepository.findById(idProbabilidadeOcorrencia)
                .orElseThrow(() -> new NoSuchElementException("Probabilidade de Ocorrência não encontrada com o ID: " + idProbabilidadeOcorrencia));

        // Mapeia os dados do DTO para a entidade existente
        modelMapper.map(dto, probabilidadeOcorrenciaExistente);
        // Salva as alterações
        probabilidadeOcorrenciaExistente = probabilidadeOcorrenciaRepository.save(probabilidadeOcorrenciaExistente);

        // Mapeia a entidade atualizada de volta para o DTO de resposta
        return modelMapper.map(probabilidadeOcorrenciaExistente, GetProbabilidadeOcorrenciaDTO.class);
    }

    public GetProbabilidadeOcorrenciaDTO editarProbabilidadeOcorrenciaIncluirRiscos(PutProbabilidadeOcorrenciaIncluirRiscosDTO dto) {
        // Verifica se o DTO contém o ID da probabilidade de ocorrência
        UUID idProbabilidadeOcorrencia = dto.getIdprobabilidadeOcorrencia();
        if (idProbabilidadeOcorrencia == null) {
            return null; // Ou lança uma exceção de acordo com sua lógica
        }

        // Busca a probabilidade de ocorrência no banco de dados com base no ID fornecido
        ProbabilidadeOcorrencia probabilidadeOcorrenciaExistente = probabilidadeOcorrenciaRepository.findById(idProbabilidadeOcorrencia)
                .orElseThrow(() -> new NoSuchElementException("Probabilidade de Ocorrência não encontrada com o ID: " + idProbabilidadeOcorrencia));

        // Mapeia os dados do DTO para a entidade existente
        modelMapper.map(dto, probabilidadeOcorrenciaExistente);

        // Salva as alterações
        probabilidadeOcorrenciaExistente = probabilidadeOcorrenciaRepository.save(probabilidadeOcorrenciaExistente);

        // Mapeia a entidade atualizada de volta para o DTO de resposta
        return modelMapper.map(probabilidadeOcorrenciaExistente, GetProbabilidadeOcorrenciaDTO.class);
    }
    
    public GetProbabilidadeOcorrenciaDTO buscarPorId(UUID id) {
        ProbabilidadeOcorrencia probabilidadeOcorrencia = probabilidadeOcorrenciaRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Probabilidade de Ocorrência não encontrada com o ID: " + id));
        return modelMapper.map(probabilidadeOcorrencia, GetProbabilidadeOcorrenciaDTO.class);
    }

    public List<GetProbabilidadeOcorrenciaDTO> buscarTodasProbabilidadesOcorrencia() {
        List<ProbabilidadeOcorrencia> probabilidadesOcorrencia = probabilidadeOcorrenciaRepository.findAll();
        return probabilidadesOcorrencia.stream()
                .map(probabilidadeOcorrencia -> modelMapper.map(probabilidadeOcorrencia, GetProbabilidadeOcorrenciaDTO.class))
                .collect(Collectors.toList());
    }
    
    public void excluirProbabilidadeOcorrencia(UUID id) {
        ProbabilidadeOcorrencia probabilidadeOcorrencia = probabilidadeOcorrenciaRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Probabilidade de Ocorrência não encontrada com o ID: " + id));
        probabilidadeOcorrenciaRepository.delete(probabilidadeOcorrencia);
    }

}



