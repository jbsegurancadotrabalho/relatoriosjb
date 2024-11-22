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

import br.com.jbst.DTO1.GetExamesCredenciados1DTO;
import br.com.jbst.DTO1.GetExamesCredenciados2DTO;
import br.com.jbst.DTO1.GetExamesCredenciados4DTO;
import br.com.jbst.DTO2.GetExamesCredenciadosDTO;
import br.com.jbst.DTO2.PostExamesCredenciadosDTO;
import br.com.jbst.DTO2.PutExamesCredenciadosDTO;
import br.com.jbst.services.ExamesCredenciadosService;

@RestController
@RequestMapping("/exames-credenciados")
public class ExamesCredenciadosController {

    @Autowired
    private ExamesCredenciadosService examesCredenciadosService;

    @PostMapping
    public ResponseEntity<GetExamesCredenciadosDTO> criarExamesCredenciados(@RequestBody PostExamesCredenciadosDTO dto) {
        GetExamesCredenciadosDTO examesCredenciadosDTO = examesCredenciadosService.criarExamesCredenciados(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(examesCredenciadosDTO);
    }

    @PutMapping
    public ResponseEntity<GetExamesCredenciadosDTO> atualizarExamesCredenciados(@RequestBody PutExamesCredenciadosDTO dto) {
        GetExamesCredenciadosDTO examesCredenciadosDTO = examesCredenciadosService.atualizarExamesCredenciados(dto);
        return ResponseEntity.ok(examesCredenciadosDTO);
    }

    @GetMapping("/{id}")
    public ResponseEntity<GetExamesCredenciadosDTO> buscarExamesCredenciadosPorId(@PathVariable UUID id) {
        GetExamesCredenciadosDTO examesCredenciadosDTO = examesCredenciadosService.buscarExamesCredenciadosPorId(id);
        return ResponseEntity.ok(examesCredenciadosDTO);
    }

    @GetMapping("/1")
    public ResponseEntity<List<GetExamesCredenciadosDTO>> buscarTodosExamesCredenciados() {
        List<GetExamesCredenciadosDTO> examesCredenciadosDTOList = examesCredenciadosService.buscarTodosExamesCredenciados();
        return ResponseEntity.ok(examesCredenciadosDTOList);
    }
    
    @GetMapping("/2")
    public ResponseEntity<List<GetExamesCredenciados2DTO>> buscarTodosExamesCredenciados2() {
        List<GetExamesCredenciados2DTO> examesCredenciadosDTOList = examesCredenciadosService.buscarTodosExamesCredenciados2();
        return ResponseEntity.ok(examesCredenciadosDTOList);
    }
    
    @GetMapping("/4")
    public ResponseEntity<List<GetExamesCredenciados4DTO>> buscarTodosExamesCredenciados4() {
        List<GetExamesCredenciados4DTO> examesCredenciadosDTOList = examesCredenciadosService.buscarTodosExamesCredenciados4();
        return ResponseEntity.ok(examesCredenciadosDTOList);
    }
    
    @GetMapping
    public ResponseEntity<List<GetExamesCredenciados1DTO>> buscarTodosExamesCredenciados1() {
        List<GetExamesCredenciados1DTO> examesCredenciadosDTOList = examesCredenciadosService.buscarTodosExamesCredenciados1();
        return ResponseEntity.ok(examesCredenciadosDTOList);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluirExamesCredenciadosPorId(@PathVariable UUID id) {
        examesCredenciadosService.excluirExamesCredenciadosPorId(id);
        return ResponseEntity.noContent().build();
    }
}
