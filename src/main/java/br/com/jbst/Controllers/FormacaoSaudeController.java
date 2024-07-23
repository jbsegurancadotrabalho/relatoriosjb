package br.com.jbst.Controllers;


import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import br.com.jbst.DTO2.GetFormacaoSaudeDTO;
import br.com.jbst.DTO2.PostFormacaoSaudeDTO;
import br.com.jbst.DTO2.PutFormacaoSaudeDTO;
import br.com.jbst.services.FormacaoSaudeService;

@RestController
@RequestMapping("/formacoes-saude")
public class FormacaoSaudeController {

    @Autowired
    FormacaoSaudeService formacaoSaudeService;

 
    @PutMapping
    public ResponseEntity<GetFormacaoSaudeDTO> atualizarFormacaoSaude(@RequestBody PutFormacaoSaudeDTO dto) {
        // Verifique se o DTO contém um ID para identificar o objeto a ser atualizado
        UUID idFormacaoSaude = dto.getIdFormacaoSaude();
        if (idFormacaoSaude == null) {
            throw new IllegalArgumentException("ID da formação de saúde não fornecido para atualização.");
        }

        // Chamamos o serviço para atualizar a formação de saúde
        GetFormacaoSaudeDTO formacaoSaudeDTO = formacaoSaudeService.atualizarFormacaoSaude(dto);

        // Retornamos a formação de saúde atualizada no corpo da resposta
        return ResponseEntity.ok(formacaoSaudeDTO);
    }
    @PostMapping
    public ResponseEntity<GetFormacaoSaudeDTO> criarFormacaoSaude(@RequestBody PostFormacaoSaudeDTO dto) {
        // Chamamos o serviço para criar a formação de saúde
        GetFormacaoSaudeDTO formacaoSaudeDTO = formacaoSaudeService.criarFormacaoSaude(dto);

        // Retornamos a formação de saúde recém-criada no corpo da resposta
        return ResponseEntity.status(HttpStatus.CREATED).body(formacaoSaudeDTO);
    }


    @GetMapping
    public ResponseEntity<List<GetFormacaoSaudeDTO>> buscarTodasFormacoesSaude() {
        List<GetFormacaoSaudeDTO> formacoesSaudeDTO = formacaoSaudeService.buscarTodasFormacoesSaude();
        return ResponseEntity.ok(formacoesSaudeDTO);
    }

    @GetMapping("/{id}")
    public ResponseEntity<GetFormacaoSaudeDTO> buscarFormacaoSaudePorId(@PathVariable UUID id) {
        GetFormacaoSaudeDTO formacaoSaudeDTO = formacaoSaudeService.buscarFormacaoSaudePorId(id);
        return ResponseEntity.ok(formacaoSaudeDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluirFormacaoSaudePorId(@PathVariable UUID id) {
        formacaoSaudeService.excluirFormacaoSaudePorId(id);
        return ResponseEntity.noContent().build();
    }
}
