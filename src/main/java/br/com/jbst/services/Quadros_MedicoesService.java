package br.com.jbst.services;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.jbst.DTO2.GetQuadros_MedicoesDTO;
import br.com.jbst.DTO2.PostQuadros_MedicoesDTO;
import br.com.jbst.DTO2.PutQuadros_MedicoesDTO;
import br.com.jbst.entities.LaudoEletrico.Quadros_Medicoes;
import br.com.jbst.repositories.modulo2.IInspecaoRepository;
import br.com.jbst.repositories.modulo2.IQuadros_MedicoesRepository;

@Service
public class Quadros_MedicoesService {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    IQuadros_MedicoesRepository IQuadros_MedicoesRepository;

    @Autowired
    IInspecaoRepository inspecaoRepository;
    
    
    public GetQuadros_MedicoesDTO criarQuadrosMedicoes(PostQuadros_MedicoesDTO dto) {
        try {
            Quadros_Medicoes quadros_medicoes = modelMapper.map(dto, Quadros_Medicoes.class);
            quadros_medicoes.setIdQuadros_Medicoes(UUID.randomUUID());
            quadros_medicoes.setInspecao(inspecaoRepository.getById(dto.getIdInspecao()));
            Quadros_Medicoes quadros_medicao = IQuadros_MedicoesRepository.save(quadros_medicoes);
            return modelMapper.map(quadros_medicao, GetQuadros_MedicoesDTO.class);
        } catch (Exception e) {
            // Tratar a exceção aqui
            e.printStackTrace(); // Isso é apenas para mostrar o erro no console, você pode lidar com ele de outra forma
            throw new RuntimeException("Ocorreu um erro ao criar os quadros de medições: " + e.getMessage());
        }
    }
    public GetQuadros_MedicoesDTO editarQuadrosMedicoes(PutQuadros_MedicoesDTO dto) {
        try {
            // Verificar se o quadro de medições com o ID fornecido existe
            if (!IQuadros_MedicoesRepository.existsById(dto.getIdQuadros_Medicoes())) {
                throw new Exception("O quadro de medições com o ID fornecido não existe.");
            }
            
            // Buscar o quadro de medições pelo ID
            Quadros_Medicoes quadros_medicoes = IQuadros_MedicoesRepository.findById(dto.getIdQuadros_Medicoes())
                .orElseThrow(() -> new Exception("Quadro de medições não encontrado com o ID fornecido."));
            
            // Mapear os novos dados do DTO para o quadro de medições existente
            modelMapper.map(dto, quadros_medicoes);
            
            // Associar a inspeção ao quadro de medições
            quadros_medicoes.setInspecao(inspecaoRepository.getById(dto.getIdInspecao()));
            
            // Salvar o quadro de medições atualizado no banco de dados
            Quadros_Medicoes quadros_medicaoAtualizado = IQuadros_MedicoesRepository.save(quadros_medicoes);
            
            // Mapear o quadro de medições atualizado para o DTO de resposta
            return modelMapper.map(quadros_medicaoAtualizado, GetQuadros_MedicoesDTO.class);
        } catch (Exception e) {
            // Tratar a exceção aqui
            e.printStackTrace(); // Isso é apenas para mostrar o erro no console, você pode lidar com ele de outra forma
            throw new RuntimeException("Ocorreu um erro ao editar os quadros de medições: " + e.getMessage());
        }
    }
        // Método para buscar quadro de medições por ID
        public GetQuadros_MedicoesDTO buscarQuadrosMedicoesPorId(UUID id) throws Exception {
            Optional<Quadros_Medicoes> quadrosMedicoesOptional = IQuadros_MedicoesRepository.findById(id);
            if (quadrosMedicoesOptional.isPresent()) {
                Quadros_Medicoes quadrosMedicoes = quadrosMedicoesOptional.get();
                return modelMapper.map(quadrosMedicoes, GetQuadros_MedicoesDTO.class);
            } else {
                throw new Exception("Quadro de medições não encontrado com o ID fornecido.");
            }
        }
        
        // Método para buscar todas as medições de quadros
        public List<GetQuadros_MedicoesDTO> buscarTodasQuadrosMedicoes() {
            List<Quadros_Medicoes> todasQuadrosMedicoes = IQuadros_MedicoesRepository.findAll();
            return todasQuadrosMedicoes.stream()
                .map(quadrosMedicoes -> modelMapper.map(quadrosMedicoes, GetQuadros_MedicoesDTO.class))
                .collect(Collectors.toList());
        
    }
        public void excluirQuadrosMedicoesPorId(UUID id) throws Exception {
            Optional<Quadros_Medicoes> quadrosMedicoesOptional = IQuadros_MedicoesRepository.findById(id);
            if (quadrosMedicoesOptional.isPresent()) {
                IQuadros_MedicoesRepository.delete(quadrosMedicoesOptional.get());
            } else {
                throw new Exception("Quadro de medições não encontrado com o ID fornecido.");
            }
        }
}
