package br.com.jbst.Controllers;


import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.jbst.DTO.GetFuncaoDTO;
import br.com.jbst.dtos.relatorios.GetFuncaoRelatoriosDTO;
import br.com.jbst.services.FuncaoServices;

@RestController
@RequestMapping("/funcoes")
public class FuncaoControllers {

 @Autowired
 private FuncaoServices funcaoServices;


 @GetMapping
	public ResponseEntity<List<GetFuncaoDTO>> get() throws Exception {
		return ResponseEntity
				.status(HttpStatus.OK)
				.body(funcaoServices.getAll());	
		
	}
 
 @GetMapping("/{id}")
 public ResponseEntity<GetFuncaoDTO> getFuncaoById(@PathVariable("id") UUID id) {
     try {
         GetFuncaoDTO funcaoDTO = funcaoServices.getById(id);
         return ResponseEntity.ok(funcaoDTO);
     } catch (Exception e) {
         return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
     }
 }
 

 @GetMapping("/consultar-funcao-relatorios")
 public ResponseEntity<List<GetFuncaoRelatoriosDTO>> consultarFuncaoRelatorios() throws Exception {
     List<GetFuncaoRelatoriosDTO> lista = funcaoServices.funcaoRelatorios();
     return ResponseEntity.status(HttpStatus.OK).body(lista);
 }
 
}
