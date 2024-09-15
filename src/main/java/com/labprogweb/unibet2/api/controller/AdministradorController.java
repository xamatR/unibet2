package com.labprogweb.unibet2.api.controller;

import com.labprogweb.unibet2.Model.entity.Administrador;
import com.labprogweb.unibet2.service.AdimistradorService;

import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Optional;




@RestController
@RequestMapping("/api/v2/administrador")
@RequiredArgsConstructor
public class AdministradorController {
    private final AdimistradorService administradorService;

    @GetMapping
    public List<Administrador> findAll(){
        return administradorService.findAll();
    }

    @PostMapping
    public Administrador save(@RequestBody Administrador administrador){
        return administradorService.save(administrador);
    }

    @PutMapping("/{id}")
    public Administrador update(@PathVariable Long id, @RequestBody Administrador administrador){
        return administradorService.update(id, administrador);
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

    
}
