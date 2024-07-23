package br.com.jbst.Controllers;


import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import br.com.jbst.DTO2.GetEspecializacaoSaudeDTO;
import br.com.jbst.DTO2.PostEspecializacaoSaudeDTO;
import br.com.jbst.DTO2.PutEspecializacaoSaudeDTO;
import br.com.jbst.services.EspecializacaoSaudeService;

@RestController
@RequestMapping("/api/especializacoes")
public class EspecializacaoSaudeController {

    @Autowired
    private EspecializacaoSaudeService especializacaoSaudeService;

    @PostMapping
    public ResponseEntity<GetEspecializacaoSaudeDTO> criarEspecializacaoSaude(@RequestBody PostEspecializacaoSaudeDTO dto) {
        try {
            GetEspecializacaoSaudeDTO especializacaoCriada = especializacaoSaudeService.criarEspecializacaoSaude(dto);
            return new ResponseEntity<>(especializacaoCriada, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping
    public ResponseEntity<GetEspecializacaoSaudeDTO> atualizarEspecializacaoSaude(@RequestBody PutEspecializacaoSaudeDTO dto) {
        try {
            GetEspecializacaoSaudeDTO especializacaoAtualizada = especializacaoSaudeService.atualizarEspecializacaoSaude(dto);
            return new ResponseEntity<>(especializacaoAtualizada, HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<GetEspecializacaoSaudeDTO> buscarEspecializacaoPorId(@PathVariable UUID id) {
        try {
            GetEspecializacaoSaudeDTO especializacao = especializacaoSaudeService.buscarEspecializacaoPorId(id);
            return new ResponseEntity<>(especializacao, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping
    public ResponseEntity<List<GetEspecializacaoSaudeDTO>> buscarTodasEspecializacoes() {
        List<GetEspecializacaoSaudeDTO> especializacoes = especializacaoSaudeService.buscarTodasEspecializacoes();
        return new ResponseEntity<>(especializacoes, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluirEspecializacao(@PathVariable UUID id) {
        try {
            especializacaoSaudeService.excluirEspecializacao(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
