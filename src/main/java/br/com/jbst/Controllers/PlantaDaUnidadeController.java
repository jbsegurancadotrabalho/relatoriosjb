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

import br.com.jbst.DTO.GetPlantaDaUnidadeDTO;
import br.com.jbst.DTO.PostPlantaDaUnidadeDTO;
import br.com.jbst.DTO.PutPlantaDaUnidadeDTO;
import br.com.jbst.services.PlantaDaUnidadeService;

@RestController
@RequestMapping("/planta-da-unidade")
public class PlantaDaUnidadeController {

    @Autowired
    private PlantaDaUnidadeService plantaDaUnidadeService;

    @PostMapping
    public ResponseEntity<GetPlantaDaUnidadeDTO> criarPlantaDaUnidade(@RequestBody PostPlantaDaUnidadeDTO dto) {
        try {
            GetPlantaDaUnidadeDTO plantaCriada = plantaDaUnidadeService.criarPlantaDaUnidade(dto);
            return ResponseEntity.status(HttpStatus.CREATED).body(plantaCriada);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @PutMapping
    public ResponseEntity<GetPlantaDaUnidadeDTO> editarPlantaDaUnidade(@RequestBody PutPlantaDaUnidadeDTO dto) {
        try {
            GetPlantaDaUnidadeDTO plantaEditada = plantaDaUnidadeService.editarPlantaDaUnidade(dto);
            return ResponseEntity.ok(plantaEditada);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }


    @GetMapping
    public ResponseEntity<List<GetPlantaDaUnidadeDTO>> consultarTodasAsPlantasDaUnidade() {
        List<GetPlantaDaUnidadeDTO> plantas = plantaDaUnidadeService.consultarTodasAsPlantasDaUnidade();
        return ResponseEntity.ok(plantas);
    }

    @GetMapping("/{id}")
    public ResponseEntity<GetPlantaDaUnidadeDTO> consultarPlantaDaUnidadePorId(@PathVariable("id") UUID id) {
        try {
            GetPlantaDaUnidadeDTO planta = plantaDaUnidadeService.consultarPlantaDaUnidadePorId(id);
            return ResponseEntity.ok(planta);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluirPlantaDaUnidade(@PathVariable("id") UUID id) {
        try {
            plantaDaUnidadeService.excluirPlantaDaUnidade(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }
}
