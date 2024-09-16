package com.labprogweb.unibet2.api.controller;

import com.labprogweb.unibet2.api.dto.ApostaDTO;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import lombok.RequiredArgsConstructor;
import com.labprogweb.unibet2.service.ApostaService;
import com.labprogweb.unibet2.Model.entity.Aposta;
import org.springframework.http.ResponseEntity;
import java.util.List;
import org.modelmapper.ModelMapper;




@RestController
@RequestMapping("/api/v2/aposta")
@RequiredArgsConstructor
public class ApostaContoller {
    private final ApostaService apostaService;

    
    @GetMapping
    public List<Aposta> findAll(){
        return apostaService.findAll();
    }

    @PostMapping
    public ResponseEntity save(@RequestBody ApostaDTO apostaDTO){
        Aposta aposta = convertToEntity(apostaDTO);
        return ResponseEntity.ok(apostaService.save(aposta));
    }

    @PutMapping("/{id}")
    public ResponseEntity update(@PathVariable Long id, @RequestBody ApostaDTO apostaDTO){
        if(!apostaService.findById(id).isPresent()){
            return ResponseEntity.notFound().build();
        }
        Aposta aposta = convertToEntity(apostaDTO);
        aposta.setId(id);
        ApostaDTO updatedApostaDTO = convertToDto(aposta);
        return ResponseEntity.ok(apostaService.update(id, updatedApostaDTO)); 
    }

    // Add this new method to convert Aposta to ApostaDTO
    private ApostaDTO convertToDto(Aposta aposta) {
        ModelMapper mapper = new ModelMapper();
        return mapper.map(aposta, ApostaDTO.class);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id){
        Aposta aposta = apostaService.findById(id)
                                     .orElseThrow(() -> new RuntimeException("Aposta not found")); // Handle Optional
        apostaService.delete(aposta); // Delete the fetched entity
    }

    @GetMapping("/teste")
    public String teste(){
        return "teste";
    }
    
    private Aposta convertToEntity(ApostaDTO apostaDTO) {
        ModelMapper mapper = new ModelMapper();
        return mapper.map(apostaDTO, Aposta.class);
    }
}
