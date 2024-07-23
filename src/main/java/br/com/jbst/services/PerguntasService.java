package br.com.jbst.services;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.jbst.DTO2.GetPerguntasDTO;
import br.com.jbst.DTO2.PostPerguntasAterramentoDTO;
import br.com.jbst.DTO2.PostPerguntasCondutoresDTO;
import br.com.jbst.DTO2.PostPerguntasLuminariasDTO;
import br.com.jbst.DTO2.PostPerguntasProcedimentosDTO;
import br.com.jbst.DTO2.PostPerguntasProtecoesDTO;
import br.com.jbst.DTO2.PostPerguntasQuadrosMediçõesDTO;
import br.com.jbst.DTO2.PostPerguntasQuadrosPaineisDTO;
import br.com.jbst.DTO2.PostPerguntasTomadasDTO;
import br.com.jbst.DTO2.PutPerguntasDTO;
import br.com.jbst.entities.LaudoEletrico.Perguntas;
import br.com.jbst.entities.LaudoEletrico.PerguntasEntity;
import br.com.jbst.entities.LaudoEletrico.Quadros_Medicoes;
import br.com.jbst.repositories.modulo1.IAterramentoRepository;
import br.com.jbst.repositories.modulo1.ICondutoresRepository;
import br.com.jbst.repositories.modulo2.ILuminariasRepository;
import br.com.jbst.repositories.modulo2.IPerguntasEntityRepositoy;
import br.com.jbst.repositories.modulo2.IPerguntasRepository;
import br.com.jbst.repositories.modulo2.IProcedimentosRepository;
import br.com.jbst.repositories.modulo2.IProtecoesRepository;
import br.com.jbst.repositories.modulo2.IQuadros_MedicoesRepository;
import br.com.jbst.repositories.modulo2.IQuadros_PaineisRepository;
import br.com.jbst.repositories.modulo2.ITomadasRepository;
import jakarta.persistence.EntityNotFoundException;

@Service
public class PerguntasService {
	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	IPerguntasRepository iperguntasRepository;

	@Autowired
	IQuadros_MedicoesRepository IQuadros_MedicoesRepository;
	
	
	@Autowired
	IQuadros_PaineisRepository iQuadros_paineisRepository;
	
	@Autowired
	IProcedimentosRepository iprocedimentosRepository;
	
	@Autowired
	ITomadasRepository itomadasRepository;
	
	@Autowired
	IProtecoesRepository iprotecoesRepository;
	
	@Autowired
	IAterramentoRepository iaterramentoRepository;
	
	@Autowired
	ICondutoresRepository icondutoresRepository;
	
	@Autowired
	ILuminariasRepository iluminariasRepository;

	@Autowired
	IPerguntasEntityRepositoy 	iperguntasEntityRepositoy;

	
	public void criarPerguntasQuadrosMedições(PostPerguntasQuadrosMediçõesDTO dto) {
	    try {
	        Perguntas perguntas = new Perguntas();
	        perguntas.setIdPerguntas(UUID.randomUUID());
	        perguntas.setPergunta(dto.getPergunta());

	        // Use the provided quadro de medições ID
	        UUID idQuadrosMedicoes = dto.getIdQuadros_Medicoes();
	        
	        // Save only the perguntas with associated quadros de medições
	        iperguntasRepository.salvarPerguntaComMedicao(perguntas.getIdPerguntas(), perguntas.getPergunta(), idQuadrosMedicoes);
	    } catch (Exception e) {
	        // Handle exception here
	        throw new RuntimeException("Falha ao criar perguntas", e);
	    }
	}
	
	public void criarPerguntasQuadrosPaineis(PostPerguntasQuadrosPaineisDTO dto) {
	    try {
	        Perguntas perguntas = new Perguntas();
	        perguntas.setIdPerguntas(UUID.randomUUID());
	        perguntas.setPergunta(dto.getPergunta());

	        // Use the provided quadro de medições ID
	        UUID idQuadrosPaineis = dto.getIdquadros_paineis();
	        
	        // Save only the perguntas with associated quadros de medições
	        iperguntasRepository.salvarPerguntaPaineis(perguntas.getIdPerguntas(), perguntas.getPergunta(), idQuadrosPaineis);
	    } catch (Exception e) {
	        // Handle exception here
	        throw new RuntimeException("Falha ao criar perguntas", e);
	    }
	}
	
	public void criarPerguntasProtecoes(PostPerguntasProtecoesDTO dto) {
	    try {
	        Perguntas perguntas = new Perguntas();
	        perguntas.setIdPerguntas(UUID.randomUUID());
	        perguntas.setPergunta(dto.getPergunta());

	        // Use the provided quadro de medições ID
	        UUID id_protecoes = dto.getId_protecoes();
	        
	        // Save only the perguntas with associated quadros de medições
	        iperguntasRepository.salvarPerguntaProtecoes(perguntas.getIdPerguntas(), perguntas.getPergunta(), id_protecoes);
	    } catch (Exception e) {
	        // Handle exception here
	        throw new RuntimeException("Falha ao criar perguntas", e);
	    }
	}
	
