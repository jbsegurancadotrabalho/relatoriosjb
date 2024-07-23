package br.com.jbst.services;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.jbst.DTO.GetPlantaDaUnidadeDTO;
import br.com.jbst.DTO.PostPlantaDaUnidadeDTO;
import br.com.jbst.DTO.PutPlantaDaUnidadeDTO;
import br.com.jbst.entities.PlantaDaUnidade;
import br.com.jbst.repositories.modulo1.IUnidadeDocRepository;
import br.com.jbst.repositories.modulo2.IPlantaDaUnidadeRepository;

@Service
public class PlantaDaUnidadeService {

    @Autowired
    private IPlantaDaUnidadeRepository iplantaDaUnidadeRepository;

    @Autowired
    private IUnidadeDocRepository iunidadeDocRepository;

    @Autowired
    private ModelMapper modelMapper;

    public GetPlantaDaUnidadeDTO criarPlantaDaUnidade(PostPlantaDaUnidadeDTO dto) throws Exception {
        try {
            // Verificar se o ID da unidade doc é nulo
            if (dto.getIdunidadedoc() == null) {
                throw new IllegalArgumentException("O ID da unidade doc não pode ser nulo.");
            }

            // Mapear o DTO para a entidade PlantaDaUnidade
            PlantaDaUnidade plantaDaUnidade = modelMapper.map(dto, PlantaDaUnidade.class);

            // Definir o ID da planta da unidade manualmente
            plantaDaUnidade.setIdPlanta(UUID.randomUUID());

            // Definir a unidade doc na entidade
            plantaDaUnidade.setUnidadedoc(iunidadeDocRepository.findById(dto.getIdunidadedoc()).orElse(null));

            // Salvar a entidade PlantaDaUnidade
            iplantaDaUnidadeRepository.save(plantaDaUnidade);

            // Retornar o DTO mapeado
            return modelMapper.map(plantaDaUnidade, GetPlantaDaUnidadeDTO.class);
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    public GetPlantaDaUnidadeDTO editarPlantaDaUnidade(PutPlantaDaUnidadeDTO dto) throws Exception {
        try {
            // Verificar se o ID da planta é nulo
            if (dto.getIdPlanta() == null) {
                throw new IllegalArgumentException("O ID da planta não pode ser nulo.");
            }

            // Verificar se o ID da unidade doc é nulo
            if (dto.getIdunidadedoc() == null) {
                throw new IllegalArgumentException("O ID da unidade doc não pode ser nulo.");
            }

            // Verificar se a planta existe no banco de dados
            Optional<PlantaDaUnidade> plantaOptional = iplantaDaUnidadeRepository.findById(dto.getIdPlanta());
            if (!plantaOptional.isPresent()) {
                throw new Exception("Planta da unidade não encontrada com o ID: " + dto.getIdPlanta());
            }

            // Mapear o DTO para a entidade PlantaDaUnidade
            PlantaDaUnidade plantaDaUnidade = modelMapper.map(dto, PlantaDaUnidade.class);

            // Definir a unidade doc na entidade
            plantaDaUnidade.setUnidadedoc(iunidadeDocRepository.findById(dto.getIdunidadedoc()).orElse(null));

            // Salvar a entidade PlantaDaUnidade
            PlantaDaUnidade updatedPlantaDaUnidade = iplantaDaUnidadeRepository.save(plantaDaUnidade);

            // Mapear a entidade atualizada para o DTO
            return modelMapper.map(updatedPlantaDaUnidade, GetPlantaDaUnidadeDTO.class);
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    public List<GetPlantaDaUnidadeDTO> consultarTodasAsPlantasDaUnidade() {
        List<PlantaDaUnidade> plantas = iplantaDaUnidadeRepository.findAll();
        return plantas.stream().map(planta -> modelMapper.map(planta, GetPlantaDaUnidadeDTO.class)).collect(Collectors.toList());
    }

    public GetPlantaDaUnidadeDTO consultarPlantaDaUnidadePorId(UUID id) throws Exception {
        Optional<PlantaDaUnidade> plantaOptional = iplantaDaUnidadeRepository.findById(id);
        if (plantaOptional.isPresent()) {
            PlantaDaUnidade planta = plantaOptional.get();
            return modelMapper.map(planta, GetPlantaDaUnidadeDTO.class);
        } else {
            throw new Exception("Planta da unidade não encontrada com o ID: " + id);
        }
    }

    public GetPlantaDaUnidadeDTO excluirPlantaDaUnidade(UUID id) throws Exception {
        Optional<PlantaDaUnidade> plantaOptional = iplantaDaUnidadeRepository.findById(id);
        if (plantaOptional.isPresent()) {
            iplantaDaUnidadeRepository.deleteById(id);
            // Como a planta foi excluída com sucesso, retornamos null
            return null;
        } else {
            throw new Exception("Planta da unidade não encontrada com o ID: " + id);
        }
    }

}
