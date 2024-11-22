package br.com.jbst.services;

import java.time.Instant;


import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.jbst.DTO2.GetAgendamentoDTO;
import br.com.jbst.DtoAtendimento.GetAnamneseDTO;
import br.com.jbst.DtoAtendimento.GetAnamneseDTO1;
import br.com.jbst.DtoAtendimento.PostAnamneseDTO;
import br.com.jbst.DtoAtendimento.PutAnamneseDTO;
import br.com.jbst.entities.Agendamento;
import br.com.jbst.entities.Anamnese;
import br.com.jbst.entities.Atendimento;
import br.com.jbst.repositories.modulo1.IAnamneseRepository;
import br.com.jbst.repositories.modulo1.IAtendimentoRepository;

@Service
public class AnamneseService {

	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private IAtendimentoRepository IAtendimentoRepository;
	
	@Autowired
	private IAnamneseRepository IAnamneseRepository;
	
	
	
	public GetAnamneseDTO criarAnamnese(PostAnamneseDTO dto) {

	    // Gera um UUID único para Anamnese e um número de anamnese
	    UUID idAnamnese = UUID.randomUUID();
	    Integer numeroanamnese = gerarNumeroAnamnese();

	    // Mapeia o DTO para a entidade Anamnese
	    Anamnese anamnese = modelMapper.map(dto, Anamnese.class);
	    anamnese.setIdAnamnese(idAnamnese);
	    anamnese.setNumeroanamnese(numeroanamnese);
	    anamnese.setDataHoraCriacao(Instant.now());

	    // Verifica se o ID do Atendimento foi fornecido
	    if (dto.getIdAtendimento() == null) {
	        throw new IllegalArgumentException("ID do atendimento não pode ser nulo");
	    }

	    // Busca a entidade Atendimento pelo ID
	    Atendimento atendimento = IAtendimentoRepository.findById(dto.getIdAtendimento())
	        .orElseThrow(() -> new IllegalArgumentException("Atendimento com o ID fornecido não foi encontrado"));

	    // Verifica se o Atendimento já possui uma Anamnese associada
	    if (atendimento.getAnamnese() != null) {
	        throw new IllegalArgumentException("Este Atendimento já possui uma Anamnese associada");
	    }

	    // Associa o Atendimento à Anamnese
	    anamnese.setAtendimento(atendimento);

	    // Salva a entidade Anamnese
	    Anamnese savedAnamnese = IAnamneseRepository.save(anamnese);

	    // Mapeia e retorna o DTO
	    return modelMapper.map(savedAnamnese, GetAnamneseDTO.class);
	}
	
	public GetAnamneseDTO editarAnamnese(PutAnamneseDTO dto) {

	    // Verifica se o ID da Anamnese foi fornecido
	    if (dto.getIdAnamnese() == null) {
	        throw new IllegalArgumentException("ID da Anamnese não pode ser nulo");
	    }

	    // Busca a Anamnese pelo ID
	    Anamnese anamneseExistente = IAnamneseRepository.findById(dto.getIdAnamnese())
	        .orElseThrow(() -> new IllegalArgumentException("Anamnese com o ID fornecido não foi encontrada"));

	    // Atualiza os campos da Anamnese com base no DTO recebido
	    modelMapper.map(dto, anamneseExistente);

	    // Define a data de atualização
	    anamneseExistente.setDataHoraCriacao(Instant.now());

	    // Salva a entidade Anamnese atualizada
	    Anamnese anamneseAtualizada = IAnamneseRepository.save(anamneseExistente);

	    // Mapeia e retorna o DTO atualizado
	    return modelMapper.map(anamneseAtualizada, GetAnamneseDTO.class);
	}

	public GetAnamneseDTO buscarAnamnesePorId(UUID idAnamnese) {
	    // Verifica se o ID da Anamnese foi fornecido
	    if (idAnamnese == null) {
	        throw new IllegalArgumentException("ID da Anamnese não pode ser nulo");
	    }

	    // Busca a Anamnese pelo ID
	    Anamnese anamnese = IAnamneseRepository.findById(idAnamnese)
	        .orElseThrow(() -> new IllegalArgumentException("Anamnese com o ID fornecido não foi encontrada"));

	    // Mapeia e retorna o DTO
	    return modelMapper.map(anamnese, GetAnamneseDTO.class);
	}

	   public List<GetAnamneseDTO1> buscarTodasAnamneses() {
	        // Busca todas as Anamneses
	        List<Anamnese> anamneses = IAnamneseRepository.findAll();

	        // Mapeia e retorna a lista de DTOs
	        return anamneses.stream()
	            .map(anamnese -> modelMapper.map(anamnese, GetAnamneseDTO1.class))
	            .collect(Collectors.toList());
	    }

	private Integer gerarNumeroAnamnese() {
	    // Busca o maior número de anamnese existente e incrementa
	    Integer ultimoNumero = IAnamneseRepository.findMaxNumeroAnamnese();
	    if (ultimoNumero == null) {
	        ultimoNumero = 0;
	    }
	    return ultimoNumero + 1;
	}
	
	  public List<GetAnamneseDTO1> consultarAnamnesePorMesAno(int mes, int ano) {
	        List<Anamnese> anamneses = IAnamneseRepository.findAnamneseByMesAndAno(mes, ano);
	        return mapToDTOList(anamneses);
	    }
	   private List<GetAnamneseDTO1> mapToDTOList(List<Anamnese> anamneses) {
	        return modelMapper.map(anamneses, new TypeToken<List<GetAnamneseDTO1>>() {}.getType());
	    }
	   

}