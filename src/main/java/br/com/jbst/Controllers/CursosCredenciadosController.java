package br.com.jbst.Controllers;

import br.com.jbst.DTO2.GetCursos_CredenciadosDTO;
import br.com.jbst.DTO2.PostCursos_CredenciadosDTO;
import br.com.jbst.DTO2.PutCursos_CredenciadosDTO;
import br.com.jbst.services.CursosCredenciadosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/cursos-credenciados")
public class CursosCredenciadosController {

	@Autowired
    CursosCredenciadosService cursosCredenciadosService;

  

    @PostMapping
    public ResponseEntity<GetCursos_CredenciadosDTO> criarCursosCredenciados(@RequestBody PostCursos_CredenciadosDTO dto) {
        GetCursos_CredenciadosDTO result = cursosCredenciadosService.criarCursosCredenciados(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(result);
    }

    @PutMapping
    public ResponseEntity<GetCursos_CredenciadosDTO> AtualizarCursosCredenciados(@RequestBody PutCursos_CredenciadosDTO dto) {
        GetCursos_CredenciadosDTO result = cursosCredenciadosService.atualizarCursosCredenciados(dto);
        return ResponseEntity.ok(result);
    }

    @GetMapping
    public ResponseEntity<List<GetCursos_CredenciadosDTO>> buscarTodosCursosCredenciados() {
        List<GetCursos_CredenciadosDTO> result = cursosCredenciadosService.buscarTodosCursosCredenciados();
        return ResponseEntity.ok(result);
    }

    @GetMapping("/{id}")
    public ResponseEntity<GetCursos_CredenciadosDTO> buscarCursosCredenciadosPorId(@PathVariable UUID id) {
        GetCursos_CredenciadosDTO result = cursosCredenciadosService.buscarCursosCredenciadosPorId(id);
        return ResponseEntity.ok(result);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluirCursosCredenciadosPorId(@PathVariable UUID id) {
        cursosCredenciadosService.excluirCursosCredenciadosPorId(id);
        return ResponseEntity.noContent().build();
    }
}
