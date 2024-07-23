package br.com.jbst.services;

import java.time.Instant;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.jbst.DTO.GetCursoDeNRsDTO;
import br.com.jbst.DTO.GetFuncaoEspecificaDTO;
import br.com.jbst.DTO.PostFuncaoEspecificaDTO;
import br.com.jbst.DTO.PutFuncaoEspecificaDTO;
import br.com.jbst.entities.CursoDeNrs;
import br.com.jbst.entities.FuncaoEspecifica;
import br.com.jbst.repositories.modulo1.ICenarioRepository;
import br.com.jbst.repositories.modulo1.IFuncaoEspecificaRepository;

@Service
public class FuncaoEspecificaService {


	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	ICenarioRepository icenarioRepository;
	
	@Autowired
	IFuncaoEspecificaRepository ifuncaoEspecificaRepository;
	
	public GetFuncaoEspecificaDTO criarFuncaoEspecifica(PostFuncaoEspecificaDTO dto) throws Exception {
		try {
			// Mapear o DTO para a entidade CursoDeNrs
			FuncaoEspecifica funcao = modelMapper.map(dto, FuncaoEspecifica.class);

			// Definir o ID do curso manualmente
			funcao.setIdFuncaoEspecifica(UUID.randomUUID());

			
			funcao.setDataHoraCriacao(Instant.now());

			funcao.setCenario(icenarioRepository.findById(dto.getIdCenario()).orElse(null));

			// Salvar a entidade CursoDeNrs
			FuncaoEspecifica funcoes = ifuncaoEspecificaRepository.save(funcao);

			// Retornar o DTO mapeado da entidade salva
			return modelMapper.map(funcoes, GetFuncaoEspecificaDTO .class);
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}
	
	
	public GetFuncaoEspecificaDTO editarFuncaoEspecifica(PutFuncaoEspecificaDTO dto) throws Exception {
        // Verifica se o DTO contém um ID válido
        UUID idFuncaoEspecifica = dto.getIdFuncaoEspecifica();
        if (idFuncaoEspecifica == null) {
            return null; // Retorna null se o ID não for fornecido
        }

        // Busca o perigo no banco de dados com base no ID fornecido no DTO
        FuncaoEspecifica funcao = ifuncaoEspecificaRepository.findById(idFuncaoEspecifica).orElse(null);

        // Se o perigo não for encontrado, retorna null
        if (funcao == null) {
            return null;
        }

        funcao.setSetor_gho(dto.getSetor_gho());
        funcao.setNome_da_funcao(dto.getNome_da_funcao());
        funcao.setCenario_funcaodoc(dto.getCenario_funcaodoc());
        funcao.setCbo(dto.getCbo());
        funcao.setGro(dto.getGro());
        funcao.setGp(dto.getGp());
        funcao.setDescricao(dto.getDescricao());
        FuncaoEspecifica funcoes  = ifuncaoEspecificaRepository.save(funcao);
        return modelMapper.map(funcoes, GetFuncaoEspecificaDTO.class);
    }
	  public List<GetFuncaoEspecificaDTO> buscarTodasFuncoesEspecificas() {
	        List<FuncaoEspecifica> funcoes = ifuncaoEspecificaRepository.findAll();
	        return funcoes.stream()
	                .map(funcao -> modelMapper.map(funcao, GetFuncaoEspecificaDTO.class))
	                .collect(Collectors.toList());
	    }

	    public GetFuncaoEspecificaDTO buscarFuncaoEspecificaPorId(UUID id) {
	        Optional<FuncaoEspecifica> funcao = ifuncaoEspecificaRepository.findById(id);
	        return funcao.map(value -> modelMapper.map(value, GetFuncaoEspecificaDTO.class)).orElse(null);
	    }
	    
	    public GetFuncaoEspecificaDTO excluirFuncaoEspecificaPorId(UUID id) throws Exception {
	        try {
	            // Verificar se a função específica com o ID fornecido existe
	            Optional<FuncaoEspecifica> optionalFuncao = ifuncaoEspecificaRepository.findById(id);
	            if (!optionalFuncao.isPresent()) {
	                throw new IllegalArgumentException("Função específica não encontrada com o ID fornecido: " + id);
	            }

	            // Excluir a função específica
	            ifuncaoEspecificaRepository.deleteById(id);

	            // Retornar o DTO mapeado da função excluída
	            return modelMapper.map(optionalFuncao.get(), GetFuncaoEspecificaDTO.class);
	        } catch (Exception e) {
	            e.printStackTrace();
	            throw e;
	        }
	    }
}
