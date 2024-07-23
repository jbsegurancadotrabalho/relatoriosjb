package br.com.jbst.Controllers;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
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

import br.com.jbst.DTO.GetCenarioDTO;
import br.com.jbst.DTO.GetCenarioUnidadeDTO;
import br.com.jbst.DTO.PostCenarioDTO;
import br.com.jbst.DTO.PutCenarioDTO;
import br.com.jbst.DTO.Cenarios.Documentos.GetCenario3DTO;
import br.com.jbst.DTO.Cenarios.Documentos.GetCenario4DTO;
import br.com.jbst.services.CenarioServices;

@RestController
@RequestMapping("/cenarios")
public class CenarioController {

    @Autowired
    private CenarioServices cenarioServices;

    @PostMapping
    public ResponseEntity<GetCenarioDTO> criarCenario(@RequestBody PostCenarioDTO dto) {
        try {
            GetCenarioDTO cenarioCriado = cenarioServices.criarCenario(dto);
            return ResponseEntity.status(HttpStatus.CREATED).body(cenarioCriado);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PutMapping
    public ResponseEntity<GetCenarioDTO> editarCenario(@RequestBody PutCenarioDTO dto) {
        try {
            GetCenarioDTO cenarioEditado = cenarioServices.editarCenario(dto);
            return ResponseEntity.ok(cenarioEditado);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<GetCenario4DTO> buscarCenarioPorId(@PathVariable UUID id) {
        try {
            GetCenario4DTO cenario = cenarioServices.buscarCenarioPorId(id);
            return ResponseEntity.ok(cenario);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping
    public ResponseEntity<List<GetCenarioDTO>> buscarTodosCenarios() {
        try {
            List<GetCenarioDTO> todosCenarios = cenarioServices.buscarTodosCenarios();
            return ResponseEntity.ok(todosCenarios);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
    
    @GetMapping("/getall")
    public ResponseEntity<List<GetCenario3DTO>> buscarTodosCenariosAssociacoes() {
        try {
            List<GetCenario3DTO> todosCenarios = cenarioServices.buscarTodosCenariosAssociacoes();
            return ResponseEntity.ok(todosCenarios);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
    
    @GetMapping("/unidades")
    public ResponseEntity<List<GetCenarioUnidadeDTO>> buscarCenariosUnidades() {
        try {
            List<GetCenarioUnidadeDTO> todosCenarios = cenarioServices.buscarCenariosUnidades();
            return ResponseEntity.ok(todosCenarios);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<GetCenarioDTO> excluirCenario(@PathVariable UUID id) {
        try {
            GetCenarioDTO cenarioExcluido = cenarioServices.excluirCenario(id);
            return ResponseEntity.ok(cenarioExcluido);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}

