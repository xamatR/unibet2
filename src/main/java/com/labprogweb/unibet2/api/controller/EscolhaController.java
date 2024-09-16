package com.labprogweb.unibet2.api.controller;

import com.labprogweb.unibet2.Model.entity.Escolha;
import com.labprogweb.unibet2.service.EscolhaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.ResponseEntity;
import org.modelmapper.ModelMapper;
import com.labprogweb.unibet2.api.dto.EscolhaDTO;

import java.util.Optional;
import java.util.List;

@RestController
@RequestMapping("/api/v2/escolha")
public class EscolhaController {
    
    @Autowired
    private EscolhaService escolhaService;

    @GetMapping
    public ResponseEntity<List<Escolha>> findAll(){
        return ResponseEntity.ok(escolhaService.findAll());
    }

    @PostMapping
    public ResponseEntity<EscolhaDTO> save(@RequestBody EscolhaDTO escolhaDTO){
        Escolha escolha = convertToEntity(escolhaDTO);
        return ResponseEntity.ok(convertToDto(escolhaService.save(escolha)));
    }

    @PutMapping("/{id}")
    public ResponseEntity<EscolhaDTO> update(@PathVariable Long id, @RequestBody EscolhaDTO escolhaDTO){
        if(!escolhaService.findById(id).isPresent()){
            return ResponseEntity.notFound().build();
        }
        Escolha escolha = convertToEntity(escolhaDTO);
        escolha.setId(id);  
        return ResponseEntity.ok(convertToDto(escolhaService.update(id, escolha)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        
        Escolha escolha = escolhaService.findById(id).orElseThrow(() -> new RuntimeException("Escolha not found"));
        escolhaService.delete(escolha);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<EscolhaDTO>> findById(@PathVariable Long id){
        return ResponseEntity.ok(escolhaService.findById(id).map(this::convertToDto));
    }

    private EscolhaDTO convertToDto(Escolha escolha) {
        ModelMapper mapper = new ModelMapper();
        return mapper.map(escolha, EscolhaDTO.class);
    }

    private Escolha convertToEntity(EscolhaDTO escolhaDTO) {
        ModelMapper mapper = new ModelMapper();
        return mapper.map(escolhaDTO, Escolha.class);
    }   

}
