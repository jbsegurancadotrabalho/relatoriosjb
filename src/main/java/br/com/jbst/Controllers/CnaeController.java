package br.com.jbst.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import br.com.jbst.DTO.GetCnaeDto;
import br.com.jbst.DTO.PostCnaeDTO;
import br.com.jbst.DTO.PutCnaeDto;
import br.com.jbst.services.CnaeService;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/cnaes")
public class CnaeController {

    @Autowired
    private CnaeService cnaeService;

    @PostMapping
    public ResponseEntity<GetCnaeDto> criarCnae(@RequestBody PostCnaeDTO dto) {
        try {
            GetCnaeDto createdCnae = cnaeService.criarCnae(dto);
            return ResponseEntity.status(HttpStatus.CREATED).body(createdCnae);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @GetMapping
    public ResponseEntity<List<GetCnaeDto>> getAllCnaes() {
        List<GetCnaeDto> cnaes = cnaeService.getAllCnaes();
        return ResponseEntity.ok(cnaes);
    }

    @GetMapping("/{id}")
    public ResponseEntity<GetCnaeDto> getCnaeById(@PathVariable UUID id) {
        try {
            GetCnaeDto cnae = cnaeService.getCnaeById(id);
            return ResponseEntity.ok(cnae);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping
    public ResponseEntity<GetCnaeDto> editarCnae(@RequestBody PutCnaeDto dto) {
        try {
            GetCnaeDto editedCnae = cnaeService.editarCnae(dto);
            return ResponseEntity.ok(editedCnae);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluirCnae(@PathVariable UUID id) {
        try {
            cnaeService.excluirCnae(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }
}

