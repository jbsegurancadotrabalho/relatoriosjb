package br.com.jbst.services;


import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.stereotype.Service;

import br.com.jbst.DTO2.GetAgendamentoDTO;
import br.com.jbst.DTO2.PostAgendamentoFuncionarioDTO;
import br.com.jbst.DTO2.PutAgendamentoDTO;
import br.com.jbst.entities.Agenda;
import br.com.jbst.entities.Agendamento;
import br.com.jbst.entities.Funcionario;
import br.com.jbst.repositories.modulo1.IAgendaRepository;
import br.com.jbst.repositories.modulo1.IAgendamentoRepository;
import br.com.jbst.repositories.modulo2.IFuncionarioRepository;
import br.com.jbst.repositories.modulo2.IPessoaFisicaRepository;

@Service
public class ServicosDeAgendamento {

	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private IAgendaRepository agendaRepository;

	@Autowired
	private IAgendamentoRepository agendamentoRepository;

	@Autowired
	IFuncionarioRepository funcionarioRepository;

	@Autowired
	IPessoaFisicaRepository pessoaFisicaRepository;

	public GetAgendamentoDTO criarAgendamento(PostAgendamentoFuncionarioDTO dto) {
	    // Verificar se os IDs são válidos
	    if (dto.getIdAgenda() == null || dto.getIdFuncionario() == null) {
	        throw new IllegalArgumentException("ID da agenda ou ID do funcionário não pode ser nulo.");
	    }

	    // Gerar um novo UUID para a agenda
	    UUID idAgendamento = UUID.randomUUID();

	    // Gerar automaticamente o número da agenda
	    Integer numeroAgendamento = gerarNumeroAgendamento();

	    // Mapear os dados do DTO para a entidade Agendamento
	    Agendamento agendamento = modelMapper.map(dto, Agendamento.class);

	    // Configurar os campos gerados automaticamente
	    agendamento.setIdAgendamento(idAgendamento);
	    agendamento.setNumeroagendamento(numeroAgendamento);

	    // Buscar e configurar a agenda e o funcionário
	    Agenda agenda = agendaRepository.findById(dto.getIdAgenda())
	                    .orElseThrow(() -> new IllegalArgumentException("Agenda não encontrada para o ID fornecido."));
	    Funcionario funcionario = funcionarioRepository.findById(dto.getIdFuncionario())
	                    .orElseThrow(() -> new IllegalArgumentException("Funcionário não encontrado para o ID fornecido."));
	    
	    agendamento.setAgenda(agenda);
	    agendamento.setFuncionario(funcionario);

	    // Salvar a nova agenda no banco de dados
	    Agendamento agendamentos = agendamentoRepository.save(agendamento);

	    // Mapear a entidade agenda para o DTO de resposta
	    return modelMapper.map(agendamentos, GetAgendamentoDTO.class);
	}


	private Integer gerarNumeroAgendamento() {
		Integer ultimoNumero = agendamentoRepository.findMaxNumeroAgendamento();
		if (ultimoNumero == null) {
			ultimoNumero = 0;
		}
		return ultimoNumero + 1;
	}

	public GetAgendamentoDTO editarAgendamento(PutAgendamentoDTO dto) throws NotFoundException {
		// Verificar se o ID do agendamento está presente no DTO
		if (dto.getIdAgendamento() == null) {
			throw new IllegalArgumentException("O ID do agendamento deve ser fornecido para editar o agendamento.");
		}

		// Buscar o agendamento no banco de dados pelo ID
		Optional<Agendamento> agendamentoOptional = agendamentoRepository.findById(dto.getIdAgendamento());
		if (agendamentoOptional.isEmpty()) {
			throw new NotFoundException();
		}

		// Obter o agendamento existente do banco de dados
		Agendamento agendamentoExistente = agendamentoOptional.get();

		// Mapear os dados do DTO para o agendamento existente
		modelMapper.map(dto, agendamentoExistente);

		// Salvar as alterações no banco de dados
		Agendamento agendamentoAtualizado = agendamentoRepository.save(agendamentoExistente);

		// Mapear o agendamento atualizado para o DTO de resposta
		return modelMapper.map(agendamentoAtualizado, GetAgendamentoDTO.class);
	}

	public GetAgendamentoDTO buscarAgendamentoPorId(UUID idAgendamento) throws NotFoundException {
		Optional<Agendamento> agendamentoOptional = agendamentoRepository.findById(idAgendamento);
		if (agendamentoOptional.isEmpty()) {
			throw new NotFoundException();
		}
		return modelMapper.map(agendamentoOptional.get(), GetAgendamentoDTO.class);
	}

	public List<GetAgendamentoDTO> buscarTodosAgendamentos() {
		List<Agendamento> agendamentos = agendamentoRepository.findAll();
		return agendamentos.stream().map(agendamento -> modelMapper.map(agendamento, GetAgendamentoDTO.class))
				.collect(Collectors.toList());
	}

	public void excluirAgendamento(UUID idAgendamento) throws NotFoundException {
		if (!agendamentoRepository.existsById(idAgendamento)) {
			throw new NotFoundException();
		}
		agendamentoRepository.deleteById(idAgendamento);
	}

}
