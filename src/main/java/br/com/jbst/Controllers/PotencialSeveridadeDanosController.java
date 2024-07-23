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
import org.springframework.web.bind.annotation.RestController;

import br.com.jbst.DTO.GetPotencialSeveridadeDanosDTO;
import br.com.jbst.DTO.PostPotencialSeveridadeDanosDTO;
import br.com.jbst.DTO.PostPotencialSeveridadeDanosIncluirRiscosDTO;
import br.com.jbst.DTO.PutPotencialSeveridadeDanosDTO;
import br.com.jbst.DTO.PutPotencialSeveridadeDanosIncluirRiscosDTO;
import br.com.jbst.services.PotencialSeveridadeDanosService;

@RestController
public class PotencialSeveridadeDanosController {

    @Autowired
    PotencialSeveridadeDanosService potencialSeveridadeDanosService;

    @PostMapping("/potencial-severidade-danos")
    public ResponseEntity<GetPotencialSeveridadeDanosDTO> criarPotencialSeveridadeDanos(@RequestBody PostPotencialSeveridadeDanosDTO dto) {
        GetPotencialSeveridadeDanosDTO responseDTO = potencialSeveridadeDanosService.criarPotencialSeveridadeDanos(dto);
        return new ResponseEntity<>(responseDTO, HttpStatus.CREATED);
    }
    
    @PostMapping("/potencial-severidade-danos-riscos")
    public ResponseEntity<GetPotencialSeveridadeDanosDTO> criarPotencialSeveridadeDanosRiscos(@RequestBody PostPotencialSeveridadeDanosIncluirRiscosDTO dto) {
        GetPotencialSeveridadeDanosDTO responseDTO = potencialSeveridadeDanosService.criarPotencialSeveridadeDanosIncluirRiscos(dto);
        return new ResponseEntity<>(responseDTO, HttpStatus.CREATED);
    }
    
    @PutMapping("/potencial-severidade-danos")
    public ResponseEntity<GetPotencialSeveridadeDanosDTO> editarPotencialSeveridadeDanos(@RequestBody PutPotencialSeveridadeDanosDTO dto) {
        GetPotencialSeveridadeDanosDTO potencialSeveridadeDanosDTO = potencialSeveridadeDanosService.editarPotencialSeveridadeDanos(dto);
        if (potencialSeveridadeDanosDTO != null) {
            return ResponseEntity.ok(potencialSeveridadeDanosDTO);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    
    @PutMapping("/potencial-severidade-danos-riscos")
    public ResponseEntity<GetPotencialSeveridadeDanosDTO> editarPotencialSeveridadeDanosRiscos(@RequestBody PutPotencialSeveridadeDanosIncluirRiscosDTO dto) {
        GetPotencialSeveridadeDanosDTO potencialSeveridadeDanosDTO = potencialSeveridadeDanosService.editarPotencialSeveridadeDanosIncluirRiscos(dto);
        if (potencialSeveridadeDanosDTO != null) {
            return ResponseEntity.ok(potencialSeveridadeDanosDTO);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    
    
    @GetMapping("/{id}")
    public ResponseEntity<GetPotencialSeveridadeDanosDTO> buscarPotencialSeveridadeDanosPorId(@PathVariable UUID id) {
        GetPotencialSeveridadeDanosDTO potencialSeveridadeDanosDTO = potencialSeveridadeDanosService.buscarPotencialSeveridadeDanosPorId(id);
        return ResponseEntity.ok(potencialSeveridadeDanosDTO);
    }

    @GetMapping("/buscar-todos-potencial-severidade-danos")
    public ResponseEntity<List<GetPotencialSeveridadeDanosDTO>> buscarTodosPotencialSeveridadeDanos() {
        List<GetPotencialSeveridadeDanosDTO> potencialSeveridadeDanosDTOList = potencialSeveridadeDanosService.buscarTodosPotencialSeveridadeDanos();
        return ResponseEntity.ok(potencialSeveridadeDanosDTOList);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluirPotencialSeveridadeDanos(@PathVariable UUID id) {
        potencialSeveridadeDanosService.excluirPotencialSeveridadeDanos(id);
        return ResponseEntity.noContent().build();
    }

}
