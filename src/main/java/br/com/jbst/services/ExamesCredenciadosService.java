package br.com.jbst.services;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.jbst.DTO2.GetCursos_CredenciadosDTO;
import br.com.jbst.DTO2.GetExamesCredenciadosDTO;
import br.com.jbst.DTO2.PostCursos_CredenciadosDTO;
import br.com.jbst.DTO2.PostExamesCredenciadosDTO;
import br.com.jbst.DTO2.PutCursos_CredenciadosDTO;
import br.com.jbst.DTO2.PutExamesCredenciadosDTO;
import br.com.jbst.entities.Cursos_Credenciados;
import br.com.jbst.entities.ExamesCredenciados;
import br.com.jbst.repositories.modulo1.ICredenciadosRepository;
import br.com.jbst.repositories.modulo1.IExamesCredenciadosRepository;
import br.com.jbst.repositories.modulo1.IExamesRepository;

@Service
public class ExamesCredenciadosService {
	     @Autowired
	    private IExamesRepository ixameRepository;
	    
	    @Autowired
	    private  IExamesCredenciadosRepository iexamesCredenciadosRepository;
	    
		@Autowired
		ICredenciadosRepository icredenciadoRepository;
		
	    @Autowired
	    private ModelMapper modelMapper;

	    public  GetExamesCredenciadosDTO criarExamesCredenciados( PostExamesCredenciadosDTO dto) {
	    	ExamesCredenciados examescredenciados = modelMapper.map(dto, ExamesCredenciados.class);
	    	examescredenciados.setIdExameCredenciado(UUID.randomUUID());
	    	examescredenciados.setExames(ixameRepository.findById(dto.getIdExames()).orElse(null));
	    	examescredenciados.setCredenciados( icredenciadoRepository.findById(dto.getIdCredenciado()).orElse(null));
	    	examescredenciados = iexamesCredenciadosRepository.save(examescredenciados);
	        return modelMapper.map(examescredenciados, GetExamesCredenciadosDTO.class);
	    }
	    public GetExamesCredenciadosDTO atualizarExamesCredenciados(PutExamesCredenciadosDTO dto) {
	        // Verifique se o DTO contém um ID para identificar o objeto a ser atualizado
	        if (dto.getIdExameCredenciado() == null) {
	            throw new IllegalArgumentException("ID do ExameCredenciados não fornecido para atualização.");
	        }

	        // Recuperar o objeto AvaliacoesCredenciados com base no ID fornecido
	        ExamesCredenciados examescredenciados =  iexamesCredenciadosRepository.findById(dto.getIdExameCredenciado())
	                .orElseThrow(() -> new RuntimeException("AvaliacoesCredenciados não encontrado com id: " + dto.getIdExameCredenciado()));

	        // Atualizando os campos com os dados do DTO
	        modelMapper.map(dto, examescredenciados);
	        
	        // Atualizando as associações com outras entidades
	        examescredenciados.setExames(ixameRepository.findById(dto.getIdExames()).orElse(null));
	        examescredenciados.setCredenciados(icredenciadoRepository.findById(dto.getIdCredenciado()).orElse(null));
	        
	        // Salvando as alterações no banco de dados
	        examescredenciados = iexamesCredenciadosRepository.save(examescredenciados);

	        // Mapeando a entidade atualizada de volta para DTO
	        return modelMapper.map(examescredenciados, GetExamesCredenciadosDTO.class);
	    }
	    
	    public List<GetExamesCredenciadosDTO> buscarTodosExamesCredenciados() {
	        List<ExamesCredenciados> examesCredenciadosList = iexamesCredenciadosRepository.findAll();
	        return examesCredenciadosList.stream()
	                .map(c -> modelMapper.map(c, GetExamesCredenciadosDTO.class))
	                .collect(Collectors.toList());
	    }

	    public GetExamesCredenciadosDTO buscarExamesCredenciadosPorId(UUID id) {
	        ExamesCredenciados exameCredenciado = iexamesCredenciadosRepository.findById(id)
	                .orElseThrow(() -> new RuntimeException("Exame Credenciado não encontrado com o ID: " + id));
	        return modelMapper.map(exameCredenciado, GetExamesCredenciadosDTO.class);
	    }

	    public void excluirExamesCredenciadosPorId(UUID id) {
	        // Verificar se o curso credenciado existe antes de excluí-lo
	        if (!iexamesCredenciadosRepository.existsById(id)) {
	            throw new RuntimeException("Exame Credenciado não encontrado com o ID: " + id);
	        }
	        iexamesCredenciadosRepository.deleteById(id);
	    }
	    
	    
}
