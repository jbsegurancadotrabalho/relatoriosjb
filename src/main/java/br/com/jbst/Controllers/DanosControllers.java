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

import br.com.jbst.DTO.GetDanosDTO;
import br.com.jbst.DTO.PostDanosDTO;
import br.com.jbst.DTO.PostDanosRiscosDTO;
import br.com.jbst.DTO.PutDanosDTO;
import br.com.jbst.services.DanosServices;

@RestController
@RequestMapping("/danos")
public class DanosControllers {

    @Autowired
    private DanosServices danosServices;

    @PostMapping("/criar")
    public ResponseEntity<GetDanosDTO> criarDanos(@RequestBody PostDanosDTO dto) {
        GetDanosDTO createdDanos = danosServices.criarDanos(dto);
        return new ResponseEntity<>(createdDanos, HttpStatus.CREATED);
    }
    
    @PostMapping("/criar-danos-riscos")
    public ResponseEntity<GetDanosDTO> criarDanosRiscos(@RequestBody PostDanosRiscosDTO dto) {
        GetDanosDTO createdDanos = danosServices.criarDanosRiscos(dto);
        return new ResponseEntity<>(createdDanos, HttpStatus.CREATED);
    }
    
    @PutMapping("/editar")
    public ResponseEntity<GetDanosDTO> editarDanos(@RequestBody PutDanosDTO dto) {
        GetDanosDTO danosEditado = danosServices.editarDanos(dto);
        if (danosEditado != null) {
            return ResponseEntity.ok(danosEditado);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    
  
    
    @GetMapping("/todos")
    public ResponseEntity<List<GetDanosDTO>> buscarTodos() {
        List<GetDanosDTO> todosDanosDTO = danosServices.buscarTodos();
        return ResponseEntity.ok(todosDanosDTO);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<GetDanosDTO> buscarPorId(@PathVariable UUID id) {
        GetDanosDTO danosDTO = danosServices.buscarPorId(id);
        return danosDTO != null ? ResponseEntity.ok(danosDTO) : ResponseEntity.notFound().build();
    }

 

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluirDanos(@PathVariable UUID id) {
        boolean excluido = danosServices.excluirDanos(id);
        return excluido ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }
}
