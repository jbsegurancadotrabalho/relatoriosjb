package br.com.jbst.services;


import java.time.Instant;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.jbst.DTO2.GetFormacaoSaudeDTO;
import br.com.jbst.DTO2.GetFotosDTO;
import br.com.jbst.DTO2.PostFotosAnexosDTO;
import br.com.jbst.DTO2.PostFotosEspecializacaoSaudeEntityDTO;
import br.com.jbst.DTO2.PostFotosFormacaoSaudeDTO;
import br.com.jbst.DTO2.PostFotosMaquinasDTO;
import br.com.jbst.DTO2.PostFotosUnidadeDocDTO;
import br.com.jbst.dtos.relatorios.GetFuncaoDocRelatoriosDTO;
import br.com.jbst.entities.FormacaoSaude;
import br.com.jbst.entities.Fotos;
import br.com.jbst.entities.FotosAnexosEntity;
import br.com.jbst.entities.FotosEspecializacaoSaudeEntity;
import br.com.jbst.entities.FotosFormacaoSaudeEntity;
import br.com.jbst.entities.FotosMaquinasEntity;
import br.com.jbst.entities.FotosUnidadeDocEntity;
import br.com.jbst.repositories.modulo1.IAnexosRepository;
import br.com.jbst.repositories.modulo1.IEspecializacaoSaudeRepository;
import br.com.jbst.repositories.modulo1.IMaquinasRepository;
import br.com.jbst.repositories.modulo1.IUnidadeDocRepository;
import br.com.jbst.repositories.modulo2.IFormacaoSaudeRepository;
import br.com.jbst.repositories.modulo2.IFotosAnexosRepository;
import br.com.jbst.repositories.modulo2.IFotosEspecializacaoSaudeRepository;
import br.com.jbst.repositories.modulo2.IFotosFormacaoSaudeRepository;
import br.com.jbst.repositories.modulo2.IFotosMaquinasRepository;
import br.com.jbst.repositories.modulo2.IFotosUnidadeDocRepository;

@Service
public class FotosService {
	
	@Autowired
	IAnexosRepository ianexosRepository;
	
	
	@Autowired
	IFotosUnidadeDocRepository IFotosUnidadeDocRepository;
	
	@Autowired
	IUnidadeDocRepository IUnidadeDocRepository;
	
	@Autowired
	IFotosMaquinasRepository IFotosMaquinasRepository;

	@Autowired
	IFotosFormacaoSaudeRepository fotosRepository;
	
	@Autowired
	IMaquinasRepository IMaquinasRepository;
	
	@Autowired
	IFormacaoSaudeRepository iformacaoSaudeRepository;
	
	@Autowired
	IEspecializacaoSaudeRepository iespecializacaoSaudeRepository;
	
	@Autowired
	IFotosEspecializacaoSaudeRepository  IFotosEspecializacaoSaudeRepository;  

	@Autowired
	IFotosAnexosRepository ifotosAnexosRepository;

	@Autowired
	private ModelMapper modelMapper;
	
	public void criarFotosFormacao(PostFotosFormacaoSaudeDTO dto) {
	    try {
	        // Crie uma nova entidade Fotos
	    	FotosFormacaoSaudeEntity fotos = new FotosFormacaoSaudeEntity();
	        fotos.setIdFotos(UUID.randomUUID());
	        fotos.setNomeFoto(dto.getNomeFoto()); 
	        fotos.setFormacaosaude(iformacaoSaudeRepository.findById(dto.getIdFormacaoSaude()).orElse(null));
	        fotos.setDataHoraCriacao(Instant.now()); // Suponho que haja um método getFoto() no DTO

	        // Salve a foto usando o repositório
	        fotosRepository.save(fotos);
	    } catch (Exception e) {
	        // Lide com a exceção aqui
	        throw new RuntimeException("Falha ao criar fotos de formação", e);
	    }
	}

