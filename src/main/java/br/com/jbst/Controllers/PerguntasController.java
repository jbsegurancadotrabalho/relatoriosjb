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

import br.com.jbst.DTO2.GetPerguntasDTO;
import br.com.jbst.DTO2.PostPerguntasAterramentoDTO;
import br.com.jbst.DTO2.PostPerguntasCondutoresDTO;
import br.com.jbst.DTO2.PostPerguntasLuminariasDTO;
import br.com.jbst.DTO2.PostPerguntasProcedimentosDTO;
import br.com.jbst.DTO2.PostPerguntasProtecoesDTO;
import br.com.jbst.DTO2.PostPerguntasQuadrosMediçõesDTO;
import br.com.jbst.DTO2.PostPerguntasQuadrosPaineisDTO;
import br.com.jbst.DTO2.PostPerguntasTomadasDTO;
import br.com.jbst.DTO2.PutPerguntasDTO;
import br.com.jbst.services.PerguntasService;

@RestController
@RequestMapping("/api/perguntas")
public class PerguntasController {

    @Autowired
    private PerguntasService perguntasService;

    @PostMapping("/criar-perguntas-quadros-medicoes")
    public ResponseEntity<String> criarPerguntasQuadrosMedicoes(@RequestBody PostPerguntasQuadrosMediçõesDTO dto) {
        try {
            perguntasService.criarPerguntasQuadrosMedições(dto);
            return ResponseEntity.status(HttpStatus.CREATED).body("Pergunta associada aos quadros de medições criada com sucesso!");
        } catch (Exception e) {
            // Handle exception here
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao criar pergunta associada aos quadros de medições: " + e.getMessage());
        }
    }

    @PostMapping("/criar-perguntas-quadros-paineis")
    public ResponseEntity<String> criarPerguntasQuadrosPaineis(@RequestBody PostPerguntasQuadrosPaineisDTO dto) {
        try {
            perguntasService.criarPerguntasQuadrosPaineis(dto);
            return ResponseEntity.status(HttpStatus.CREATED).body("Pergunta associada aos quadros e painéis criada com sucesso!");
        } catch (Exception e) {
            // Handle exception here
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao criar pergunta associada aos quadros e painéis: " + e.getMessage());
        }
    }

    @PostMapping("/criar-perguntas-protecoes")
    public ResponseEntity<String> criarPerguntasProtecoes(@RequestBody PostPerguntasProtecoesDTO dto) {
        try {
            perguntasService.criarPerguntasProtecoes(dto);
            return ResponseEntity.status(HttpStatus.CREATED).body("Pergunta associada às proteções criada com sucesso!");
        } catch (Exception e) {
            // Handle exception here
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao criar pergunta associada às proteções: " + e.getMessage());
        }
    }

    @PostMapping("/criar-perguntas-tomadas")
    public ResponseEntity<String> criarPerguntasTomadas(@RequestBody PostPerguntasTomadasDTO dto) {
        try {
            perguntasService.criarPerguntasTomadas(dto);
            return ResponseEntity.status(HttpStatus.CREATED).body("Pergunta associada às tomadas criada com sucesso!");
        } catch (Exception e) {
            // Handle exception here
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao criar pergunta associada às tomadas: " + e.getMessage());
        }
    }

    @PostMapping("/criar-perguntas-procedimentos")
    public ResponseEntity<String> CriarPerguntasProcedimentos(@RequestBody PostPerguntasProcedimentosDTO dto) {
        try {
        	perguntasService.criarPerguntasProcedimentos(dto);
            return ResponseEntity.status(HttpStatus.CREATED).body("Perguntas com procedimentos criadas com sucesso.");
        } catch (IllegalArgumentException e) {
            // Caso o procedimento não seja encontrado
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        } catch (Exception e) {
            // Outras exceções
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Ocorreu um erro ao criar perguntas com procedimentos.");
        }
    }
    
    
    @PostMapping("/criar-perguntas-condutores")
    public ResponseEntity<String> CriarPerguntasCondutores(@RequestBody PostPerguntasCondutoresDTO dto) {
        try {
        	perguntasService.criarPerguntasCondutores(dto);
            return ResponseEntity.status(HttpStatus.CREATED).body("Perguntas com procedimentos criadas com sucesso.");
        } catch (IllegalArgumentException e) {
            // Caso o procedimento não seja encontrado
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        } catch (Exception e) {
            // Outras exceções
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Ocorreu um erro ao criar perguntas com procedimentos.");
        }
    }
    
    @PostMapping("/criar-perguntas-luminarias")
    public ResponseEntity<String> CriarPerguntasLuminarias(@RequestBody PostPerguntasLuminariasDTO dto) {
        try {
        	perguntasService.criarPerguntasLuminarias(dto);
            return ResponseEntity.status(HttpStatus.CREATED).body("Perguntas com procedimentos criadas com sucesso.");
        } catch (IllegalArgumentException e) {
            // Caso o procedimento não seja encontrado
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        } catch (Exception e) {
            // Outras exceções
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Ocorreu um erro ao criar perguntas com procedimentos.");
        }
    }
    
    @PostMapping("/criar-perguntas-aterramento")
    public ResponseEntity<String> CriarPerguntasAterramento(@RequestBody PostPerguntasAterramentoDTO dto) {
        try {
        	perguntasService.criarPerguntasAterramento(dto);
            return ResponseEntity.status(HttpStatus.CREATED).body("Perguntas com procedimentos criadas com sucesso.");
        } catch (IllegalArgumentException e) {
            // Caso o procedimento não seja encontrado
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        } catch (Exception e) {
            // Outras exceções
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Ocorreu um erro ao criar perguntas com procedimentos.");
        }
    }
    
    
    @PutMapping("/atualizar-perguntas")
    public ResponseEntity<String> atualizarPergunta( @RequestBody PutPerguntasDTO dto) {
        try {
            perguntasService.atualizarPerguntas(dto);
            return ResponseEntity.ok("Pergunta atualizada com sucesso");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Falha ao atualizar pergunta: " + e.getMessage());
        }
    }
    
      

    
    @GetMapping("/consular-todas-as-perguntas")
    public ResponseEntity<List<GetPerguntasDTO>> buscarTodasAsPerguntas() {
        List<GetPerguntasDTO> perguntas = perguntasService.buscarTodasAsPerguntas();
        return ResponseEntity.ok(perguntas);
    }
    
    
    @GetMapping("/{id}")
    public ResponseEntity<GetPerguntasDTO> buscarPerguntaPorId(@PathVariable UUID id) {
        GetPerguntasDTO pergunta = perguntasService.buscarPerguntaPorId(id);
        if (pergunta != null) {
            return new ResponseEntity<>(pergunta, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<String> excluirPergunta(@PathVariable("id") UUID idPergunta) {
        try {
            perguntasService.excluirPergunta(idPergunta);
            return ResponseEntity.ok("Pergunta excluída com sucesso.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Erro ao excluir pergunta: " + e.getMessage());
        }
    }
}
