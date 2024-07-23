package br.com.jbst.services;

import java.util.List;


import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.jbst.DTO.Empresa.Documentos1.ConsultaUnidadeComAssociaçãoDTO;
import br.com.jbst.DTO.Empresa.Documentos2.ConsultarAssociacoeComUnidadesDTO;
import br.com.jbst.DTO.Empresa.Documentos2.ConsultarUnidadeDeAssociaoDTO;
import br.com.jbst.entities.Associacoes;
import br.com.jbst.entities.UnidadeDoc;
import br.com.jbst.repositories.modulo1.IAssociacoesRepository;
import br.com.jbst.repositories.modulo1.IUnidadeDocRepository;

@Service
public class DocumentosUnidadesServices {

	@Autowired
	private ModelMapper modelMapper;
	

	
	@Autowired
	private IUnidadeDocRepository unidadeDocRepository;
	
	@Autowired
	private IAssociacoesRepository associacoesRepository;
	
	
	   public List<ConsultaUnidadeComAssociaçãoDTO> consultarUnidadesDocumentos() {
	        List<UnidadeDoc> unidades = unidadeDocRepository.findAll();
	        return unidades.stream()
	                .map(unidade -> modelMapper.map(unidade, ConsultaUnidadeComAssociaçãoDTO.class))
	                .collect(Collectors.toList());
	    }

	    public ConsultaUnidadeComAssociaçãoDTO consultarUnidadesDocumentosPorId(UUID id) {
	        Optional<UnidadeDoc> optionalUnidade = unidadeDocRepository.findById(id);
	        if (optionalUnidade.isPresent()) {
	            UnidadeDoc unidade = optionalUnidade.get();
	            return modelMapper.map(unidade, ConsultaUnidadeComAssociaçãoDTO.class);
	        } else {
	            throw new IllegalArgumentException("Unidade com o ID " + id + " não encontrada.");
	        }
	    }
	    
	    public List<ConsultarUnidadeDeAssociaoDTO> consultarUnidadesDocumentosEmpresa() {
	        List<UnidadeDoc> unidades = unidadeDocRepository.findAll();
	        return unidades.stream()
	                .map(unidade -> modelMapper.map(unidade, ConsultarUnidadeDeAssociaoDTO.class))
	                .collect(Collectors.toList());
	    }

	    public ConsultarUnidadeDeAssociaoDTO consultarUnidadesDocumentosEmpresaPorId(UUID id) {
	        Optional<UnidadeDoc> optionalUnidade = unidadeDocRepository.findById(id);
	        if (optionalUnidade.isPresent()) {
	            UnidadeDoc unidade = optionalUnidade.get();
	            return modelMapper.map(unidade, ConsultarUnidadeDeAssociaoDTO.class);
	        } else {
	            throw new IllegalArgumentException("Unidade com o ID " + id + " não encontrada.");
	        }
	    }
	    
	    public List<ConsultarAssociacoeComUnidadesDTO> consultarAssociacoeComUnidades() {
	        List<Associacoes> associacoes = associacoesRepository.findAll();
	        return associacoes.stream()
	                .map(associacao -> modelMapper.map(associacao, ConsultarAssociacoeComUnidadesDTO.class))
	                .collect(Collectors.toList());
	    }

	    public ConsultarAssociacoeComUnidadesDTO consultarConsultarAssociacoeComUnidadesPorId(UUID id) {
	        Optional<Associacoes> optionalAssociacoes = associacoesRepository.findById(id);
	        if (optionalAssociacoes.isPresent()) {
	        	Associacoes associacoes = optionalAssociacoes.get();
	            return modelMapper.map(associacoes, ConsultarAssociacoeComUnidadesDTO.class);
	        } else {
	            throw new IllegalArgumentException("Unidade com o ID " + id + " não encontrada.");
	        }
	    }
	    
	
	    
	 
}
