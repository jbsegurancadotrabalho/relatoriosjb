package br.com.jbst.services;

import java.time.Instant;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.stereotype.Service;

import br.com.jbst.DTO2.GetAgendamentoDTO;
import br.com.jbst.DTO2.GetAgendamentoPessoaFisicaDTO;
import br.com.jbst.DTO2.PostAgendamentoFuncionarioDTO;
import br.com.jbst.DTO2.PostAgendamentoPessoaFisicaDTO;
import br.com.jbst.DTO2.PutAgendamentoDTO;
import br.com.jbst.entities.Agenda;
import br.com.jbst.entities.Agendamento;
import br.com.jbst.entities.Funcionario;
import br.com.jbst.entities.PessoaFisica;
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

    public GetAgendamentoDTO criarAgendamentoFuncionario(PostAgendamentoFuncionarioDTO dto) {
        // Gerar um novo UUID para o agendamento
        UUID idAgendamento = UUID.randomUUID();

        // Gerar automaticamente o número do agendamento
        Integer numeroAgendamento = gerarNumeroAgendamento();

        // Mapear os dados do DTO para a entidade Agendamento
        Agendamento agendamento = modelMapper.map(dto, Agendamento.class);

        // Configurar os campos gerados automaticamente
        agendamento.setIdAgendamento(idAgendamento);
        agendamento.setNumeroagendamento(numeroAgendamento);

        // Verificar se o ID da agenda não é nulo e buscar a entidade
        if (dto.getIdAgenda() != null) {
            Agenda agenda = agendaRepository.findById(dto.getIdAgenda())
                .orElseThrow(() -> new IllegalArgumentException("Agenda com o ID fornecido não foi encontrada"));

            // Verificar se já existe um Agendamento associado a esta Agenda
            if (agenda.getAgendamento() != null && !agenda.getAgendamento().getIdAgendamento().equals(idAgendamento)) {
                throw new IllegalArgumentException("Esta Agenda já possui um Agendamento associado");
            }
            
            agendamento.setAgenda(agenda);
        } else {
            throw new IllegalArgumentException("ID da agenda não pode ser nulo");
        }

        // Verificar se o ID do funcionário não é nulo e buscar a entidade
        if (dto.getIdFuncionario() != null) {
            Funcionario funcionario = funcionarioRepository.findById(dto.getIdFuncionario())
                .orElseThrow(() -> new IllegalArgumentException("Funcionário com o ID fornecido não foi encontrado"));
            
            agendamento.setFuncionario(funcionario);
        } else {
            throw new IllegalArgumentException("ID do funcionário não pode ser nulo");
        }

        // Salvar a nova Agenda e Agendamento no banco de dados
        // Primeiro salvar a agenda se necessário
        if (agendamento.getAgenda().getIdAgenda() == null) {
            agendaRepository.save(agendamento.getAgenda());
        }

        // Salvar o Agendamento
        Agendamento agendamentos = agendamentoRepository.save(agendamento);

        // Mapear a entidade agendamento para o DTO de resposta
        return modelMapper.map(agendamentos, GetAgendamentoDTO.class);
    }

    public GetAgendamentoDTO criarAgendamentoPessoaFisica(PostAgendamentoPessoaFisicaDTO dto) {
        // Gerar um novo UUID para o agendamento
        UUID idAgendamento = UUID.randomUUID();

        // Gerar automaticamente o número do agendamento
        Integer numeroAgendamento = gerarNumeroAgendamento();

        // Mapear os dados do DTO para a entidade Agendamento
        Agendamento agendamento = modelMapper.map(dto, Agendamento.class);

        // Configurar os campos gerados automaticamente
        agendamento.setIdAgendamento(idAgendamento);
        agendamento.setNumeroagendamento(numeroAgendamento);

        // Verificar se o ID da agenda não é nulo e buscar a entidade
        if (dto.getIdAgenda() != null) {
            Agenda agenda = agendaRepository.findById(dto.getIdAgenda())
                .orElseThrow(() -> new IllegalArgumentException("Agenda com o ID fornecido não foi encontrada"));

            // Verificar se já existe um Agendamento associado a esta Agenda
            if (agenda.getAgendamento() != null && !agenda.getAgendamento().getIdAgendamento().equals(idAgendamento)) {
                throw new IllegalArgumentException("Esta Agenda já possui um Agendamento associado");
            }
            
            agendamento.setAgenda(agenda);
        } else {
            throw new IllegalArgumentException("ID da agenda não pode ser nulo");
        }

        // Verificar se o ID do funcionário não é nulo e buscar a entidade
        if (dto.getIdpessoafisica() != null) {
            PessoaFisica pessoafisica = pessoaFisicaRepository.findById(dto.getIdpessoafisica())
                .orElseThrow(() -> new IllegalArgumentException("Funcionário com o ID fornecido não foi encontrado"));
            
            agendamento.setPessoafisica(pessoafisica);
        } else {
            throw new IllegalArgumentException("ID do funcionário não pode ser nulo");
        }

        // Salvar a nova Agenda e Agendamento no banco de dados
        // Primeiro salvar a agenda se necessário
        if (agendamento.getAgenda().getIdAgenda() == null) {
            agendaRepository.save(agendamento.getAgenda());
        }

        // Salvar o Agendamento
        Agendamento agendamentos = agendamentoRepository.save(agendamento);

        // Mapear a entidade agendamento para o DTO de resposta
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
	 
	  public List<GetAgendamentoDTO> consultarAgendamentoFuncionariosPorMesAno(int mes, int ano) {
	        List<Agendamento> agendamentoFuncionarios = agendamentoRepository.findAgendamentoFuncionariosByMesAno(mes, ano);
	        return mapToDTOList(agendamentoFuncionarios);
	    }
	   private List<GetAgendamentoDTO> mapToDTOList(List<Agendamento> agendamentos) {
	        return modelMapper.map(agendamentos, new TypeToken<List<GetAgendamentoDTO>>() {}.getType());
	    }
	   
	  public List<GetAgendamentoPessoaFisicaDTO> consultarAgendamentoPessoaFisicaPorMesAno(int mes, int ano) {
	        List<Agendamento> agendamentoPessoaFisica = agendamentoRepository.findAgendamentoPessoaFisicaByMesAno(mes, ano);
	        return mapToDTOListPessoaFisica(agendamentoPessoaFisica);
	    }
	  
	   private List<GetAgendamentoPessoaFisicaDTO> mapToDTOListPessoaFisica(List<Agendamento> agendamentos) {
	        return modelMapper.map(agendamentos, new TypeToken<List<GetAgendamentoPessoaFisicaDTO>>() {}.getType());
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
