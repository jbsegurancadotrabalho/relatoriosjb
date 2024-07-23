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

import br.com.jbst.DTO2.GetCondutoresDTO;
import br.com.jbst.DTO2.PostCondutoresDTO;
import br.com.jbst.DTO2.PutCondutoresDTO;
import br.com.jbst.services.CondutoresService;

@RestController
@RequestMapping("/api/condutores")
public class CondutorController {
    @Autowired
    private CondutoresService condutoresService;

    @PostMapping
    public ResponseEntity<GetCondutoresDTO> criarCondutores(@RequestBody PostCondutoresDTO dto) {
        GetCondutoresDTO condutoresDTO = condutoresService.criarCondutores(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(condutoresDTO);
    }

    @PutMapping
    public ResponseEntity<GetCondutoresDTO> editarCondutores(@RequestBody PutCondutoresDTO dto) {
        GetCondutoresDTO condutoresDTO = condutoresService.editarCondutores(dto);
        return ResponseEntity.ok(condutoresDTO);
    }

    @GetMapping("/{id}")
    public ResponseEntity<GetCondutoresDTO> consultarCondutoresPorId(@PathVariable UUID id) {
        GetCondutoresDTO condutoresDTO = condutoresService.consultarCondutoresPorId(id);
        return ResponseEntity.ok(condutoresDTO);
    }

    @GetMapping
    public ResponseEntity<List<GetCondutoresDTO>> consultarTodosOsCondutores() {
        List<GetCondutoresDTO> condutoresDTOList = condutoresService.consultarTodosOsCondutores();
        return ResponseEntity.ok(condutoresDTOList);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluirCondutores(@PathVariable UUID id) {
        condutoresService.excluirCondutoresPorId(id);
        return ResponseEntity.noContent().build();
    }
}
