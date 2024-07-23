package br.com.jbst.services;

import java.time.Instant;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.stereotype.Service;

import br.com.jbst.DTO2.GetAnexoDTO;
import br.com.jbst.DTO2.PostAnexosDTO;
import br.com.jbst.DTO2.PutAnexosDTO;
import br.com.jbst.entities.Agenda;
import br.com.jbst.entities.Anexos;
import br.com.jbst.repositories.modulo1.IAnexosRepository;
import br.com.jbst.repositories.modulo1.IAssociacoesRepository;

@Service
public class AnexoService {

	
	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	IAssociacoesRepository iassociacoesRepository;
	
	@Autowired
	IAnexosRepository ianexoRepository;
	
	public GetAnexoDTO criarAnexo(PostAnexosDTO dto) {
	    // Gerar um novo UUID para a agenda
	    UUID idAnexo = UUID.randomUUID();
	    Anexos anexo = modelMapper.map(dto, Anexos.class);

	    // Configurar os campos gerados automaticamente
	    anexo.setIdAnexos(UUID.randomUUID());
	    anexo.setDataHoraCriacao(Instant.now()); 
	    anexo.setAssociacoes(iassociacoesRepository.findById(dto.getIdAssociacoes()).orElse(null));

	    // Salvar a nova agenda no banco de dados
        Anexos anexos =  ianexoRepository.save(anexo);

	    // Mapear a entidade agenda para o DTO de resposta
	    return modelMapper.map(anexos, GetAnexoDTO.class);
	}
	
	public GetAnexoDTO editarAnexo(PutAnexosDTO dto) {
	    // Gerar um novo UUID para a agenda
	    Anexos anexo = modelMapper.map(dto, Anexos.class);

	    // Configurar os campos gerados automaticamente
	    anexo.setAssociacoes(iassociacoesRepository.findById(dto.getIdAssociacoes()).orElse(null));
	    anexo.setDataHoraCriacao(Instant.now()); 

	    // Salvar a nova agenda no banco de dados
        Anexos anexos =  ianexoRepository.save(anexo);

	    // Mapear a entidade agenda para o DTO de resposta
	    return modelMapper.map(anexos, GetAnexoDTO.class);
	}
	
	public GetAnexoDTO consultarAnexoPorId(UUID idAnexos) throws NotFoundException {
		Optional<Anexos> anexoOptional = ianexoRepository.findById(idAnexos);
		if (anexoOptional.isPresent()) {
			Anexos anexos =  anexoOptional.get();
			return modelMapper.map(anexos, GetAnexoDTO.class);
		} else {
			throw new NotFoundException();
		}
	}
	
	public List<GetAnexoDTO> buscarTodosOsAnexos() {
		List<Anexos> anexos = ianexoRepository.findAll();
		return anexos.stream().map(anexo -> modelMapper.map(anexo, GetAnexoDTO.class)).collect(Collectors.toList());
	}
	
	public void excluirAnexoPorId(UUID idAnexo) throws NotFoundException {
        Optional<Anexos> anexoOptional = ianexoRepository.findById(idAnexo);
        if (anexoOptional.isPresent()) {
        	Anexos anexos = anexoOptional.get();
        	ianexoRepository.delete(anexos);
        } else {
			throw new NotFoundException();
        }
    }
}
