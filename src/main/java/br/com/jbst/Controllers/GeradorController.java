package br.com.jbst.Controllers;


import br.com.jbst.DTO2.GetGeradorDTO;
import br.com.jbst.DTO2.PostGeradorDTO;
import br.com.jbst.DTO2.PutGeradorDTO;
import br.com.jbst.services.GeradorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/geradores")
public class GeradorController {

    private final GeradorService geradorService;

    @Autowired
    public GeradorController(GeradorService geradorService) {
        this.geradorService = geradorService;
    }

    @PostMapping
    public ResponseEntity<GetGeradorDTO> criarGerador( @RequestBody PostGeradorDTO dto) {
        GetGeradorDTO geradorDTO = geradorService.criarGerador(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(geradorDTO);
    }

    @PutMapping
    public ResponseEntity<GetGeradorDTO> editarGerador(  @RequestBody PutGeradorDTO dto) {
        GetGeradorDTO geradorDTO = geradorService.editarGerador( dto);
        return ResponseEntity.ok(geradorDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluirGeradorPorId(@PathVariable UUID id) throws NotFoundException {
        geradorService.excluirGeradorPorId(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<GetGeradorDTO> consultarGeradorPorId(@PathVariable UUID id) throws Exception {
        GetGeradorDTO geradorDTO = geradorService.consultarGeradorPorId(id);
        return ResponseEntity.ok(geradorDTO);
    }

    @GetMapping("/consultar-todos-os-geradores")
    public ResponseEntity<List<GetGeradorDTO>> buscarTodosOsGeradores() {
        List<GetGeradorDTO> geradores = geradorService.buscarTodosOsGeradores();
        return ResponseEntity.ok(geradores);
    }
}
