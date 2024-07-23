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

import br.com.jbst.DTO.GetPerigoDTO;
import br.com.jbst.DTO.GetPerigoFuncaoDTO;
import br.com.jbst.DTO.PostPerigoDTO;
import br.com.jbst.DTO.PostPerigoIncluirFuncaoEspecificaCenarioDTO;
import br.com.jbst.DTO.PutPerigoDTO;
import br.com.jbst.services.PerigoServices;

@RestController
@RequestMapping("/perigos")
public class PerigoController {

  @Autowired PerigoServices perigoServices;

   
    
    @PostMapping("/incluir-funcao-especifica")
    public ResponseEntity<GetPerigoDTO> criarPerigoFuncaoEspecifica(@RequestBody PostPerigoIncluirFuncaoEspecificaCenarioDTO dto) {
        GetPerigoDTO perigoDTO = perigoServices.criarPerigoFuncaoEspecifica(dto);
        return ResponseEntity.ok(perigoDTO);
    }

    @PostMapping
    public ResponseEntity<GetPerigoDTO> criarPerigo(@RequestBody PostPerigoDTO dto) {
        GetPerigoDTO perigoCriado = perigoServices.criarPerigo(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(perigoCriado);
    }

    @PutMapping
    public ResponseEntity<GetPerigoDTO> editarPerigo(@RequestBody PutPerigoDTO dto) {
        GetPerigoDTO perigoEditado = perigoServices.editarPerigo(dto);
        if (perigoEditado != null) {
            return ResponseEntity.ok(perigoEditado);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/{idPerigo}")
    public ResponseEntity<GetPerigoDTO> buscarPorId(@PathVariable UUID idPerigo) {
        GetPerigoDTO perigoEncontrado = perigoServices.buscarPorId(idPerigo);
        if (perigoEncontrado != null) {
            return ResponseEntity.ok(perigoEncontrado);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/consultar-perigo-funcao")
    public ResponseEntity<List<GetPerigoFuncaoDTO>> buscarPerigosFuncao() {
        try {
            List<GetPerigoFuncaoDTO> perigos = perigoServices.buscarPerigosFuncao();
            return ResponseEntity.ok(perigos);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
    
    @GetMapping
    public ResponseEntity<List<GetPerigoDTO>> buscarTodos() {
        List<GetPerigoDTO> perigos = perigoServices.buscarTodos();
        return ResponseEntity.ok(perigos);
    }

    @DeleteMapping("/{idPerigo}")
    public ResponseEntity<Void> excluirPorId(@PathVariable UUID idPerigo) {
        boolean excluido = perigoServices.excluirPorId(idPerigo);
        if (excluido) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    
    
}
