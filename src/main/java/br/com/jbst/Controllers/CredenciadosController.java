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

import br.com.jbst.DTO2.GetCredenciadosDTO;
import br.com.jbst.DTO2.PostCredenciadosDTO;
import br.com.jbst.DTO2.PutCredenciadosDTO;
import br.com.jbst.services.CredenciadoService;

@RestController
@RequestMapping("/credenciados")
public class CredenciadosController {

    @Autowired
    private CredenciadoService credenciadoService;

    @PostMapping
    public ResponseEntity<GetCredenciadosDTO> criarCredenciados(@RequestBody PostCredenciadosDTO dto) {
        try {
            GetCredenciadosDTO novoCredenciado = credenciadoService.criarCredenciados(dto);
            return ResponseEntity.status(HttpStatus.CREATED).body(novoCredenciado);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @PutMapping
    public ResponseEntity<GetCredenciadosDTO> editarCredenciados(@RequestBody PutCredenciadosDTO dto) {
        try {
            GetCredenciadosDTO credenciadoEditado = credenciadoService.editarCredenciados(dto);
            return ResponseEntity.ok(credenciadoEditado);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<GetCredenciadosDTO> buscarPorId(@PathVariable UUID id) {
        try {
            GetCredenciadosDTO credenciado = credenciadoService.buscarPorId(id);
            return ResponseEntity.ok(credenciado);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @GetMapping
    public ResponseEntity<List<GetCredenciadosDTO>> buscarTodosCredenciados() {
        try {
            List<GetCredenciadosDTO> credenciados = credenciadoService.buscarTodosCredenciados();
            return ResponseEntity.ok(credenciados);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarCredenciados(@PathVariable UUID id) {
        try {
            credenciadoService.deletarCredenciados(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}