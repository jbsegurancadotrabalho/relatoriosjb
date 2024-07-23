package br.com.jbst.services;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.jbst.DTO.GetPotencialSeveridadeDanosDTO;
import br.com.jbst.DTO.PostPotencialSeveridadeDanosDTO;
import br.com.jbst.DTO.PostPotencialSeveridadeDanosIncluirRiscosDTO;
import br.com.jbst.DTO.PutPotencialSeveridadeDanosDTO;
import br.com.jbst.DTO.PutPotencialSeveridadeDanosIncluirRiscosDTO;
import br.com.jbst.entities.Perigo;
import br.com.jbst.entities.Potencial_Severidade_Danos;
import br.com.jbst.entities.Riscos;
import br.com.jbst.repositories.modulo2.IPotencial_SeveridadeRepository;
import br.com.jbst.repositories.modulo2.IPerigoRepository;
import br.com.jbst.repositories.modulo2.IRiscosRepository;

import java.time.Instant;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class PotencialSeveridadeDanosService {

    @Autowired
    IPotencial_SeveridadeRepository potencialSeveridadeDanosRepository;

    @Autowired
    private IPerigoRepository perigoRepository;

    @Autowired
    private IRiscosRepository riscosRepository;

    @Autowired
    private ModelMapper modelMapper;

    public GetPotencialSeveridadeDanosDTO criarPotencialSeveridadeDanos(PostPotencialSeveridadeDanosDTO dto) {
        Potencial_Severidade_Danos potencialSeveridadeDanos = modelMapper.map(dto, Potencial_Severidade_Danos.class);
        potencialSeveridadeDanos.setIdPotencialSeveridade(UUID.randomUUID());

        // Busca e associa o perigo com base no ID fornecido, se não for nulo
        if (dto.getIdPerigo() != null) {
            perigoRepository.findById(dto.getIdPerigo()).ifPresent(potencialSeveridadeDanos::setPerigo);
        }

        potencialSeveridadeDanos.setIdPotencialSeveridade(UUID.randomUUID());

        potencialSeveridadeDanos = potencialSeveridadeDanosRepository.save(potencialSeveridadeDanos);

        return modelMapper.map(potencialSeveridadeDanos, GetPotencialSeveridadeDanosDTO.class);
    }
    
    public GetPotencialSeveridadeDanosDTO criarPotencialSeveridadeDanosIncluirRiscos(PostPotencialSeveridadeDanosIncluirRiscosDTO dto) {
        Potencial_Severidade_Danos potencialSeveridadeDanos = modelMapper.map(dto, Potencial_Severidade_Danos.class);
        potencialSeveridadeDanos.setIdPotencialSeveridade(UUID.randomUUID());

        // Busca e associa o perigo com base no ID fornecido, se não for nulo
        if (dto.getIdRisco() != null) {
            riscosRepository.findById(dto.getIdRisco()).ifPresent(potencialSeveridadeDanos::setRiscos);
        }

        potencialSeveridadeDanos.setIdPotencialSeveridade(UUID.randomUUID());

        potencialSeveridadeDanos = potencialSeveridadeDanosRepository.save(potencialSeveridadeDanos);

        return modelMapper.map(potencialSeveridadeDanos, GetPotencialSeveridadeDanosDTO.class);
    }
    public GetPotencialSeveridadeDanosDTO editarPotencialSeveridadeDanos(PutPotencialSeveridadeDanosDTO dto) {
        // Busca o Potencial_Severidade_Danos no banco de dados com base no ID fornecido
        UUID idPotencialSeveridade = dto.getIdPotencialSeveridade();
        if (idPotencialSeveridade == null) {
            return null;
        }

        // Busca o Potencial_Severidade_Danos no banco de dados com base no ID fornecido
        Potencial_Severidade_Danos potencialSeveridadeDanos = potencialSeveridadeDanosRepository.findById(idPotencialSeveridade)
                .orElseThrow(() -> new NoSuchElementException("Potencial_Severidade_Danos não encontrado com o ID: " + idPotencialSeveridade));

        // Mapeia os dados do DTO para a entidade existente
        modelMapper.map(dto, potencialSeveridadeDanos);  
        // Salva as alterações
        potencialSeveridadeDanos = potencialSeveridadeDanosRepository.save(potencialSeveridadeDanos);

        // Mapeia a entidade atualizada de volta para o DTO de resposta
        return modelMapper.map(potencialSeveridadeDanos, GetPotencialSeveridadeDanosDTO.class);
    }
    
    public GetPotencialSeveridadeDanosDTO editarPotencialSeveridadeDanosIncluirRiscos(PutPotencialSeveridadeDanosIncluirRiscosDTO dto) {
        // Busca o Potencial_Severidade_Danos no banco de dados com base no ID fornecido
        UUID idPotencialSeveridade = dto.getIdPotencialSeveridade();
        if (idPotencialSeveridade == null) {
            return null;
        }

        // Busca o Potencial_Severidade_Danos no banco de dados com base no ID fornecido
        Potencial_Severidade_Danos potencialSeveridadeDanos = potencialSeveridadeDanosRepository.findById(idPotencialSeveridade)
                .orElseThrow(() -> new NoSuchElementException("Potencial_Severidade_Danos não encontrado com o ID: " + idPotencialSeveridade));

        // Mapeia os dados do DTO para a entidade existente
        modelMapper.map(dto, potencialSeveridadeDanos);  
        // Salva as alterações
        potencialSeveridadeDanos = potencialSeveridadeDanosRepository.save(potencialSeveridadeDanos);

        // Mapeia a entidade atualizada de volta para o DTO de resposta
        return modelMapper.map(potencialSeveridadeDanos, GetPotencialSeveridadeDanosDTO.class);
    }
    public GetPotencialSeveridadeDanosDTO buscarPotencialSeveridadeDanosPorId(UUID id) {
        Potencial_Severidade_Danos potencialSeveridadeDanos = potencialSeveridadeDanosRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Potencial_Severidade_Danos não encontrado com o ID: " + id));
        return modelMapper.map(potencialSeveridadeDanos, GetPotencialSeveridadeDanosDTO.class);
    }

    public List<GetPotencialSeveridadeDanosDTO> buscarTodosPotencialSeveridadeDanos() {
        List<Potencial_Severidade_Danos> potencialSeveridadeDanosList = potencialSeveridadeDanosRepository.findAll();
        return potencialSeveridadeDanosList.stream()
                .map(potencialSeveridadeDanos -> modelMapper.map(potencialSeveridadeDanos, GetPotencialSeveridadeDanosDTO.class))
                .collect(Collectors.toList());
    }

    public void excluirPotencialSeveridadeDanos(UUID id) {
        potencialSeveridadeDanosRepository.deleteById(id);
    }

}
