package br.com.jbst.services;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import br.com.jbst.DTO.GetEnderecoDTO;
import br.com.jbst.DTO.GetExamesDTO;
import br.com.jbst.DTO.PostExamesDTO;
import br.com.jbst.DTO.PutExamesDTO;
import br.com.jbst.DTO1.GetCredenciados4DTO;
import br.com.jbst.DTO1.GetExames2DTO;
import br.com.jbst.DTO1.GetExamesCredenciados4DTO;
import br.com.jbst.DTO2.GetCredenciados2DTO;
import br.com.jbst.entities.Credenciados;
import br.com.jbst.entities.Endereco;
import br.com.jbst.entities.Exames;
import br.com.jbst.entities.ExamesCredenciados;
import br.com.jbst.repositories.modulo1.IExamesRepository;

@Service
public class ExamesServices {

	
	@Autowired
	IExamesRepository iexamesRepository;
	
	@Autowired
	private ModelMapper modelMapper;
	
	public GetExamesDTO criarExames(PostExamesDTO dto) throws Exception {
	    try {
	        Exames exames = modelMapper.map(dto, Exames.class);
	        
	        // Definindo um novo ID para o exame
	        exames.setIdExames(UUID.randomUUID());

	        // Salvando o exame com o novo ID
	        Exames novoExame = iexamesRepository.save(exames);
	        
	        // Mapeando o exame para o DTO de retorno
	        return modelMapper.map(novoExame, GetExamesDTO.class);
	    } catch (Exception e) {
	        throw new Exception("Erro ao criar exame: " + e.getMessage());
	    }
	}

	
	public GetExamesDTO editarExames(PutExamesDTO dto) throws Exception {
        if (dto == null) {
            throw new IllegalArgumentException("DTO de edição de exame não pode ser nulo.");
        }

        if (dto.getIdExames() == null) {
            throw new IllegalArgumentException("ID do exame não pode ser nulo.");
        }

        Exames exameExistente = iexamesRepository.findById(dto.getIdExames())
                .orElseThrow(() -> new Exception("Exame não encontrado com o ID: " + dto.getIdExames()));

        modelMapper.map(dto, exameExistente);

        Exames exameAtualizado = iexamesRepository.save(exameExistente);

        return modelMapper.map(exameAtualizado, GetExamesDTO.class);
    }

	public List<GetExamesDTO> consultarExames() {
        List<Exames> exames = iexamesRepository.findAll();
        return exames.stream()
                .map(exame -> modelMapper.map(exame, GetExamesDTO.class))
                .collect(Collectors.toList());
    }
	


    public GetExamesDTO consultarExamePorId(UUID idExames) throws Exception {
        Exames exame = iexamesRepository.findById(idExames)
                .orElseThrow(() -> new Exception("Exame não encontrado com o ID: " + idExames));
        return modelMapper.map(exame, GetExamesDTO.class);
    }
    
    public GetExamesDTO excluirExame(UUID idExames) throws Exception {
        try {
            Exames exameExcluido = iexamesRepository.findById(idExames)
                    .orElseThrow(() -> new Exception("Exame não encontrado com o ID: " + idExames));

            iexamesRepository.deleteById(idExames);

            return modelMapper.map(exameExcluido, GetExamesDTO.class);
        } catch (EmptyResultDataAccessException e) {
            throw new Exception("Exame não encontrado com o ID: " + idExames);
        }
    }
    
   public List<GetExames2DTO> getExamesByNomeExameAndLocalidade(String nomeExame, String localidade, String uf, String bairro) {
       List<Exames> exames = iexamesRepository.findByNomeExameAndLocalidadeAndBairro(nomeExame, localidade, uf, bairro);
       return exames.stream()
                    .map(this::convertToDTO)
                   .collect(Collectors.toList());
    }

    private GetExames2DTO convertToDTO(Exames exame) {
        GetExames2DTO dto = new GetExames2DTO();
        dto.setIdExames(exame.getIdExames());
        dto.setNome_exame(exame.getNome_exame());
        dto.setTipo_exame(exame.getTipo_exame());
        dto.setDescricao_exame(exame.getDescricao_exame());

        if (exame.getExamescredenciados() != null) {
            List<GetExamesCredenciados4DTO> credenciadosDTO = exame.getExamescredenciados().stream()
                .map(this::convertToCredenciadoDTO1)
                .collect(Collectors.toList());
             dto.setExamescredenciados(credenciadosDTO);
        }

        return dto;
    }

    private GetExamesCredenciados4DTO convertToCredenciadoDTO1(ExamesCredenciados credenciado) {
        GetExamesCredenciados4DTO dto = new GetExamesCredenciados4DTO();
        dto.setIdExameCredenciado(credenciado.getIdExameCredenciado());
        dto.setValorCredenciado(credenciado.getValorCredenciado());
        dto.setValorJb(credenciado.getValorJb());

        // Adicione a conversão para GetCredenciados2DTO
        if (credenciado.getCredenciados() != null) {
            GetCredenciados4DTO credenciadoDTO = convertToCredenciadoDTO(credenciado.getCredenciados());
            dto.setCredenciados(credenciadoDTO);
        }

        return dto;
    }

    private GetCredenciados4DTO convertToCredenciadoDTO(Credenciados credenciado) {
        GetCredenciados4DTO dto = new GetCredenciados4DTO();
        dto.setIdCredenciado(credenciado.getIdCredenciado());
        dto.setRazaosocial(credenciado.getRazaosocial());
        dto.setNomeCredenciado(credenciado.getNomeCredenciado());
        dto.setCnpj(credenciado.getCnpj());
        dto.setStatus(credenciado.getStatus());
        dto.setInscricaomunicipal(credenciado.getInscricaomunicipal());
        dto.setResponsavel_sistema(credenciado.getResponsavel_sistema());
        dto.setEmail_usuario(credenciado.getEmail_usuario());
        dto.setSenha_sistema(credenciado.getSenha_sistema());
        dto.setTelefone_responsavel(credenciado.getTelefone_responsavel());

        // Adicione a conversão para GetEnderecoDTO
        if (credenciado.getEndereco() != null) {
            GetEnderecoDTO enderecoDTO = convertToEnderecoDTO(credenciado.getEndereco());
            dto.setEndereco(enderecoDTO);
        }

        return dto;
    }

    private GetEnderecoDTO convertToEnderecoDTO(Endereco endereco) {
        GetEnderecoDTO dto = new GetEnderecoDTO();
        dto.setIdEndereco(endereco.getIdEndereco());
        dto.setDataHoraCriacao(endereco.getDataHoraCriacao());
        dto.setCep(endereco.getCep());
        dto.setLogradouro(endereco.getLogradouro());
        dto.setComplemento(endereco.getComplemento());
        dto.setNumero(endereco.getNumero());
        dto.setBairro(endereco.getBairro());
        dto.setLocalidade(endereco.getLocalidade());
        dto.setUf(endereco.getUf());
        
        return dto;
    }
  
}
