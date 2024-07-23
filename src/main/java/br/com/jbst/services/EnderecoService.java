package br.com.jbst.services;


import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.jbst.DTO.GetEnderecoDTO;

import br.com.jbst.entities.Endereco;
import br.com.jbst.repositories.modulo1.IEnderecoEntityRepository;
import br.com.jbst.repositories.modulo1.IEnderecoRepository;

@Service
public class EnderecoService {

	@Autowired
	IEnderecoRepository ienderecoRepository;
    @Autowired
    IEnderecoEntityRepository  IEnderecoEntityRepository;
	@Autowired
	private ModelMapper modelMapper;
	
	
	public List<GetEnderecoDTO> buscarTodosEnderecos() {
		List<Endereco> enderecos = ienderecoRepository.findAll();
		return enderecos.stream().map(this::mapearEnderecoParaDTO).collect(Collectors.toList());
	}

	public GetEnderecoDTO buscarEnderecoPorId(UUID idEndereco) throws Exception {
		Endereco endereco = ienderecoRepository.findById(idEndereco)
				.orElseThrow(() -> new Exception("Endereço não encontrado com o ID: " + idEndereco));
		return mapearEnderecoParaDTO(endereco);
	}

	private GetEnderecoDTO mapearEnderecoParaDTO(Endereco endereco) {
		return modelMapper.map(endereco, GetEnderecoDTO.class);
	}
	
	
}
