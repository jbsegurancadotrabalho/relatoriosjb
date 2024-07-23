package br.com.jbst.services;


import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.jbst.DTO2.GetSubstacoesDTO;
import br.com.jbst.DTO2.PostSubstacoesDTO;
import br.com.jbst.DTO2.PutSubstacoesDTO;
import br.com.jbst.entities.LaudoEletrico.Substacoes;
import br.com.jbst.repositories.modulo2.IInspecaoRepository;
import br.com.jbst.repositories.modulo2.ISubstacoesRepository;

@Service
public class SubstacoesServices {
    
    @Autowired
    private ModelMapper modelMapper;
    
    @Autowired
    ISubstacoesRepository  substacoesRepository;
    
    @Autowired
    IInspecaoRepository  inspecaoRepository;
    
    
    public GetSubstacoesDTO criarSubstacoes(PostSubstacoesDTO dto) throws Exception {
        // Verificar se a inspeção com o ID fornecido existe
        if (!inspecaoRepository.existsById(dto.getIdInspecao())) {
            throw new Exception("A inspeção com o ID fornecido não existe.");
        }
        
        // Mapear os dados do DTO para a entidade Substacoes
        Substacoes substacoes = modelMapper.map(dto, Substacoes.class);
        
        // Definir um UUID para a substação
        substacoes.setIdSubstacoes(UUID.randomUUID());
        
        // Associar a inspeção à substação
        substacoes.setInspecao(inspecaoRepository.getById(dto.getIdInspecao()));
        
        // Salvar a substação no banco de dados
        Substacoes novaSubstacao = substacoesRepository.save(substacoes);
        
        // Mapear a substação criada para o DTO de resposta
        return modelMapper.map(novaSubstacao, GetSubstacoesDTO.class);
    }
    
    public GetSubstacoesDTO editarSubstacoes(PutSubstacoesDTO dto) throws Exception {
        // Verificar se a subestação com o ID fornecido existe
        UUID id = dto.getIdSubstacoes();
        if (!substacoesRepository.existsById(id)) {
            throw new Exception("A subestação com o ID fornecido não existe.");
        }
        
        // Buscar a subestação pelo ID
        Substacoes substacao = substacoesRepository.findById(id)
            .orElseThrow(() -> new Exception("Subestação não encontrada com o ID fornecido."));
        
        // Mapear os novos dados do DTO para a subestação existente
        modelMapper.map(dto, substacao);
        
        // Associar a inspeção à subestação
        substacao.setInspecao(inspecaoRepository.getById(dto.getIdInspecao()));
        
        // Salvar a subestação atualizada no banco de dados
        Substacoes substacaoAtualizada = substacoesRepository.save(substacao);
        
        // Mapear a subestação atualizada para o DTO de resposta
        return modelMapper.map(substacaoAtualizada, GetSubstacoesDTO.class);
    }
    public GetSubstacoesDTO buscarSubstacaoPorId(UUID id) throws Exception {
        // Buscar a subestação pelo ID
        Substacoes substacao = substacoesRepository.findById(id)
            .orElseThrow(() -> new Exception("Subestação não encontrada com o ID fornecido."));
        
        // Mapear a subestação para o DTO de resposta
        return modelMapper.map(substacao, GetSubstacoesDTO.class);
    }
    public List<GetSubstacoesDTO> buscarTodasSubstacoes() {
        List<Substacoes> todasSubstacoes = substacoesRepository.findAll();
        return todasSubstacoes.stream()
            .map(substacao -> modelMapper.map(substacao, GetSubstacoesDTO.class))
            .collect(Collectors.toList());
    }
    
    public void excluirSubstacaoPorId(UUID id) throws Exception {
        Optional<Substacoes> substacaoOptional = substacoesRepository.findById(id);
        if (substacaoOptional.isPresent()) {
            substacoesRepository.delete(substacaoOptional.get());
        } else {
            throw new Exception("Substação não encontrada com o ID fornecido.");
        }
    }
}

