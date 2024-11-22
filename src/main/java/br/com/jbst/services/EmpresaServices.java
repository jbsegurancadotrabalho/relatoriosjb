package br.com.jbst.services;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.jbst.DTO.GetAllEmpresaDTO;
import br.com.jbst.DTO.GetEmpresaDTO;
import br.com.jbst.DTO.Empresas.Unidades.GetEmpresa3DTO;
import br.com.jbst.DTO.Funcionarios.Documentos.GetEmpresa1DTO;
import br.com.jbst.entities.Empresa;
import br.com.jbst.repositories.modulo1.IEmpresaRepository;


@Service
public class EmpresaServices {
    
    @Autowired
    private IEmpresaRepository empresaRepository;
    
    @Autowired
    private ModelMapper modelMapper;

    public GetEmpresaDTO buscarEmpresaPorId(UUID id) {
        Optional<Empresa> optionalEmpresa = empresaRepository.findById(id);
        if (optionalEmpresa.isPresent()) {
            Empresa empresa = optionalEmpresa.get();
            return modelMapper.map(empresa, GetEmpresaDTO.class);
        } else {
            return null; // Ou lançar uma exceção indicando que a empresa não foi encontrada
        }
    }
    
    public GetEmpresa3DTO buscarUnidadesEmpresaPorId(UUID id) {
        Optional<Empresa> optionalEmpresa = empresaRepository.findById(id);
        if (optionalEmpresa.isPresent()) {
            Empresa empresa = optionalEmpresa.get();
            return modelMapper.map(empresa, GetEmpresa3DTO.class);
        } else {
            return null; 
        }
    }

  


        public List<GetAllEmpresaDTO> buscarTodasEmpresasComDocumentosEFuncionarios() {
    		List<Empresa> empresa = empresaRepository.findAll();
    		return empresa.stream().map(empresas -> modelMapper.map(empresas, GetAllEmpresaDTO.class)).collect(Collectors.toList());
    	}
   
        public List<GetEmpresa1DTO> buscarTodasEmpresasDocumentosFreeFuncionarios() {
    		List<Empresa> empresa = empresaRepository.findAll();
    		return empresa.stream().map(empresas -> modelMapper.map(empresas, GetEmpresa1DTO.class)).collect(Collectors.toList());
    	}
        
        public Optional<GetAllEmpresaDTO> buscarEmpresaPorCnpj(String cnpj) {
            Optional<Empresa> empresaOptional = empresaRepository.findByCnpj(cnpj);
            return empresaOptional.map(empresa -> modelMapper.map(empresa, GetAllEmpresaDTO.class));
        }

}
