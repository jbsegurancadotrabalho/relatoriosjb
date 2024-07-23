package br.com.jbst.Controllers;


import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import br.com.jbst.DTO.GetFuncaoEspecificaDTO;
import br.com.jbst.DTO.PostFuncaoEspecificaDTO;
import br.com.jbst.DTO.PutFuncaoEspecificaDTO;
import br.com.jbst.services.FuncaoEspecificaService;

@RestController
@RequestMapping("/funcoes-especificas")
public class FuncaoEspecificaController {

    @Autowired
    private FuncaoEspecificaService funcaoEspecificaService;

    @PostMapping
    public ResponseEntity<GetFuncaoEspecificaDTO> criarFuncaoEspecifica(@RequestBody PostFuncaoEspecificaDTO dto) {
        try {
            GetFuncaoEspecificaDTO created = funcaoEspecificaService.criarFuncaoEspecifica(dto);
            return ResponseEntity.ok(created);
        } catch (Exception e) {
            return ResponseEntity.status(500).build();
        }
    }

    @PutMapping
    public ResponseEntity<GetFuncaoEspecificaDTO> editarFuncaoEspecifica( @RequestBody PutFuncaoEspecificaDTO dto) {
        try {
            GetFuncaoEspecificaDTO updated = funcaoEspecificaService.editarFuncaoEspecifica(dto);
            if (updated == null) {
                return ResponseEntity.notFound().build();
            }
            return ResponseEntity.ok(updated);
        } catch (Exception e) {
            return ResponseEntity.status(500).build();
        }
    }

    @GetMapping
    public ResponseEntity<List<GetFuncaoEspecificaDTO>> buscarTodasFuncoesEspecificas() {
        List<GetFuncaoEspecificaDTO> funcoes = funcaoEspecificaService.buscarTodasFuncoesEspecificas();
        return ResponseEntity.ok(funcoes);
    }

    @GetMapping("/{id}")
    public ResponseEntity<GetFuncaoEspecificaDTO> buscarFuncaoEspecificaPorId(@PathVariable UUID id) {
        GetFuncaoEspecificaDTO funcao = funcaoEspecificaService.buscarFuncaoEspecificaPorId(id);
        if (funcao == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(funcao);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<GetFuncaoEspecificaDTO> excluirFuncaoEspecifica(@PathVariable UUID id) {
        try {
            GetFuncaoEspecificaDTO deleted = funcaoEspecificaService.excluirFuncaoEspecificaPorId(id);
            return ResponseEntity.ok(deleted);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            return ResponseEntity.status(500).build();
        }
    }
}
