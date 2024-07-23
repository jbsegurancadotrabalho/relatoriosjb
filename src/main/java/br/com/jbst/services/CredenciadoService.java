package br.com.jbst.services;

import java.time.Instant;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.jbst.repositories.modulo1.ICredenciadosRepository;
import br.com.jbst.repositories.modulo1.IEnderecoRepository;
import br.com.jbst.DTO2.GetCredenciadosDTO;
import br.com.jbst.DTO2.PostCredenciadosDTO;
import br.com.jbst.DTO2.PutCredenciadosDTO;
import br.com.jbst.entities.Credenciados;

@Service
public class CredenciadoService {
	
	@Autowired
	ICredenciadosRepository icredenciadoRepository;
	
	@Autowired
	IEnderecoRepository ienderecoRepository;
	
	@Autowired
	private ModelMapper modelMapper;
	
	
	public GetCredenciadosDTO criarCredenciados(PostCredenciadosDTO dto) throws Exception {
	    try {
	        // Verifica se já existe um credenciado com o mesmo CNPJ
	        if (icredenciadoRepository.existsByCnpj(dto.getCnpj())) {
	            throw new IllegalArgumentException("Já existe um credenciado com o mesmo CNPJ");
	        }
	        
	        // Mapeia o DTO para a entidade Credenciados
	        Credenciados credenciados = modelMapper.map(dto, Credenciados.class);
	        
	        // Define o ID e a data/hora de criação
	        credenciados.setIdCredenciado(UUID.randomUUID());
	        credenciados.setDataHoraCriacao(Instant.now());
	        
	        // Busca e define o endereço com base no ID fornecido no DTO
	        credenciados.setEndereco(ienderecoRepository.findById(dto.getIdEndereco()).orElse(null));
	        
	        // Salva o novo credenciado no banco de dados
	        return modelMapper.map(icredenciadoRepository.save(credenciados), GetCredenciadosDTO.class);
	    } catch (Exception e) {
	        throw new Exception("Erro ao criar credenciados: " + e.getMessage());
	    }
	}

	
	public GetCredenciadosDTO editarCredenciados(PutCredenciadosDTO dto) throws Exception {
        try {
            // Verifica se o ID do credenciado a ser editado está presente no DTO
            if (dto.getIdCredenciado() == null) {
                throw new IllegalArgumentException("ID do credenciado não fornecido");
            }
            
            // Busca o credenciado pelo ID no banco de dados
            Credenciados credenciados = icredenciadoRepository.findById(dto.getIdCredenciado())
                .orElseThrow(() -> new IllegalArgumentException("Credenciado não encontrado"));
            
            // Mapeia os dados do DTO para o objeto Credenciados
            modelMapper.map(dto, credenciados);
            
            // Atualiza a data e hora da modificação
            credenciados.setDataHoraCriacao(Instant.now());
            
            // Salva as mudanças no banco de dados
            Credenciados credenciadosAtualizados = icredenciadoRepository.save(credenciados);
            
            // Mapeia os dados do credenciado atualizado para GetCredenciadosDTO e retorna
            return modelMapper.map(credenciadosAtualizados, GetCredenciadosDTO.class);
        } catch (Exception e) {
            throw new Exception("Erro ao editar credenciados: " + e.getMessage());
        }
    }
	
	public GetCredenciadosDTO buscarPorId(UUID id) throws Exception {
        try {
            Optional<Credenciados> credenciadosOptional = icredenciadoRepository.findById(id);
            if (credenciadosOptional.isPresent()) {
                return modelMapper.map(credenciadosOptional.get(), GetCredenciadosDTO.class);
            } else {
                throw new IllegalArgumentException("Credenciado não encontrado");
            }
        } catch (Exception e) {
            throw new Exception("Erro ao buscar credenciado por ID: " + e.getMessage());
        }
    }
    
    public List<GetCredenciadosDTO> buscarTodosCredenciados() throws Exception {
        try {
            List<Credenciados> credenciadosList = icredenciadoRepository.findAll();
            return credenciadosList.stream()
                    .map(credenciados -> modelMapper.map(credenciados, GetCredenciadosDTO.class))
                    .collect(Collectors.toList());
        } catch (Exception e) {
            throw new Exception("Erro ao buscar todos os credenciados: " + e.getMessage());
        }
    }
    
    public void deletarCredenciados(UUID id) throws Exception {
        try {
            icredenciadoRepository.deleteById(id);
        } catch (Exception e) {
            throw new Exception("Erro ao deletar credenciados: " + e.getMessage());
        }
    }
}

