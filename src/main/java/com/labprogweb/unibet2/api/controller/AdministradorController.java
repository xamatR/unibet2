package com.labprogweb.unibet2.api.controller;

import com.labprogweb.unibet2.Model.entity.Administrador;
import com.labprogweb.unibet2.api.dto.AdministradorDTO;
import com.labprogweb.unibet2.service.AdimistradorService;

import lombok.RequiredArgsConstructor;

import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/api/v2/administrador")
@RequiredArgsConstructor
public class AdministradorController {
    private final AdimistradorService administradorService;

    @GetMapping
    public ResponseEntity findAll(){
        return ResponseEntity.ok(administradorService.findAll().
                stream().map(AdministradorDTO::create)
                .collect(Collectors.toList()));
    }

    @PostMapping
    public ResponseEntity save(@RequestBody AdministradorDTO dto){
        try{
            Administrador administradorNew = coverter(dto);
            return ResponseEntity.ok(AdministradorDTO.create(administradorService.save(administradorNew)));
        } catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }   
    }

    @PutMapping("/{id}")
    public ResponseEntity update(@PathVariable Long id, @RequestBody AdministradorDTO    dto){
        if(!administradorService.findById(id).isPresent()){
            return ResponseEntity.notFound().build();
        }
        Administrador administrador = coverter(dto);
        administrador.setId(id);
        return ResponseEntity.ok(AdministradorDTO.create(administradorService.update(id, administrador)));
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id){
        Administrador administrador = administradorService.findById(id)
                                                        .orElseThrow(() -> new RuntimeException("Administrador not found"));
        administradorService.delete(administrador);
    }

    @GetMapping("/{id}")
    public Optional<Administrador> findById(@PathVariable Long id){
        return administradorService.findById(id);
    }

    private Administrador coverter(AdministradorDTO dto){
        ModelMapper mapper = new ModelMapper();
        return mapper.map(dto, Administrador.class);
    }

    
}
