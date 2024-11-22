package br.com.jbst.Controllers;


import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.jbst.DTO1.GetAgenda1DTO;
import br.com.jbst.DTO1.GetAgenda2DTO;
import br.com.jbst.DTO1.GetAgenda3DTO;
import br.com.jbst.DTO2.GetAgendaDTO;
import br.com.jbst.DTO2.PostAgendaDTO;
import br.com.jbst.DTO2.PutAgendaDTO;
import br.com.jbst.services.AgendaService;

@RestController
@RequestMapping("/api/agendas")
public class AgendaController {

    @Autowired
    private AgendaService agendaService;

    @PostMapping
    public ResponseEntity<GetAgenda3DTO> criarAgenda(@RequestBody PostAgendaDTO dto) {
        try {
            GetAgenda3DTO novaAgenda = agendaService.criarAgenda(dto);
            return new ResponseEntity<>(novaAgenda, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/editar")
    public ResponseEntity<GetAgenda3DTO> editarAgenda(@RequestBody PutAgendaDTO putAgendaDTO) {
        try {
            GetAgenda3DTO agendaAtualizada = agendaService.editarAgenda(putAgendaDTO);
            return ResponseEntity.ok(agendaAtualizada);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(null); // Bad request if ID is not provided
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null); // Internal server error for other exceptions
        }
    }
    

    @GetMapping("/{id}")
    public ResponseEntity<GetAgenda3DTO> consultarAgendaPorId(@PathVariable UUID id) {
        try {
            GetAgenda3DTO agendaDTO = agendaService.consultarAgendaPorId(id);
            return ResponseEntity.ok(agendaDTO);
        } catch (NotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/0")
	public ResponseEntity<List<GetAgendaDTO>> buscarTodasAsAgendas() {
        List<GetAgendaDTO> agendas = agendaService.buscarTodasAsAgendas();
        return ResponseEntity.ok(agendas);
    }
    
    @GetMapping("/1")
 	public ResponseEntity<List<GetAgenda1DTO>> buscarTodasAsAgendas1() {
         List<GetAgenda1DTO> agendas = agendaService.buscarTodasAsAgendas1();
         return ResponseEntity.ok(agendas);
     }
    
    @GetMapping("/2")
 	public ResponseEntity<List<GetAgenda2DTO>> buscarTodasAsAgendas2() {
         List<GetAgenda2DTO> agendas = agendaService.buscarTodasAsAgendas2();
         return ResponseEntity.ok(agendas);
     }
    
    @GetMapping("/3")
 	public ResponseEntity<List<GetAgenda3DTO>> buscarTodasAsAgendas3() {
         List<GetAgenda3DTO> agendas = agendaService.buscarTodasAsAgendas3();
         return ResponseEntity.ok(agendas);
     }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluirAgendaPorId(@PathVariable UUID id) {
        try {
            agendaService.excluirAgendaPorId(id);
            return ResponseEntity.noContent().build();
        } catch (NotFoundException e) {
            return ResponseEntity.notFound().build();
        }
       }
    }
