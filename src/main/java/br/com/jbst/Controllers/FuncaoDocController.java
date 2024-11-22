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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.jbst.DTO.GetFuncaoDocDTO;
import br.com.jbst.DTO.PostFuncaoDocDTO;
import br.com.jbst.DTO.PostFuncaoDocIncluirCenarioDTO;
import br.com.jbst.DTO.PutFuncaoDocDTO;
import br.com.jbst.DTO.Funcionarios.Documentos.GetFuncaoDoc1DTO;
import br.com.jbst.services.FuncaoDocServices;

@RestController
@RequestMapping("/funcoesdoc")
public class FuncaoDocController {

    @Autowired private FuncaoDocServices funcaoDocServices;
    
    @PostMapping
    public ResponseEntity<GetFuncaoDocDTO> criarFuncaoDoc(@RequestBody PostFuncaoDocDTO dto) {
        GetFuncaoDocDTO createdFuncaoDoc = funcaoDocServices.criarFuncaoDoc(dto);
        return new ResponseEntity<>(createdFuncaoDoc, HttpStatus.CREATED);
    }
    


    @PutMapping
    public ResponseEntity<GetFuncaoDocDTO> editarFuncaoDoc(@RequestBody PutFuncaoDocDTO dto) {
        try {
            GetFuncaoDocDTO funcaoDocDTO = funcaoDocServices.editarFuncaoDoc(dto);
            return ResponseEntity.ok(funcaoDocDTO);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    
    @GetMapping
    public ResponseEntity<List<GetFuncaoDocDTO>> getAllFuncoesDoc() {
        try {
            List<GetFuncaoDocDTO> funcoesDocDTO =funcaoDocServices.getAllFuncoesDoc();
            return ResponseEntity.ok(funcoesDocDTO);
        } catch (Exception e) {
            e.printStackTrace(); // Aqui você pode lidar com o erro de alguma forma, como retornar uma mensagem de erro adequada
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<GetFuncaoDocDTO> buscarFuncaoDocPorId(@PathVariable UUID id) {
        GetFuncaoDocDTO funcaoDoc = funcaoDocServices.buscarFuncaoDocPorId(id);
        if (funcaoDoc != null) {
            return new ResponseEntity<>(funcaoDoc, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<GetFuncaoDocDTO> excluirFuncaoDoc(@PathVariable UUID id) throws Exception {
        GetFuncaoDocDTO deletedFuncaoDoc = funcaoDocServices.excluirFuncaoDoc(id);
        return new ResponseEntity<>(deletedFuncaoDoc, HttpStatus.OK);
    }
    

    @GetMapping("/relatorio")
    public List<GetFuncaoDoc1DTO> consultarFuncaodocGerarRelatorio() throws Exception {
        return funcaoDocServices.consultarFuncaodocGerarRelatorio();
    }
 
    
    @GetMapping("/buscar")
    public ResponseEntity<GetFuncaoDoc1DTO> getByFuncao(@RequestParam String funcaoNome) {
        try {
            GetFuncaoDoc1DTO funcaoDto = funcaoDocServices.getByFuncao(funcaoNome);
            return ResponseEntity.ok(funcaoDto);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(null); // Retorna 400 se não encontrar a função
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body(null); // Retorna 500 em caso de erro inesperado
        }
    }
    
}

