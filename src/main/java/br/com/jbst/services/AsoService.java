package br.com.jbst.services;

import java.time.Instant;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.jbst.DtoAtendimento.GetAnamneseDTO;
import br.com.jbst.DtoAtendimento.GetAnamneseDTO1;
import br.com.jbst.DtoAtendimento.GetAsoDTO;
import br.com.jbst.DtoAtendimento.PostAsoDTO;
import br.com.jbst.DtoAtendimento.PutAnamneseDTO;
import br.com.jbst.DtoAtendimento.PutAsoDTO;
import br.com.jbst.entities.ASO;
import br.com.jbst.entities.Anamnese;
import br.com.jbst.entities.Atendimento;
import br.com.jbst.repositories.modulo1.IAsoRepository;
import br.com.jbst.repositories.modulo1.IAtendimentoRepository;

@Service
public class AsoService {

	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private IAtendimentoRepository IAtendimentoRepository;
	
	@Autowired
	private IAsoRepository IAsoRepository;
	
	public GetAsoDTO criarAso(PostAsoDTO dto) {

	    // Gera um UUID único para Anamnese e um número de anamnese
	    UUID idAso = UUID.randomUUID();
	    Integer numeroaso = gerarNumeroAso();

	    // Mapeia o DTO para a entidade Anamnese
	    ASO aso = modelMapper.map(dto, ASO.class);
	    aso.setId_aso(idAso);
	    aso.setNumeroaso(numeroaso);
	    aso.setDataHoraCriacao(Instant.now());

	    // Verifica se o ID do Atendimento foi fornecido
	    if (dto.getIdAtendimento() == null) {
	        throw new IllegalArgumentException("ID do atendimento não pode ser nulo");
	    }

	    // Busca a entidade Atendimento pelo ID
	    Atendimento atendimento = IAtendimentoRepository.findById(dto.getIdAtendimento())
	        .orElseThrow(() -> new IllegalArgumentException("Atendimento com o ID fornecido não foi encontrado"));

	    // Verifica se o Atendimento já possui uma Anamnese associada
	    if (atendimento.getAso() != null) {
	        throw new IllegalArgumentException("Este Atendimento já possui um Aso associado a ele");
	    }

	    // Associa o Atendimento à Anamnese
	    aso.setAtendimento(atendimento);

	    // Salva a entidade Anamnese
	    ASO savedAso = IAsoRepository.save(aso);

	    // Mapeia e retorna o DTO
	    return modelMapper.map(savedAso, GetAsoDTO.class);
	}
	
	public GetAsoDTO editarAso(PutAsoDTO dto) {

	    // Verifica se o ID da Anamnese foi fornecido
	    if (dto.getId_aso() == null) {
	        throw new IllegalArgumentException("ID do Aso não pode ser nulo");
	    }

	    // Busca a Anamnese pelo ID
	    ASO asoExistente = IAsoRepository.findById(dto.getId_aso())
	        .orElseThrow(() -> new IllegalArgumentException("Aso com o ID fornecido não foi encontrada"));

	    // Atualiza os campos da Anamnese com base no DTO recebido
	    modelMapper.map(dto, asoExistente);

	    // Define a data de atualização
	    asoExistente.setDataHoraCriacao(Instant.now());

	    // Salva a entidade Anamnese atualizada
	    ASO asoAtualizada = IAsoRepository.save(asoExistente);

	    // Mapeia e retorna o DTO atualizado
	    return modelMapper.map(asoAtualizada, GetAsoDTO.class);
	}

	public GetAsoDTO buscarAsoPorId(UUID id_aso) {
	    // Verifica se o ID da Anamnese foi fornecido
	    if (id_aso == null) {
	        throw new IllegalArgumentException("ID do Aso não pode ser nulo");
	    }

	    // Busca a Anamnese pelo ID
	    ASO aso = IAsoRepository.findById(id_aso)
	        .orElseThrow(() -> new IllegalArgumentException("Aso com o ID fornecido não foi encontrada"));

	    // Mapeia e retorna o DTO
	    return modelMapper.map(aso, GetAsoDTO.class);
	}
	
	public List<GetAsoDTO> buscarTodosAsos() {
        // Busca todas as Anamneses
        List<ASO> asos = IAsoRepository.findAll();

        // Mapeia e retorna a lista de DTOs
        return asos.stream()
            .map(aso -> modelMapper.map(aso, GetAsoDTO.class))
            .collect(Collectors.toList());
    }
	
	private Integer gerarNumeroAso() {
	    // Busca o maior número de anamnese existente e incrementa
	    Integer ultimoNumero = IAsoRepository.findMaxNumeroAso();
	    if (ultimoNumero == null) {
	        ultimoNumero = 0;
	    }
	    return ultimoNumero + 1;
	}
	
	  public List<GetAsoDTO> consultarAsoPorMesAno(int mes, int ano) {
	        List<ASO> asos = IAsoRepository.findAsoByMesAndAno(mes, ano);
	        return mapToDTOList(asos);
	    }
	   private List<GetAsoDTO> mapToDTOList(List<ASO> asos) {
	        return modelMapper.map(asos, new TypeToken<List<GetAsoDTO>>() {}.getType());
	    }
}
