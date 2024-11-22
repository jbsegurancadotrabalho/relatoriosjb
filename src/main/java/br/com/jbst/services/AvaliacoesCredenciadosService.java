package br.com.jbst.services;


import br.com.jbst.DTO2.GetAvaliacoesCredenciadosDTO;
import br.com.jbst.DTO2.PostAvaliacoesCredenciadosDTO;
import br.com.jbst.DTO2.PutAvaliacoesCredenciadosDTO;
import br.com.jbst.entities.AvaliacoesCredenciados;
import br.com.jbst.repositories.modulo1.IAvaliacoesCredenciadosRepository;
import br.com.jbst.repositories.modulo1.IAvaliacoesRepository;
import br.com.jbst.repositories.modulo1.ICredenciadosRepository;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class AvaliacoesCredenciadosService {

    @Autowired
    IAvaliacoesCredenciadosRepository iavaliacoesCredenciadosRepository;

    @Autowired
    IAvaliacoesRepository avaliacoesRepository;
    
	@Autowired
	ICredenciadosRepository icredenciadoRepository;
	
    @Autowired
    private ModelMapper modelMapper;

    public GetAvaliacoesCredenciadosDTO criarAvaliacoesCredenciados(PostAvaliacoesCredenciadosDTO dto) {
        AvaliacoesCredenciados avaliacoesCredenciados = modelMapper.map(dto, AvaliacoesCredenciados.class);
        avaliacoesCredenciados.setIdAvaliacoesCredenciados(UUID.randomUUID());
        avaliacoesCredenciados.setAvaliacoes(avaliacoesRepository.findById(dto.getIdAvaliacoes()).orElse(null));
        avaliacoesCredenciados.setCredenciados( icredenciadoRepository.findById(dto.getIdCredenciado()).orElse(null));
        avaliacoesCredenciados = iavaliacoesCredenciadosRepository.save(avaliacoesCredenciados);
        return modelMapper.map(avaliacoesCredenciados, GetAvaliacoesCredenciadosDTO.class);
    }

    public GetAvaliacoesCredenciadosDTO atualizarAvaliacoesCredenciados(PutAvaliacoesCredenciadosDTO dto) {
        // Verifique se o DTO contém um ID para identificar o objeto a ser atualizado
        if (dto.getIdAvaliacoesCredenciados() == null) {
            throw new IllegalArgumentException("ID da AvaliacoesCredenciados não fornecido para atualização.");
        }

        // Recuperar o objeto AvaliacoesCredenciados com base no ID fornecido
        AvaliacoesCredenciados avaliacoesCredenciados = iavaliacoesCredenciadosRepository.findById(dto.getIdAvaliacoesCredenciados())
                .orElseThrow(() -> new RuntimeException("AvaliacoesCredenciados não encontrado com id: " + dto.getIdAvaliacoesCredenciados()));

        // Atualizando os campos com os dados do DTO
        modelMapper.map(dto, avaliacoesCredenciados);
        
        // Atualizando as associações com outras entidades
        avaliacoesCredenciados.setAvaliacoes(avaliacoesRepository.findById(dto.getIdAvaliacoes()).orElse(null));
        
        // Salvando as alterações no banco de dados
        avaliacoesCredenciados = iavaliacoesCredenciadosRepository.save(avaliacoesCredenciados);

        // Mapeando a entidade atualizada de volta para DTO
        return modelMapper.map(avaliacoesCredenciados, GetAvaliacoesCredenciadosDTO.class);
    }


    public List<GetAvaliacoesCredenciadosDTO> buscarTodasAvaliacoesCredenciados() {
        List<AvaliacoesCredenciados> avaliacoesCredenciadosList = iavaliacoesCredenciadosRepository.findAll();
        return avaliacoesCredenciadosList.stream()
                .map(avaliacoesCredenciados -> modelMapper.map(avaliacoesCredenciados, GetAvaliacoesCredenciadosDTO.class))
                .collect(Collectors.toList());
    }
    public GetAvaliacoesCredenciadosDTO buscarAvaliacaoCredenciadoPorId(UUID id) {
        AvaliacoesCredenciados avaliacaoCredenciado = iavaliacoesCredenciadosRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Avaliação de credenciado não encontrada com o ID: " + id));
        
        return modelMapper.map(avaliacaoCredenciado, GetAvaliacoesCredenciadosDTO.class);
    }
    public void excluirAvaliacaoCredenciadoPorId(UUID id) {
        AvaliacoesCredenciados avaliacaoCredenciado = iavaliacoesCredenciadosRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Avaliação de credenciado não encontrada com o ID: " + id));

        iavaliacoesCredenciadosRepository.delete(avaliacaoCredenciado);
    }


}


