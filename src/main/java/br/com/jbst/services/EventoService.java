package br.com.jbst.services;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.jbst.DtoEvento.GetEventoDTO;
import br.com.jbst.DtoEvento.PostEventoDTO;
import br.com.jbst.DtoEvento.PutEventoDTO;
import br.com.jbst.entities.Evento;
import br.com.jbst.repositories.modulo1.IEventoRepository;

@Service
public class EventoService {
	@Autowired
	private IEventoRepository  ieventoRepository;
	
	@Autowired
	private ModelMapper modelMapper;
	
	public GetEventoDTO criarEvento(PostEventoDTO dto) {
	    // Gerar um novo UUID para a agenda
	    UUID idEvento = UUID.randomUUID();

	    // Gerar automaticamente o número da agenda
	    Integer numeroEvento = gerarNumeroEvento();

	    // Obter a data e hora atual

	    // Mapear os dados do DTO para a entidade Agenda
	    Evento evento = modelMapper.map(dto, Evento.class);

	    // Configurar os campos gerados automaticamente
	    evento.setIdEvento(UUID.randomUUID());
	    evento.setNumeroevento(numeroEvento);

	    // Salvar a nova agenda no banco de dados
	    Evento eventos = ieventoRepository.save(evento);

	    // Mapear a entidade agenda para o DTO de resposta
	    return modelMapper.map(eventos, GetEventoDTO.class);
	}
	 
	 private Integer gerarNumeroEvento() {
			Integer ultimoNumero = ieventoRepository.findMaxNumeroEvento();
			if (ultimoNumero == null) {
				ultimoNumero = 0;
			}
			return ultimoNumero + 1;
		}

	 public GetEventoDTO editarEvento(UUID idEvento, PutEventoDTO dto) {
	        // Buscar o evento existente pelo ID
	        Evento eventoExistente = ieventoRepository.findById(idEvento)
	                .orElseThrow(() -> new RuntimeException("Evento não encontrado"));

	        // Mapear os dados do DTO para a entidade existente
	        modelMapper.map(dto, eventoExistente);

	        // Salvar as alterações no banco de dados
	        Evento eventoAtualizado = ieventoRepository.save(eventoExistente);

	        // Retornar o DTO de resposta com os dados atualizados
	        return modelMapper.map(eventoAtualizado, GetEventoDTO.class);
	    }
	 
	 public GetEventoDTO consultarEventoPorId(UUID idEvento) {
	        // Buscar o evento pelo ID no repositório
	        Evento evento = ieventoRepository.findById(idEvento)
	                .orElseThrow(() -> new RuntimeException("Evento não encontrado"));

	        // Mapear a entidade Evento para o DTO de resposta
	        return modelMapper.map(evento, GetEventoDTO.class);
	    }
	 
	 public List<GetEventoDTO> consultarTodosEventos() {
	        // Buscar todos os eventos no repositório
	        List<Evento> eventos = ieventoRepository.findAll();

	        // Mapear a lista de entidades Evento para uma lista de GetEventoDTO
	        return eventos.stream()
	                .map(evento -> modelMapper.map(evento, GetEventoDTO.class))
	                .collect(Collectors.toList());
	    }
	 
	 public GetEventoDTO excluirEvento(UUID idEvento) {
		    // Verificar se o evento existe
		    Evento evento = ieventoRepository.findById(idEvento)
		            .orElseThrow(() -> new RuntimeException("Evento não encontrado"));

		    // Mapear o evento para GetEventoDTO antes de excluí-lo
		    GetEventoDTO eventoDTO = modelMapper.map(evento, GetEventoDTO.class);

		    // Excluir o evento
		    ieventoRepository.delete(evento);

		    // Retornar o DTO do evento que foi excluído
		    return eventoDTO;
		}
	 
	 public List<GetEventoDTO> consultarEventosDoMesAtual() {
	        // Obter a data do início e fim do mês atual
	        Instant startOfMonth = LocalDate.now().withDayOfMonth(1).atStartOfDay(ZoneId.systemDefault()).toInstant();
	        Instant endOfMonth = LocalDate.now().withDayOfMonth(LocalDate.now().lengthOfMonth())
	                .atTime(23, 59, 59)
	                .atZone(ZoneId.systemDefault())
	                .toInstant();

	        // Buscar eventos do mês atual no repositório
	        List<Evento> eventos = ieventoRepository.findEventosDoMes(startOfMonth, endOfMonth);

	        // Mapear a lista de entidades Evento para uma lista de GetEventoDTO
	        return eventos.stream()
	                .map(evento -> modelMapper.map(evento, GetEventoDTO.class))
	                .collect(Collectors.toList());
	    }
}
