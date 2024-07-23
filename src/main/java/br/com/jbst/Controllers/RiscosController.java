package br.com.jbst.Controllers;

import br.com.jbst.DTO.GetRiscosDTO;
import br.com.jbst.DTO.PostRiscosDTO;
import br.com.jbst.DTO.PostRiscosIncluirFuncaoEspecificaDTO;
import br.com.jbst.DTO.PutRiscosDTO;
import br.com.jbst.services.RiscosServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;

@RestController
@RequestMapping("/riscos")
public class RiscosController {

    @Autowired
    RiscosServices riscosServices;

    @PostMapping("/criar")
    public ResponseEntity<GetRiscosDTO> criarRiscos(@RequestBody PostRiscosDTO dto) {
        try {
            // Chamando o serviço para criar o risco
            GetRiscosDTO novoRisco = riscosServices.criarRiscos(dto);
            return new ResponseEntity<>(novoRisco, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @PostMapping("/incluir-funcao-especifica")
    public ResponseEntity<GetRiscosDTO> criarRiscosIncluirFuncaoEspecifica(@RequestBody PostRiscosIncluirFuncaoEspecificaDTO dto) {
        try {
            GetRiscosDTO riscosDTO = riscosServices.criarRiscosIncluirFuncaoEspecifica(dto);
            return ResponseEntity.ok(riscosDTO);
        } catch (Exception e) {
            return ResponseEntity.status(500).body(null);
        }
    }

    @PutMapping
    public ResponseEntity<GetRiscosDTO> criarRiscos(@RequestBody PutRiscosDTO dto) {
        try {
            // Chamando o serviço para criar o risco
            GetRiscosDTO novoRisco = riscosServices.atualizarRiscos(dto);
            return new ResponseEntity<>(novoRisco, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<GetRiscosDTO> buscarRiscoPorId(@PathVariable UUID id) {
        try {
            // Chama o serviço para buscar o risco por ID
            GetRiscosDTO risco = riscosServices.buscarPorId(id);
            // Retorna o risco encontrado
            return ResponseEntity.ok(risco);
        } catch (NoSuchElementException e) {
            // Retorna uma resposta com status 404 (Not Found) se o risco não for encontrado
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            // Retorna uma resposta com status 500 (Internal Server Error) se ocorrer um erro inesperado
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                                 .body(new GetRiscosDTO(id, "Erro ao buscar o risco: " + e.getMessage(), null, null, null, null, null, null, null, null, null, null));
        }
    }

    @GetMapping("/consultar-todos-os-riscos")
    public ResponseEntity<List<GetRiscosDTO>> consultarRiscos() {
        List<GetRiscosDTO> riscos = riscosServices.buscarTodosRiscos();
        if (riscos.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(riscos);
    }

    @DeleteMapping("/excluir/{idRisco}")
    public ResponseEntity<String> excluirRiscoPorId(@PathVariable UUID idRisco) {
        try {
            riscosServices.excluirRiscoPorId(idRisco);
            return ResponseEntity.ok("Risco excluído com sucesso.");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao excluir risco: " + e.getMessage());
        }
    }
}
