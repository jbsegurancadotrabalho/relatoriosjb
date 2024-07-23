package br.com.jbst.services;


import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.jbst.DTO.GetPosturaDTO;
import br.com.jbst.DTO.PostPosturaDTO;
import br.com.jbst.DTO.PutPosturaDTO;
import br.com.jbst.entities.Postura;
import br.com.jbst.repositories.modulo2.IGho_SetorRepository;
import br.com.jbst.repositories.modulo2.IPosturaRepository;


@Service
public class PosturaServices {

    @Autowired
    private IPosturaRepository posturaRepository;

    @Autowired
    private IGho_SetorRepository ghoSetorRepository;

    @Autowired
    private ModelMapper modelMapper;

    public GetPosturaDTO criarPostura(PostPosturaDTO dto) throws Exception {
        try {
            // Verificar se o ID do GHO Setor é nulo
            UUID idGhoSetor = dto.getId_gho_setor();
            if (idGhoSetor == null) {
                throw new IllegalArgumentException("O ID do GHO Setor não pode ser nulo.");
            }

            // Mapear o DTO para a entidade Postura
            Postura postura = modelMapper.map(dto, Postura.class);

            // Definir o ID da postura manualmente
            postura.setIdPostura(UUID.randomUUID());

            // Definir o GHO Setor na entidade
            postura.setGho_setor(ghoSetorRepository.findById(idGhoSetor).orElse(null));

            // Salvar a entidade Postura
            Postura savedPostura = posturaRepository.save(postura);

            // Retornar o DTO mapeado da entidade salva
            return modelMapper.map(savedPostura, GetPosturaDTO.class);
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }
    
    public GetPosturaDTO editarPostura(PutPosturaDTO dto) throws Exception {
        try {
            // Aqui você deve obter os dados de edição de alguma fonte
            // Por exemplo, você poderia buscar os dados da postura a ser editada de algum lugar,
            // como a sessão, o banco de dados, um arquivo, etc.
            
            // Suponha que você obtenha o ID da postura a ser editada de alguma fonte
            UUID idPostura = dto.getIdPostura();
            
            // Verificar se o ID da postura é nulo
            if (idPostura == null) {
                throw new IllegalArgumentException("O ID da postura não pode ser nulo.");
            }

            // Verificar se a postura existe
            Optional<Postura> optionalPostura = posturaRepository.findById(idPostura);
            if (!optionalPostura.isPresent()) {
                throw new IllegalArgumentException("Postura não encontrada com o ID fornecido: " + idPostura);
            }

            // Obter a postura a ser editada
            Postura postura = optionalPostura.get();

            // Aqui você pode obter os dados de edição de alguma fonte
            // Por exemplo, você pode obter os dados da postura a ser editada de algum formulário,
            // de uma requisição HTTP, de um arquivo, etc.
            // Suponha que você obtenha os dados de edição em algum formato, como PutPosturaDTO
            
            // Mapear os campos atualizados do DTO para a entidade Postura
            modelMapper.map(dto, postura);

            // Verificar se o ID do GHO Setor é fornecido no DTO de edição
            UUID idGhoSetor = dto.getIdGhoSetor();
            if (idGhoSetor != null) {
                // Definir o GHO Setor na entidade
                postura.setGho_setor(ghoSetorRepository.findById(idGhoSetor).orElse(null));
            }

            // Salvar as alterações na entidade Postura
            Postura updatedPostura = posturaRepository.save(postura);

            // Retornar o DTO mapeado da entidade atualizada
            return modelMapper.map(updatedPostura, GetPosturaDTO.class);
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    public GetPosturaDTO buscarPosturaPorId(UUID id) throws Exception {
        try {
            // Encontrar a postura pelo ID
            Optional<Postura> optionalPostura = posturaRepository.findById(id);

            // Verificar se a postura existe
            if (!optionalPostura.isPresent()) {
                throw new IllegalArgumentException("Postura não encontrada com o ID fornecido: " + id);
            }

            // Mapear a postura para o DTO GetPosturaDTO
            Postura postura = optionalPostura.get();
            GetPosturaDTO posturaDTO = modelMapper.map(postura, GetPosturaDTO.class);

            return posturaDTO;
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    public List<GetPosturaDTO> buscarTodasPosturas() {
        List<Postura> todasPosturas = posturaRepository.findAll();

        // Mapear a lista de entidades Postura para uma lista de DTOs GetPosturaDTO
        List<GetPosturaDTO> dtos = todasPosturas.stream()
            .map(postura -> modelMapper.map(postura, GetPosturaDTO.class))
            .collect(Collectors.toList());

        return dtos;
    }
    public GetPosturaDTO excluirPostura(UUID id) throws Exception {
        try {
            // Verificar se a postura existe antes de excluí-la
            Optional<Postura> optionalPostura = posturaRepository.findById(id);
            if (!optionalPostura.isPresent()) {
                throw new IllegalArgumentException("Postura não encontrada com o ID fornecido: " + id);
            }

            // Excluir a postura
            Postura posturaExcluida = optionalPostura.get();
            posturaRepository.deleteById(id);

            // Retornar o DTO mapeado da postura excluída
            return modelMapper.map(posturaExcluida, GetPosturaDTO.class);
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }
    
    
}
