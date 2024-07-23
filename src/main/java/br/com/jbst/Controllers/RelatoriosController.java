package br.com.jbst.Controllers;


import java.util.List;
import java.util.UUID;

import javax.management.relation.RelationException;
import javax.management.relation.RelationNotFoundException;

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

import br.com.jbst.DTO2.GetRelatoriosDTO;
import br.com.jbst.DTO2.PostRelatoriosDTO;
import br.com.jbst.DTO2.PutRelatoriosDTO;
import br.com.jbst.services.RelatoriosService;

@RestController
@RequestMapping("/relatorios")
public class RelatoriosController {

    @Autowired
    private RelatoriosService relatoriosService;

    @PostMapping
    public ResponseEntity<GetRelatoriosDTO> criarRelatorio(@RequestBody PostRelatoriosDTO dto) {
        GetRelatoriosDTO novoRelatorio = relatoriosService.criarRelatorio(dto);
        return new ResponseEntity<>(novoRelatorio, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<GetRelatoriosDTO> buscarRelatorioPorId(@PathVariable UUID id) {
        GetRelatoriosDTO relatorio = relatoriosService.buscarRelatorioPorId(id);
        return ResponseEntity.ok(relatorio);
    }

    @GetMapping
    public ResponseEntity<List<GetRelatoriosDTO>> buscarTodosRelatorios() {
        List<GetRelatoriosDTO> relatorios = relatoriosService.buscarTodosRelatorios();
        return ResponseEntity.ok(relatorios);
    }

    @PutMapping
    public ResponseEntity<GetRelatoriosDTO> editarRelatorios(@RequestBody PutRelatoriosDTO dto) {
        try {
            // Verificar se o ID da agenda está presente no DTO
            if (dto.getIdRelatorios() == null) {
                throw new IllegalArgumentException("O ID do relatório deve ser fornecido para editar.");
            }

            // Chamar o serviço para editar o relatório
            GetRelatoriosDTO relatorioAtualizado = relatoriosService.editarRelatorios(dto);
            
            // Retornar o DTO do relatório atualizado
            return ResponseEntity.ok(relatorioAtualizado);
        } catch (IllegalArgumentException e) {
            // Se o ID do relatório estiver faltando
            return ResponseEntity.badRequest().build();
        } catch (Exception e) {
            // Se ocorrer qualquer outro erro durante a edição
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluirRelatorio(@PathVariable UUID id) {
        relatoriosService.excluirRelatorioPorId(id);
        return ResponseEntity.noContent().build();
    }
}