	public void criarFotosEspecializaçãoSaude(PostFotosEspecializacaoSaudeEntityDTO dto) {
	    try {
	        // Crie uma nova entidade Fotos
	    	FotosEspecializacaoSaudeEntity fotos = new FotosEspecializacaoSaudeEntity();
	        fotos.setIdFotos(UUID.randomUUID());
	        fotos.setNomeFoto(dto.getNomeFoto()); // Suponho que haja um método getFoto() no DTO
	        fotos.setEspecializacaosaude(iespecializacaoSaudeRepository.findById(dto.getIdEspecializacaoSaude()).orElse(null));
	        fotos.setDataHoraCriacao(Instant.now()); // Suponho que haja um método getFoto() no DTO
	        // Salve a foto usando o repositório
	        IFotosEspecializacaoSaudeRepository.save(fotos);
	    } catch (Exception e) {
	        // Lide com a exceção aqui
	        throw new RuntimeException("Falha ao criar fotos de formação", e);
	    }
	}
	
	public void criarFotosMaquinas(PostFotosMaquinasDTO dto) {
	    try {
	        // Crie uma nova entidade Fotos
	    	FotosMaquinasEntity fotos = new FotosMaquinasEntity();
	        fotos.setIdFotos(UUID.randomUUID());
	        fotos.setNomeFoto(dto.getNomeFoto()); // Suponho que haja um método getFoto() no DTO
	        fotos.setMaquinas(IMaquinasRepository.findById(dto.getIdMaquina()).orElse(null));
	        fotos.setDataHoraCriacao(Instant.now()); // Suponho que haja um método getFoto() no DTO
	        // Salve a foto usando o repositório
	        IFotosMaquinasRepository.save(fotos);
	    } catch (Exception e) {
	        // Lide com a exceção aqui
	        throw new RuntimeException("Falha ao criar fotos de formação", e);
	    }
	}
	 
	 public void UnidadeDoc (PostFotosUnidadeDocDTO dto) {
		    try {
		    	FotosUnidadeDocEntity  fotos = new FotosUnidadeDocEntity();
		        fotos.setIdFotos(UUID.randomUUID());
		        fotos.setNomeFoto(dto.getNomeFoto()); // Suponho que haja um método getFoto() no DTO
		        fotos.setUnidadedoc(IUnidadeDocRepository.findById(dto.getIdunidadeDoc()).orElse(null));
		        fotos.setDataHoraCriacao(Instant.now()); // Suponho que haja um método getFoto() no DTO
		        IFotosUnidadeDocRepository.save(fotos);
		    } catch (Exception e) {
		        throw new RuntimeException("Falha ao criar fotos de formação", e);
		    }
		}
	 
	 public void FotosAnexos (PostFotosAnexosDTO dto) {
		    try {
		    	FotosAnexosEntity  fotos = new FotosAnexosEntity();
		        fotos.setIdFotos(UUID.randomUUID());
		        fotos.setNomeFoto(dto.getNomeFoto()); // Suponho que haja um método getFoto() no DTO
		        fotos.setAnexos(ianexosRepository.findById(dto.getIdAnexos()).orElse(null));
		        fotos.setDataHoraCriacao(Instant.now()); // Suponho que haja um método getFoto() no DTO
		        ifotosAnexosRepository.save(fotos);
		    } catch (Exception e) {
		        throw new RuntimeException("Falha ao criar fotos de formação", e);
		    }
		}
	 
	 public FotosAnexosEntity incluirFotos(UUID id, byte[] fotos) throws Exception {

			Optional<FotosAnexosEntity> registro =  ifotosAnexosRepository.findById(id);
			if (registro.isEmpty())
				throw new IllegalArgumentException("Funcionario inválido: " + id);

			FotosAnexosEntity foto = registro.get();

			foto.setFoto(fotos);

			return ifotosAnexosRepository.save(foto);
		}
	 
	 public List<GetFotosDTO> buscarTodasAsFotos() {
	        List<FotosAnexosEntity> fotos = ifotosAnexosRepository.findAll();
	        return fotos.stream()
	                .map(foto -> modelMapper.map(foto, GetFotosDTO.class))
	                .collect(Collectors.toList());
	    }
	 
	    public GetFotosDTO buscarFotosPorId(UUID id) {
	    	FotosAnexosEntity fotos = ifotosAnexosRepository.findById(id)
	                .orElseThrow(() -> new RuntimeException("Foto não encontrado com o ID: " + id));
	        return modelMapper.map(fotos, GetFotosDTO.class);
	    }
	 
	    public void excluirFotoPorId(UUID id) {
	        FotosAnexosEntity foto = ifotosAnexosRepository.findById(id)
	                .orElseThrow(() -> new RuntimeException("Foto não encontrada com o ID: " + id));
	        ifotosAnexosRepository.delete(foto);
	    }
	
}
