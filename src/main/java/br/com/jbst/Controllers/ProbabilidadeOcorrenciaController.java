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

import br.com.jbst.DTO.GetProbabilidadeOcorrenciaDTO;
import br.com.jbst.DTO.PostProbabilidadeOcorrenciaDTO;
import br.com.jbst.DTO.PostProbabilidadeOcorrenciaIncluirRiscosDTO;
import br.com.jbst.DTO.PutProbabilidadeOcorrenciaDTO;
import br.com.jbst.DTO.PutProbabilidadeOcorrenciaIncluirRiscosDTO;
import br.com.jbst.services.ProbabilidadeOcorrenciaService;

@RestController
@RequestMapping("/api/probabilidade-ocorrencia")
public class ProbabilidadeOcorrenciaController {

    @Autowired
    private ProbabilidadeOcorrenciaService probabilidadeOcorrenciaService;

    @PostMapping
    public ResponseEntity<GetProbabilidadeOcorrenciaDTO> criarProbabilidadeOcorrencia(@RequestBody PostProbabilidadeOcorrenciaDTO dto) {
        GetProbabilidadeOcorrenciaDTO probabilidadeOcorrenciaDTO = probabilidadeOcorrenciaService.criarProbabilidadeOcorrencia(dto);
        return new ResponseEntity<>(probabilidadeOcorrenciaDTO, HttpStatus.CREATED);
    }
    
    @PostMapping("/criar-probabilidade-de-ocorrencia-riscos")
    public ResponseEntity<GetProbabilidadeOcorrenciaDTO> criarProbabilidadeOcorrenciaRiscos(@RequestBody PostProbabilidadeOcorrenciaIncluirRiscosDTO dto) {
        GetProbabilidadeOcorrenciaDTO probabilidadeOcorrenciaDTO = probabilidadeOcorrenciaService.criarProbabilidadeOcorrenciaRiscos(dto);
        return new ResponseEntity<>(probabilidadeOcorrenciaDTO, HttpStatus.CREATED);
    }


    @PutMapping("/editar-probabilidade-de-ocorrencia-riscos")
    public ResponseEntity<GetProbabilidadeOcorrenciaDTO> EditarProbabilidadeOcorrenciaRiscos(@RequestBody PutProbabilidadeOcorrenciaIncluirRiscosDTO dto) {
        GetProbabilidadeOcorrenciaDTO probabilidadeOcorrenciaDTO = probabilidadeOcorrenciaService.editarProbabilidadeOcorrenciaIncluirRiscos(dto);
        return new ResponseEntity<>(probabilidadeOcorrenciaDTO, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<GetProbabilidadeOcorrenciaDTO> buscarPorId(@PathVariable UUID id) {
        GetProbabilidadeOcorrenciaDTO probabilidadeOcorrenciaDTO = probabilidadeOcorrenciaService.buscarPorId(id);
        return new ResponseEntity<>(probabilidadeOcorrenciaDTO, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<GetProbabilidadeOcorrenciaDTO>> buscarTodasProbabilidadesOcorrencia() {
        List<GetProbabilidadeOcorrenciaDTO> probabilidadesOcorrenciaDTO = probabilidadeOcorrenciaService.buscarTodasProbabilidadesOcorrencia();
        return new ResponseEntity<>(probabilidadesOcorrenciaDTO, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluirProbabilidadeOcorrencia(@PathVariable UUID id) {
        probabilidadeOcorrenciaService.excluirProbabilidadeOcorrencia(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
