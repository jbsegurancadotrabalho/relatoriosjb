package br.com.jbst.services;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.jbst.DTO2.GetTransformadoresDTO;
import br.com.jbst.DTO2.PostTransformadoresDTO;
import br.com.jbst.DTO2.PutTransformadoresDTO;
import br.com.jbst.entities.LaudoEletrico.Transformadores;
import br.com.jbst.repositories.modulo1.ITransformadorRepository;
import br.com.jbst.repositories.modulo2.IInspecaoRepository;

@Service
public class TransformadoresService {

	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	ITransformadorRepository transformadorRepository;
	
	@Autowired
	IInspecaoRepository inspecaoRepository;
	
	
	 public GetTransformadoresDTO criarTransformador(PostTransformadoresDTO dto) throws Exception {
	        // Mapear os dados do DTO para a entidade Transformadores
	        Transformadores transformador = modelMapper.map(dto, Transformadores.class);
	        
	        // Verificar se a inspeção associada ao transformador existe
	        if (!inspecaoRepository.existsById(dto.getIdInspecao())) {
	            throw new Exception("A inspeção com o ID fornecido não existe.");
	        }
	        
	        // Definir a inspeção para o transformador
	        transformador.setInspecao(inspecaoRepository.getById(dto.getIdInspecao()));
	        transformador.setIdTransformadores(UUID.randomUUID());
	        // Salvar o transformador no banco de dados
	        Transformadores novoTransformador = transformadorRepository.save(transformador);
	        
	        // Mapear o transformador criado para o DTO de resposta
	        GetTransformadoresDTO transformadorDTO = modelMapper.map(novoTransformador, GetTransformadoresDTO.class);
	        
	        return transformadorDTO;
	    }
	 
	 public GetTransformadoresDTO editarTransformador(PutTransformadoresDTO dto) throws Exception {
		    // Buscar o transformador no banco de dados com base no ID da inspeção
		    Transformadores transformador = transformadorRepository.findById(dto.getIdTransformadores())
		        .orElseThrow(() -> new Exception("Não foi encontrado um transformador associado à inspeção com o ID fornecido."));
		    
		    // Atualizar os dados do transformador com os valores fornecidos no DTO
		    modelMapper.map(dto, transformador);
		    transformador.setInspecao(inspecaoRepository.getById(dto.getIdInspecao()));

		    // Salvar o transformador atualizado no banco de dados
		    Transformadores transformadorAtualizado = transformadorRepository.save(transformador);
		    
		    // Mapear o transformador atualizado para o DTO de resposta
		    GetTransformadoresDTO transformadorDTO = modelMapper.map(transformadorAtualizado, GetTransformadoresDTO.class);
		    
		    return transformadorDTO;
		}

	 public GetTransformadoresDTO buscarPorId(UUID id) throws Exception {
	        Transformadores transformador = transformadorRepository.findById(id)
	            .orElseThrow(() -> new Exception("Transformador não encontrado com o ID fornecido."));
	        
	        return modelMapper.map(transformador, GetTransformadoresDTO.class);
	    }
	    
	    public List<GetTransformadoresDTO> buscarTodos() {
	        List<Transformadores> transformadores = transformadorRepository.findAll();
	        return transformadores.stream()
	            .map(transformador -> modelMapper.map(transformador, GetTransformadoresDTO.class))
	            .collect(Collectors.toList());
	    }
	    
	    public void excluirTransformador(UUID id) throws Exception {
	        Optional<Transformadores> transformador = transformadorRepository.findById(id);
	        if (transformador.isPresent()) {
	            transformadorRepository.delete(transformador.get());
	        } else {
	            throw new Exception("Transformador não encontrado com o ID fornecido.");
	        }
	    }
}
