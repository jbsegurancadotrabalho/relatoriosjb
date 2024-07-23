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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.jbst.DTO.GetEnderecoDTO;
import br.com.jbst.DTO.PostEnderecoDTO;
import br.com.jbst.DTO.PutEnderecoDTO;
import br.com.jbst.services.EnderecoService;
import io.swagger.v3.oas.annotations.parameters.RequestBody;

@RestController
@RequestMapping("/enderecos")
public class EnderecoController {

    @Autowired
    private EnderecoService enderecoService;
    

    @GetMapping
    public ResponseEntity<List<GetEnderecoDTO>> buscarTodosEnderecos() {
        List<GetEnderecoDTO> enderecosDTO = enderecoService.buscarTodosEnderecos();
        return ResponseEntity.ok(enderecosDTO);
    }

    @GetMapping("/{id}")
    public ResponseEntity<GetEnderecoDTO> buscarEnderecoPorId(@PathVariable("id") UUID idEndereco) {
        try {
            GetEnderecoDTO enderecoDTO = enderecoService.buscarEnderecoPorId(idEndereco);
            return ResponseEntity.ok(enderecoDTO);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
}

