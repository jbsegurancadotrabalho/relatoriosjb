package br.com.jbst.services;


import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.jbst.DTO.GetSistemasDeSegurancaDTO;
import br.com.jbst.DTO.PostSistemasDeSegurancaDTO;
import br.com.jbst.DTO.PutSistemasDeSegurancaDTO;
import br.com.jbst.entities.Maquinas;
import br.com.jbst.entities.SistemasDeSeguranca;
import br.com.jbst.repositories.modulo2.ISistemasDeSegurancaRepository;

@Service
public class SistemasDeSegurancaService {

    @Autowired
    private ISistemasDeSegurancaRepository sistemasDeSegurancaRepository;
    
    @Autowired
    private ModelMapper modelMapper;

    public GetSistemasDeSegurancaDTO criarSistemasDeSeguranca(PostSistemasDeSegurancaDTO dto) {
        SistemasDeSeguranca sistemasDeSeguranca = modelMapper.map(dto, SistemasDeSeguranca.class);

        // Setando o ID manualmente, caso necessário
        sistemasDeSeguranca.setIdsistemas_de_seguranca(UUID.randomUUID());

        // Mapeando a associação com a entidade Maquinas
        if (dto.getIdMaquina() != null) {
            Maquinas maquina = new Maquinas();
            maquina.setIdMaquina(dto.getIdMaquina());
            sistemasDeSeguranca.setMaquinas(maquina);
        }

        sistemasDeSeguranca = sistemasDeSegurancaRepository.save(sistemasDeSeguranca);
        return modelMapper.map(sistemasDeSeguranca, GetSistemasDeSegurancaDTO.class);
    }
    public GetSistemasDeSegurancaDTO editarSistemasDeSeguranca(PutSistemasDeSegurancaDTO dto) {
        // Verifica se o ID da entidade é válido
        UUID idSistemasDeSeguranca = dto.getIdsistemas_de_seguranca();
        if (idSistemasDeSeguranca == null) {
            return null; // ou lançar uma exceção, dependendo da sua lógica de negócios
        }

        // Busca a entidade no banco de dados com base no ID fornecido
        SistemasDeSeguranca sistemasDeSegurancaExistente = sistemasDeSegurancaRepository.findById(idSistemasDeSeguranca)
                .orElseThrow(() -> new NoSuchElementException("Sistemas de Segurança não encontrado com o ID: " + idSistemasDeSeguranca));

        // Mapeia os dados do DTO para a entidade existente, excluindo o ID da máquina se estiver presente
        modelMapper.map(dto, sistemasDeSegurancaExistente, "maquinaId");

        // Mapeando a associação com a entidade Maquinas, se o ID da máquina estiver presente no DTO
        if (dto.getIdMaquina() != null) {
            Maquinas maquina = new Maquinas();
            maquina.setIdMaquina(dto.getIdMaquina());
            sistemasDeSegurancaExistente.setMaquinas(maquina);
        } else {
            sistemasDeSegurancaExistente.setMaquinas(null);
        }

        // Salva as alterações
        sistemasDeSegurancaExistente = sistemasDeSegurancaRepository.save(sistemasDeSegurancaExistente);

        // Mapeia a entidade atualizada de volta para o DTO de resposta
        return modelMapper.map(sistemasDeSegurancaExistente, GetSistemasDeSegurancaDTO.class);
    }

    public GetSistemasDeSegurancaDTO buscarPorId(UUID id) {
        SistemasDeSeguranca sistemasDeSeguranca = sistemasDeSegurancaRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Sistemas de Segurança não encontrado com o ID: " + id));
        return modelMapper.map(sistemasDeSeguranca, GetSistemasDeSegurancaDTO.class);
    }

    public List<GetSistemasDeSegurancaDTO> buscarTodosSistemasDeSeguranca() {
        List<SistemasDeSeguranca> sistemasDeSegurancaList = sistemasDeSegurancaRepository.findAll();
        return sistemasDeSegurancaList.stream()
                .map(sistemasDeSeguranca -> modelMapper.map(sistemasDeSeguranca, GetSistemasDeSegurancaDTO.class))
                .collect(Collectors.toList());
    }

    public void excluirSistemasDeSeguranca(UUID id) {
        SistemasDeSeguranca sistemasDeSeguranca = sistemasDeSegurancaRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Sistemas de Segurança não encontrado com o ID: " + id));
        sistemasDeSegurancaRepository.delete(sistemasDeSeguranca);
    }
}
