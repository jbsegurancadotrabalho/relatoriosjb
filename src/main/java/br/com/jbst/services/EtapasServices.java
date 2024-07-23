package br.com.jbst.services;


import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.jbst.DTO.GetEtapasDTO;
import br.com.jbst.DTO.GetExamesDTO;
import br.com.jbst.DTO.PostEtapasIncluirCenarioDTO;
import br.com.jbst.DTO.PostEtapasIncluirDocumentosFreeDTO;
import br.com.jbst.DTO.PostEtapasIncluirFuncaoDocDTO;
import br.com.jbst.DTO.PutEtapasCenarioDTO;
import br.com.jbst.DTO.PutEtapasFuncaoDocDTO;
import br.com.jbst.DTO.PutExamesDTO;
import br.com.jbst.entities.Etapas;
import br.com.jbst.entities.Exames;
import br.com.jbst.entities.FuncaoDoc;
import br.com.jbst.repositories.modulo1.ICenarioRepository;
import br.com.jbst.repositories.modulo1.IDocumentosFreeRepositories;
import br.com.jbst.repositories.modulo1.IEtapasRepository;
import br.com.jbst.repositories.modulo1.IFuncaoDocCenariosRepository;
import br.com.jbst.repositories.modulo2.IFuncaoDocRepository;


@Service
public class EtapasServices {

    @Autowired
    private IEtapasRepository etapasRepository;

    @Autowired
    private ICenarioRepository cenarioRepository;

    @Autowired
    IFuncaoDocRepository ifuncaoDocRepository;
    
    @Autowired
    IDocumentosFreeRepositories idocumentosfreeRepository;
    
    @Autowired
    IFuncaoDocCenariosRepository ifuncaodocCenariosRepository;
    
    @Autowired
    private ModelMapper modelMapper;

