package br.com.jbst.Controllers;



import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import br.com.jbst.DTO.GetDocumentosFree;
import br.com.jbst.DTO.PostDocumentosFree;
import br.com.jbst.DTO.PutDocumentosFree;
import br.com.jbst.services.DocumentosFreeService;

@RestController
@RequestMapping("/api/documentos-free")
public class DocumentosFreeController {

    @Autowired
    private DocumentosFreeService documentosFreeService;

    @PostMapping
    public ResponseEntity<GetDocumentosFree> criarDocumentosFree(@RequestBody PostDocumentosFree dto) {
        try {
            GetDocumentosFree documentosFree = documentosFreeService.criarDocumentosFree(dto);
            return ResponseEntity.ok(documentosFree);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(null);
        }
    }
    

    @PutMapping
    public ResponseEntity<GetDocumentosFree> editarDocumentosFree(@RequestBody PutDocumentosFree dto) {
        try {
            GetDocumentosFree documentosFree = documentosFreeService.editarDocumentosFree(dto);
            return ResponseEntity.ok(documentosFree);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    @GetMapping
    public ResponseEntity<List<GetDocumentosFree>> consultarDocumentosFree() {
        List<GetDocumentosFree> documentosFreeList = documentosFreeService.consultarDocumentosFree();
        return ResponseEntity.ok(documentosFreeList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<GetDocumentosFree> getDocumentoById(@PathVariable UUID id) {
        try {
            GetDocumentosFree documentosFree = documentosFreeService.getDocumentoById(id);
            return ResponseEntity.ok(documentosFree);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluirDocumentoFree(@PathVariable UUID id) {
        try {
            documentosFreeService.excluirDocumentoFree(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }
    
    
}