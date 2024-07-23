package br.com.jbst.services;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.stereotype.Service;

import br.com.jbst.DTO2.GetAgendaDTO;
import br.com.jbst.DTO2.PostAgendaDTO;
import br.com.jbst.DTO2.PutAgendaDTO;
import br.com.jbst.entities.Agenda;

import br.com.jbst.repositories.modulo1.IAgendaRepository;
import br.com.jbst.repositories.modulo1.ICredenciadosRepository;
import br.com.jbst.repositories.modulo1.IExamesCredenciadosRepository;
import br.com.jbst.repositories.modulo2.IProfissionalSaudeRepository;

@Service
public class AgendaService {

	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private IAgendaRepository agendaRepository;

	@Autowired
	ICredenciadosRepository icredenciadoRepository;

	@Autowired
	IProfissionalSaudeRepository iprofissionalSaudeRepository;

	@Autowired
	private IExamesCredenciadosRepository iexamesCredenciadosRepository;

	public GetAgendaDTO criarAgenda(PostAgendaDTO dto) {
	    // Gerar um novo UUID para a agenda
	    UUID idAgenda = UUID.randomUUID();

	    // Gerar automaticamente o número da agenda
	    Integer numeroAgenda = gerarNumeroAgenda();

	    // Obter a data e hora atual

	    // Mapear os dados do DTO para a entidade Agenda
	    Agenda agenda = modelMapper.map(dto, Agenda.class);

	    // Configurar os campos gerados automaticamente
	    agenda.setIdAgenda(UUID.randomUUID());
	    agenda.setNumeroagenda(numeroAgenda);
	    agenda.setCredenciados(icredenciadoRepository.findById(dto.getIdCredenciado()).orElse(null));
	    agenda.setExamescredenciados(iexamesCredenciadosRepository.findById(dto.getIdExameCredenciado()).orElse(null));
	    agenda.setProfissionalsaude(iprofissionalSaudeRepository.findById(dto.getId_profissionalsaude()).orElse(null));

	    // Salvar a nova agenda no banco de dados
	    Agenda agendas = agendaRepository.save(agenda);

	    // Mapear a entidade agenda para o DTO de resposta
	    return modelMapper.map(agendas, GetAgendaDTO.class);
	}


	private Integer gerarNumeroAgenda() {
		Integer ultimoNumero = agendaRepository.findMaxNumeroAgenda();
		if (ultimoNumero == null) {
			ultimoNumero = 0;
		}
		return ultimoNumero + 1;
	}

	public GetAgendaDTO editarAgenda(PutAgendaDTO dto) throws Exception {
		// Verificar se o ID da agenda está presente no DTO
		if (dto.getIdAgenda() == null) {
			throw new IllegalArgumentException("O ID da agenda deve ser fornecido para editar a agenda.");
		}

		// Buscar a agenda no banco de dados pelo ID
		Optional<Agenda> agendaOptional = agendaRepository.findById(dto.getIdAgenda());
		if (agendaOptional.isEmpty()) {
			throw new Exception("Agenda não encontrada com o ID: " + dto.getIdAgenda());
		}

		// Obter a agenda existente do banco de dados
		Agenda agendaExistente = agendaOptional.get();

		// Mapear os dados do DTO para a agenda existente
		modelMapper.map(dto, agendaExistente);
		agendaExistente.setCredenciados(icredenciadoRepository.findById(dto.getIdCredenciado()).orElse(null));
		agendaExistente.setExamescredenciados(
				iexamesCredenciadosRepository.findById(dto.getIdExameCredenciado()).orElse(null));
		agendaExistente.setProfissionalsaude(
				iprofissionalSaudeRepository.findById(dto.getId_profissionalsaude()).orElse(null));

		// Salvar as alterações no banco de dados
		Agenda agendaAtualizada = agendaRepository.save(agendaExistente);

		// Mapear a agenda atualizada para o DTO de resposta
		return modelMapper.map(agendaAtualizada, GetAgendaDTO.class);
	}

	public GetAgendaDTO consultarAgendaPorId(UUID idAgenda) throws NotFoundException {
		Optional<Agenda> agendaOptional = agendaRepository.findById(idAgenda);
		if (agendaOptional.isPresent()) {
			Agenda agenda = agendaOptional.get();
			return modelMapper.map(agenda, GetAgendaDTO.class);
		} else {
			throw new NotFoundException();
		}
	}

	// Método para buscar todas as agendas
	public List<GetAgendaDTO> buscarTodasAsAgendas() {
		List<Agenda> agendas = agendaRepository.findAll();
		return agendas.stream().map(agenda -> modelMapper.map(agenda, GetAgendaDTO.class)).collect(Collectors.toList());
	}
	
	 public void excluirAgendaPorId(UUID idAgenda) throws NotFoundException {
	        Optional<Agenda> agendaOptional = agendaRepository.findById(idAgenda);
	        if (agendaOptional.isPresent()) {
	            Agenda agenda = agendaOptional.get();
	            agendaRepository.delete(agenda);
	        } else {
				throw new NotFoundException();
	        }
	    }
}
