package br.com.jbst.Controllers;


import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import br.com.jbst.DTO.GetDocFreeFuncionarioDTO;
import br.com.jbst.DTO.PostDocFreeFuncionarioDTO;
import br.com.jbst.DTO.PutDocFreeFuncionarioDTO;
import br.com.jbst.DTO.Funcionarios.Documentos.GetDocFreeFuncionarioDTO1;
import br.com.jbst.services.DocFreeFuncionarioService;

@RestController
@RequestMapping("/doc-free-funcionario")
public class DocFreeFuncionarioController {

    @Autowired
    private DocFreeFuncionarioService docFreeFuncionarioService;

    @PostMapping
    public ResponseEntity<GetDocFreeFuncionarioDTO> criarDocumento(@RequestBody PostDocFreeFuncionarioDTO dto) {
        try {
            GetDocFreeFuncionarioDTO createdDocumento = docFreeFuncionarioService.criarDocumentosFree(dto);
            return ResponseEntity.ok(createdDocumento);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    @PutMapping
    public ResponseEntity<GetDocFreeFuncionarioDTO> editarDocumento(@RequestBody PutDocFreeFuncionarioDTO dto) {
        try {
            GetDocFreeFuncionarioDTO updatedDocumento = docFreeFuncionarioService.editarDocumentosFree(dto);
            return ResponseEntity.ok(updatedDocumento);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    @GetMapping
    public ResponseEntity<List<GetDocFreeFuncionarioDTO>> consultarDocumentos() {
        List<GetDocFreeFuncionarioDTO> documentos = docFreeFuncionarioService.consultarDocumentosFree();
        return ResponseEntity.ok(documentos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<GetDocFreeFuncionarioDTO> getDocumentoById(@PathVariable UUID id) {
        try {
            GetDocFreeFuncionarioDTO documento = docFreeFuncionarioService.getDocumentoById(id);
            return ResponseEntity.ok(documento);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/relatorios/{id}")
    public ResponseEntity<GetDocFreeFuncionarioDTO1> getDocumentoByIdGerarRelatorio(@PathVariable UUID id) {
        try {
            GetDocFreeFuncionarioDTO1 documento = docFreeFuncionarioService.getDocumentoByIdGerarRelatorio(id);
            return ResponseEntity.ok(documento);
        } catch (Exception e) {
            return ResponseEntity.status(404).body(null);  // Retorna 404 se não encontrado
        }
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluirDocumento(@PathVariable UUID id) {
        try {
            docFreeFuncionarioService.excluirDocumentoFree(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }
}