    public GetEtapasDTO criarEtapaCenario(PostEtapasIncluirCenarioDTO dto) throws Exception {
        try {
            // Verificar se o ID do cenário é nulo
            UUID idCenario = dto.getIdCenario();
            if (idCenario == null) {
                throw new IllegalArgumentException("O ID do cenário não pode ser nulo.");
            }

            // Mapear o DTO para a entidade Etapas
            Etapas etapa = modelMapper.map(dto, Etapas.class);

            // Definir o ID da etapa manualmente
            etapa.setIdEtapas(UUID.randomUUID());

            // Definir o cenário na entidade
            etapa.setCenario(cenarioRepository.findById(dto.getIdCenario()).orElse(null));

            // Salvar a entidade Etapas
            etapasRepository.save(etapa);

            // Retornar o DTO mapeado
            return modelMapper.map(etapa, GetEtapasDTO.class);
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }
    
    public GetEtapasDTO criarEtapaDocumentosFree(PostEtapasIncluirDocumentosFreeDTO dto) throws Exception {
        try {
            // Verificar se o ID do cenário é nulo
            UUID idDocumentosFree = dto.getIdDocumentosFree();
            if (idDocumentosFree == null) {
                throw new IllegalArgumentException("O ID do documento não pode ser nulo.");
            }

            // Mapear o DTO para a entidade Etapas
            Etapas etapa = modelMapper.map(dto, Etapas.class);

            // Definir o ID da etapa manualmente
            etapa.setIdEtapas(UUID.randomUUID());

            // Definir o cenário na entidade
            etapa.setDocumentosfree(idocumentosfreeRepository.findById(dto.getIdDocumentosFree()).orElse(null));

            // Salvar a entidade Etapas
            etapasRepository.save(etapa);

            // Retornar o DTO mapeado
            return modelMapper.map(etapa, GetEtapasDTO.class);
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }
    public GetEtapasDTO criarEtapaFuncaodoc(PostEtapasIncluirFuncaoDocDTO dto) throws Exception {
        try {
            // Verificar se o ID do FuncaoDoc é nulo
            UUID idFuncaoDoc = dto.getIdFuncaoDoc();
            if (idFuncaoDoc == null) {
                throw new IllegalArgumentException("O ID do FuncaoDoc não pode ser nulo.");
            }

            // Verificar se o FuncaoDoc existe no banco de dados
            Optional<FuncaoDoc> optionalFuncaoDoc = ifuncaoDocRepository.findById(idFuncaoDoc);
            if (!optionalFuncaoDoc.isPresent()) {
                throw new IllegalArgumentException("O FuncaoDoc com o ID especificado não foi encontrado.");
            }
            
            // Mapear o DTO para a entidade Etapas
            Etapas etapa = modelMapper.map(dto, Etapas.class);

            // Definir o ID da etapa manualmente
            etapa.setIdEtapas(UUID.randomUUID());

            // Obter o FuncaoDocCenarios do Optional
            FuncaoDoc funcaoDoc = optionalFuncaoDoc.get();

            // Definir o FuncaoDoc na entidade Etapas
            etapa.setFuncaodoc(funcaoDoc);

            // Salvar a entidade Etapas
            etapasRepository.save(etapa);

            // Retornar o DTO mapeado
            return modelMapper.map(etapa, GetEtapasDTO.class);
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    
    public GetEtapasDTO editarEtapaCenario(PutEtapasCenarioDTO dto)  throws Exception {
        if (dto == null) {
            throw new IllegalArgumentException("DTO de edição de exame não pode ser nulo.");
        }

        if (dto.getIdEtapas() == null) {
            throw new IllegalArgumentException("ID do exame não pode ser nulo.");
        }

        Etapas etapaExistente = etapasRepository.findById(dto.getIdEtapas())
                .orElseThrow(() -> new Exception("Etapa não encontrado com o ID: " + dto.getIdEtapas()));

        modelMapper.map(dto, etapaExistente);

        Etapas etapaAtualizada = etapasRepository.save(etapaExistente);

        return modelMapper.map(etapaAtualizada, GetEtapasDTO.class);
    }


    
    public GetEtapasDTO editarEtapaFuncaoDoc(PutEtapasFuncaoDocDTO dto) throws Exception {
        if (dto == null) {
            throw new IllegalArgumentException("DTO de edição de exame não pode ser nulo.");
        }

        if (dto.getIdEtapas() == null) {
            throw new IllegalArgumentException("ID do exame não pode ser nulo.");
        }

        Etapas etapaExistente = etapasRepository.findById(dto.getIdEtapas())
                .orElseThrow(() -> new Exception("Exame não encontrado com o ID: " + dto.getIdEtapas()));

        modelMapper.map(dto, etapaExistente);

        Etapas etapaAtualizada = etapasRepository.save(etapaExistente);

        return modelMapper.map(etapaAtualizada, GetEtapasDTO.class);
    }


    public GetEtapasDTO buscarEtapaPorId(UUID id) throws Exception {
        try {
            // Encontrar a etapa pelo ID
            Optional<Etapas> optionalEtapa = etapasRepository.findById(id);

            // Verificar se a etapa existe
            if (!optionalEtapa.isPresent()) {
                throw new IllegalArgumentException("Etapa não encontrada com o ID fornecido: " + id);
            }

            // Mapear a etapa para o DTO GetEtapasDTO
            Etapas etapa = optionalEtapa.get();
            GetEtapasDTO etapaDTO = modelMapper.map(etapa, GetEtapasDTO.class);

            return etapaDTO;
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    public List<GetEtapasDTO> buscarTodasEtapas() {
        List<Etapas> todasEtapas = etapasRepository.findAll();

        // Mapear a lista de entidades Etapas para uma lista de DTOs GetEtapasDTO
        List<GetEtapasDTO> dtos = todasEtapas.stream()
            .map(etapa -> modelMapper.map(etapa, GetEtapasDTO.class))
            .collect(Collectors.toList());

        return dtos;
    }
    public GetEtapasDTO excluirEtapa(UUID id) throws Exception {
        try {
            // Verificar se a etapa existe antes de excluí-la
            Optional<Etapas> optionalEtapa = etapasRepository.findById(id);
            if (!optionalEtapa.isPresent()) {
                throw new IllegalArgumentException("Etapa não encontrada com o ID fornecido: " + id);
            }

            // Excluir a etapa
            Etapas etapaExcluida = optionalEtapa.get();
            etapasRepository.deleteById(id);

            // Retornar o DTO mapeado da etapa excluída
            return modelMapper.map(etapaExcluida, GetEtapasDTO.class);
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

}

