package br.com.jbst.Controllers;


import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.jbst.DTO2.GetInspecaoDTO;
import br.com.jbst.DTO2.PostInspecaoDTO;
import br.com.jbst.DTO2.PutInspecaoDTO;
import br.com.jbst.services.InspecaoService;

@RestController
@RequestMapping("/inspecoes")
public class InspecaoController {

    @Autowired
    private InspecaoService inspecaoService;

    @PostMapping
    public ResponseEntity<GetInspecaoDTO> criarInspecao(@RequestBody PostInspecaoDTO dto) {
        try {
            GetInspecaoDTO inspecaoDTO = inspecaoService.criarInspecao(dto);
            return ResponseEntity.status(HttpStatus.CREATED).body(inspecaoDTO);
        } catch (Exception e) {
            // Se ocorrer algum erro ao criar a inspeção, retornar um erro 500 (Internal Server Error)
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
    
    @PutMapping
	public ResponseEntity<GetInspecaoDTO> editarInspecao(@RequestBody PutInspecaoDTO dto) {
		try {
			// Chamar o serviço para editar a inspeção
			GetInspecaoDTO inspecaoEditada = inspecaoService.editarInspecao(dto);
			// Retornar a resposta com a inspeção editada e o status HTTP 200 (OK)
			return ResponseEntity.ok(inspecaoEditada);
		} catch (Exception e) {
			// Em caso de erro, retornar um erro interno do servidor (status HTTP 500)
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
		}
	}
    
    @GetMapping("/{id}")
    public ResponseEntity<GetInspecaoDTO> consultarInspecaoPorId(@PathVariable UUID id) throws Exception {
        GetInspecaoDTO inspecao = inspecaoService.consultarInspecaoPorId(id);
        return ResponseEntity.ok(inspecao);
    }

    @GetMapping
    public ResponseEntity<List<GetInspecaoDTO>> consultarTodasAsInspecoes() {
        List<GetInspecaoDTO> inspecoes = inspecaoService.consultarTodasAsInspecoes();
        return ResponseEntity.ok(inspecoes);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluirInspecaoPorId(@PathVariable UUID id) {
        try {
        	inspecaoService.ExcluirInspecaoPorId(id);
            return ResponseEntity.noContent().build();
        } catch (NotFoundException e) {
            return ResponseEntity.notFound().build();
        }
       }
}
