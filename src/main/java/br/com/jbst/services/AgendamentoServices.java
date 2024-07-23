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
import br.com.jbst.DTO2.PostAgendamentoDTO;
import br.com.jbst.DTO2.PutAgendamentoDTO;
import br.com.jbst.entities.Agendamento;
import br.com.jbst.repositories.modulo1.IAgendaRepository;
import br.com.jbst.repositories.modulo1.IAgendamentoRepository;
import br.com.jbst.repositories.modulo2.IFuncionarioRepository;
import br.com.jbst.repositories.modulo2.IPessoaFisicaRepository;

@Service
public class AgendamentoServices {

	@Autowired
	private ModelMapper modelMapper;
	
    @Autowired
    private IAgendaRepository agendaRepository;

    @Autowired
    private IAgendamentoRepository agendamentoRepository;

    @Autowired
    private IFuncionarioRepository funcionarioRepository;

    @Autowired
    private IPessoaFisicaRepository pessoaFisicaRepository;

    public GetAgendamentoDTO criarAgendamento(PostAgendamentoDTO dto) {
		// Gerar um novo UUID para a agenda
		UUID idAgendamento = UUID.randomUUID();

		// Gerar automaticamente o número da agenda
		Integer numeroAgendamento = gerarNumeroAgendamento();

		// Obter a data e hora atual

		// Mapear os dados do DTO para a entidade Agenda
		Agendamento agendamento = modelMapper.map(dto, Agendamento.class);

		// Configurar os campos gerados automaticamente
		agendamento.setIdAgendamento(UUID.randomUUID());
		agendamento.setNumeroagendamento(numeroAgendamento);
		int numeroagenda = gerarNumeroAgendamento();
		agendamento.setNumeroagendamento(numeroAgendamento);
		agendamento.setAgenda(agendaRepository.findById(dto.getIdAgenda()).orElse(null));
		agendamento.setPessoafisica(pessoaFisicaRepository.findById(dto.getIdpessoafisica()).orElse(null));
		agendamento.setFuncionario(funcionarioRepository.findById(dto.getIdFuncionario()).orElse(null));

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
	public GetAgendamentoDTO editarAgendamento(PutAgendamentoDTO dto) {
	    // Verificar se o DTO contém o ID do agendamento
	    if (dto.getIdAgendamento() == null) {
	        // Se o ID do agendamento não estiver presente no DTO, retornar null
	        return null;
	    }

	    // Encontrar o agendamento a ser editado pelo ID fornecido no DTO
	    Agendamento agendamento = agendamentoRepository.findById(dto.getIdAgendamento()).orElse(null);
	    if (agendamento == null) {
	        // Se o agendamento não for encontrado, retornar null
	        return null;
	    }

	    // Mapear os dados do DTO para a entidade Agendamento
	    modelMapper.map(dto, agendamento);

	    // Configurar o ID do agendamento
	    agendamento.setIdAgendamento(dto.getIdAgendamento());

	    // Verificar se os IDs de Agenda, Pessoa Física e Funcionário no DTO são válidos e atualizá-los
	    if (dto.getIdAgenda() != null) {
	        agendamento.setAgenda(agendaRepository.findById(dto.getIdAgenda()).orElse(null));
	    }
	    if (dto.getIdpessoafisica() != null) {
	        agendamento.setPessoafisica(pessoaFisicaRepository.findById(dto.getIdpessoafisica()).orElse(null));
	    }
	    if (dto.getIdFuncionario() != null) {
	        agendamento.setFuncionario(funcionarioRepository.findById(dto.getIdFuncionario()).orElse(null));
	    }

	    // Salvar as alterações no banco de dados
	    Agendamento agendamentoAtualizado = agendamentoRepository.save(agendamento);

	    // Mapear a entidade agendamento atualizada para o DTO de resposta
	    return modelMapper.map(agendamentoAtualizado, GetAgendamentoDTO.class);
	}
	 public GetAgendamentoDTO buscarPorId(UUID id) {
	        // Buscar o agendamento pelo ID fornecido
	        Agendamento agendamento = agendamentoRepository.findById(id).orElse(null);
	        if (agendamento == null) {
	            // Se o agendamento não for encontrado, retornar null
	            return null;
	        }

	        // Mapear a entidade agendamento para o DTO de resposta
	        return modelMapper.map(agendamento, GetAgendamentoDTO.class);
	    }

	    public List<GetAgendamentoDTO> buscarTodos() {
	        // Buscar todos os agendamentos
	        List<Agendamento> agendamentos = agendamentoRepository.findAll();

	        // Mapear a lista de entidades agendamento para uma lista de DTOs de resposta
	        return agendamentos.stream()
	                .map(agendamento -> modelMapper.map(agendamento, GetAgendamentoDTO.class))
	                .collect(Collectors.toList());
	    }
	    
	    public void excluirAgendaPorId(UUID idAgendamento) throws NotFoundException {
	        Optional<Agendamento> agendamentoOptional = agendamentoRepository.findById(idAgendamento);
	        if (agendamentoOptional.isPresent()) {
	            Agendamento agendamento = agendamentoOptional.get();
	            agendamentoRepository.delete(agendamento);
	        } else {
				throw new NotFoundException();
	        }
	    }


}
