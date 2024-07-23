package br.com.jbst.Controllers;


import br.com.jbst.DTO2.GetProtecoesDTO;
import br.com.jbst.DTO2.PostProtecoesDTO;
import br.com.jbst.DTO2.PutProtecoesDTO;
import br.com.jbst.services.ProtecoesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/protecoes")
public class ProtecoesController {

    private final ProtecoesService protecoesService;

    @Autowired
    public ProtecoesController(ProtecoesService protecoesService) {
        this.protecoesService = protecoesService;
    }

    @PostMapping
    public ResponseEntity<GetProtecoesDTO> criarProtecoes( @RequestBody PostProtecoesDTO dto) {
        GetProtecoesDTO protecoesDTO = protecoesService.criarProtecoes(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(protecoesDTO);
    }

    @PutMapping
    public ResponseEntity<GetProtecoesDTO> editarProtecoes( @RequestBody PutProtecoesDTO dto) {
        GetProtecoesDTO protecoesDTO = protecoesService.editarProtecoes(dto);
        return ResponseEntity.ok(protecoesDTO);
    }

    @GetMapping("/{id}")
    public ResponseEntity<GetProtecoesDTO> consultarProtecoesPorId(@PathVariable UUID id) {
        GetProtecoesDTO protecoesDTO = protecoesService.consultarProtecoesPorId(id);
        return ResponseEntity.ok(protecoesDTO);
    }

    @GetMapping
    public ResponseEntity<List<GetProtecoesDTO>> consultarTodasAsProtecoes() {
        List<GetProtecoesDTO> protecoesDTOList = protecoesService.consultarTodasAsProtecoes();
        return ResponseEntity.ok(protecoesDTOList);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluirProtecoesPorId(@PathVariable UUID id) {
        protecoesService.excluirProtecoesPorId(id);
        return ResponseEntity.noContent().build();
    }
}
