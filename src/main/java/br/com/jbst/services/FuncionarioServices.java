package br.com.jbst.services;



import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.jbst.DTO.Funcionarios.Documentos.GetFuncionarioDTO1;
import br.com.jbst.DTO.Unidades.Funcionarios.GetFuncionarioRelatorioDTO;
import br.com.jbst.dtos.relatorios.GetFuncionarioRelatoriosDTO;
import br.com.jbst.entities.Funcionario;
import br.com.jbst.repositories.modulo2.IFuncionarioRepository;

@Service
public class FuncionarioServices {
    
    @Autowired
    private IFuncionarioRepository funcionarioRepository;
    
    @Autowired
    private ModelMapper modelMapper;

  
    public List<GetFuncionarioRelatoriosDTO> buscarTodosOsFuncionarios() {
		List<Funcionario> funcionario = funcionarioRepository.findAll();
		return funcionario.stream().map(funcionarios -> modelMapper.map(funcionarios, GetFuncionarioRelatoriosDTO.class))
				.collect(Collectors.toList());

	}
    
    public List<GetFuncionarioDTO1> buscarTodosOsFuncionariosGerarRelatorios() {
		List<Funcionario> funcionario = funcionarioRepository.findAll();
		return funcionario.stream().map(funcionarios -> modelMapper.map(funcionarios, GetFuncionarioDTO1.class))
				.collect(Collectors.toList());

	}
    
    public GetFuncionarioRelatorioDTO buscarFuncionarioPorId(UUID id) {
        Optional<Funcionario> optionalFuncionario = funcionarioRepository.findById(id);
        if (optionalFuncionario.isPresent()) {
            return modelMapper.map(optionalFuncionario.get(), GetFuncionarioRelatorioDTO.class);
        } else {
            throw new RuntimeException("Funcionário não encontrado para o ID: " + id);
        }
    }


}
