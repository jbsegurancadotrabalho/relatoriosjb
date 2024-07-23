package br.com.jbst.Controllers;

import br.com.jbst.DTO2.GetQuadros_PaineisDTO;
import br.com.jbst.DTO2.PostQuadros_PaineisDTO;
import br.com.jbst.DTO2.PutQuadros_PaineisDTO;
import br.com.jbst.services.Quadros_PaineisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/quadros-paineis")
public class Quadros_PaineisController {

    private final Quadros_PaineisService quadrosPaineisService;

    @Autowired
    public Quadros_PaineisController(Quadros_PaineisService quadrosPaineisService) {
        this.quadrosPaineisService = quadrosPaineisService;
    }

    @PostMapping
    public ResponseEntity<GetQuadros_PaineisDTO> criarQuadros_Paineis(@RequestBody PostQuadros_PaineisDTO dto) {
        GetQuadros_PaineisDTO quadros_paineisDTO = quadrosPaineisService.criarQuadros_Paineis(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(quadros_paineisDTO);
    }

    @PutMapping
    public ResponseEntity<GetQuadros_PaineisDTO> editarQuadros_Paineis(@RequestBody PutQuadros_PaineisDTO dto) {
        GetQuadros_PaineisDTO quadros_paineisDTO = quadrosPaineisService.editarQuadros_Paineis(dto);
        return ResponseEntity.ok(quadros_paineisDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluirQuadros_PaineisPorId(@PathVariable UUID id) {
        quadrosPaineisService.excluirQuadros_PaineisPorId(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<GetQuadros_PaineisDTO> consultarQuadros_PaineisPorId(@PathVariable UUID id) {
        GetQuadros_PaineisDTO quadros_paineisDTO = quadrosPaineisService.consultarQuadros_PaineisPorId(id);
        return ResponseEntity.ok(quadros_paineisDTO);
    }

    @GetMapping
    public ResponseEntity<List<GetQuadros_PaineisDTO>> consultarTodosOsQuadros_Paineis() {
        List<GetQuadros_PaineisDTO> quadros_paineisDTOList = quadrosPaineisService.consultarTodosOsQuadros_Paineis();
        return ResponseEntity.ok(quadros_paineisDTOList);
    }
}
