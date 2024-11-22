package br.com.jbst.Controllers;


import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import br.com.jbst.DtoEvento.GetEventoDTO;
import br.com.jbst.DtoEvento.PostEventoDTO;
import br.com.jbst.DtoEvento.PutEventoDTO;
import br.com.jbst.services.EventoService;

@RestController
@RequestMapping("/eventos")
public class EventoController {

    @Autowired
    private EventoService eventoService;

    // Criar um novo evento
    @PostMapping
    public ResponseEntity<GetEventoDTO> criarEvento(@RequestBody PostEventoDTO dto) {
        GetEventoDTO eventoCriado = eventoService.criarEvento(dto);
        return new ResponseEntity<>(eventoCriado, HttpStatus.CREATED);
    }

    // Editar um evento existente
    @PutMapping("/{idEvento}")
    public ResponseEntity<GetEventoDTO> editarEvento(@PathVariable UUID idEvento, @RequestBody PutEventoDTO dto) {
        GetEventoDTO eventoAtualizado = eventoService.editarEvento(idEvento, dto);
        return new ResponseEntity<>(eventoAtualizado, HttpStatus.OK);
    }

    // Consultar um evento por ID
    @GetMapping("/{idEvento}")
    public ResponseEntity<GetEventoDTO> consultarEventoPorId(@PathVariable UUID idEvento) {
        GetEventoDTO evento = eventoService.consultarEventoPorId(idEvento);
        return new ResponseEntity<>(evento, HttpStatus.OK);
    }

    // Consultar todos os eventos
    @GetMapping
    public ResponseEntity<List<GetEventoDTO>> consultarTodosEventos() {
        List<GetEventoDTO> eventos = eventoService.consultarTodosEventos();
        return new ResponseEntity<>(eventos, HttpStatus.OK);
    }

    // Consultar os eventos do mês atual
    @GetMapping("/mes-atual")
    public ResponseEntity<List<GetEventoDTO>> consultarEventosDoMesAtual() {
        List<GetEventoDTO> eventosDoMes = eventoService.consultarEventosDoMesAtual();
        return new ResponseEntity<>(eventosDoMes, HttpStatus.OK);
    }

    // Excluir um evento por ID
    @DeleteMapping("/{idEvento}")
    public ResponseEntity<GetEventoDTO> excluirEvento(@PathVariable UUID idEvento) {
        GetEventoDTO eventoExcluido = eventoService.excluirEvento(idEvento);
        return new ResponseEntity<>(eventoExcluido, HttpStatus.OK);
    }
}