	public void criarPerguntasTomadas(PostPerguntasTomadasDTO dto) {
	    try {
	        Perguntas perguntas = new Perguntas();
	        perguntas.setIdPerguntas(UUID.randomUUID());
	        perguntas.setPergunta(dto.getPergunta());

	        // Use the provided quadro de medições ID
	        UUID idTomadas = dto.getIdTomadas();
	        
	        // Save only the perguntas with associated quadros de medições
	        iperguntasRepository.salvarPerguntasTomadas(perguntas.getIdPerguntas(), perguntas.getPergunta(), idTomadas);
	    } catch (Exception e) {
	        // Handle exception here
	        throw new RuntimeException("Falha ao criar perguntas", e);
	    }
	}
	public void criarPerguntasProcedimentos(PostPerguntasProcedimentosDTO dto) {
	    try {
	        Perguntas perguntas = new Perguntas();
	        perguntas.setIdPerguntas(UUID.randomUUID());
	        perguntas.setPergunta(dto.getPergunta());

	        // Use the provided quadro de medições ID
	        UUID idProcedimentos = dto.getIdProcedimentos();
	        
			// Save only the perguntas with associated quadros de medições
	        iperguntasRepository.salvarPerguntasProcedimentos(perguntas.getIdPerguntas(), perguntas.getPergunta(), idProcedimentos);
	    } catch (Exception e) {
	        // Handle exception here
	        throw new RuntimeException("Falha ao criar perguntas", e);
	    }
	}
	
	public void criarPerguntasCondutores(PostPerguntasCondutoresDTO dto) {
	    try {
	        Perguntas perguntas = new Perguntas();
	        perguntas.setIdPerguntas(UUID.randomUUID());
	        perguntas.setPergunta(dto.getPergunta());

	        // Use the provided quadro de medições ID
	        UUID idCondutores = dto.getIdCondutores();
	        
			// Save only the perguntas with associated quadros de medições
	        iperguntasRepository.salvarPerguntasCondutores(perguntas.getIdPerguntas(), perguntas.getPergunta(), idCondutores);
	    } catch (Exception e) {
	        // Handle exception here
	        throw new RuntimeException("Falha ao criar perguntas", e);
	    }
	}
	
	public void criarPerguntasLuminarias(PostPerguntasLuminariasDTO dto) {
	    try {
	        Perguntas perguntas = new Perguntas();
	        perguntas.setIdPerguntas(UUID.randomUUID());
	        perguntas.setPergunta(dto.getPergunta());

	        // Use the provided quadro de medições ID
	        UUID idLuminarias = dto.getIdLuminarias();
	        
			// Save only the perguntas with associated quadros de medições
	        iperguntasRepository.salvarPerguntasLuminarias(perguntas.getIdPerguntas(), perguntas.getPergunta(), idLuminarias);
	    } catch (Exception e) {
	        // Handle exception here
	        throw new RuntimeException("Falha ao criar perguntas", e);
	    }
	}
	
	public void criarPerguntasAterramento(PostPerguntasAterramentoDTO dto) {
	    try {
	        Perguntas perguntas = new Perguntas();
	        perguntas.setIdPerguntas(UUID.randomUUID());
	        perguntas.setPergunta(dto.getPergunta());

	        // Use the provided quadro de medições ID
	        UUID idAterramento = dto.getIdAterramento();
	        
			// Save only the perguntas with associated quadros de medições
	        iperguntasRepository.salvarPerguntasAterramento(perguntas.getIdPerguntas(), perguntas.getPergunta(), idAterramento);
	    } catch (Exception e) {
	        // Handle exception here
	        throw new RuntimeException("Falha ao criar perguntas", e);
	    }
	}
	
	
	////////////////////////////////////////////////////////////////
	
	 public void atualizarPerguntas(PutPerguntasDTO dto) {
	        // Find the question by ID
	        Optional<PerguntasEntity> optionalPerguntas = iperguntasEntityRepositoy.findById(dto.getIdPerguntas());
	        
	        // If the question exists, update it
	        if (optionalPerguntas.isPresent()) {
	            PerguntasEntity perguntas = optionalPerguntas.get();
	            perguntas.setPergunta(dto.getPergunta());
	            iperguntasEntityRepositoy.save(perguntas);
	        } else {
	            // If the question doesn't exist, throw an exception
	            throw new EntityNotFoundException("Pergunta não encontrada com o ID: " + dto.getIdPerguntas());
	        }
	    }
	
	
	
	
	
	
	
	/////////////////////////////////////////////////////////////////
	public List<GetPerguntasDTO> buscarTodasAsPerguntas() {
        return iperguntasRepository.buscarTodasAsPerguntas();
    }
	
	public GetPerguntasDTO buscarPerguntaPorId(UUID id) {
        return iperguntasRepository.buscarPerguntaPorId(id);
    }
	
	public void excluirPergunta(UUID idPergunta) {
		iperguntasEntityRepositoy.deletePerguntaPorId(idPergunta);
    }
	
}
