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
import java.util.Optional;
import java.util.List;

@RestController
@RequestMapping("/api/v2/escolha")
public class EscolhaController {
    
    @Autowired
    private EscolhaService escolhaService;

    @GetMapping
    public List<Escolha> findAll(){
        return escolhaService.findAll();
    }

    @PostMapping
    public Escolha save(@RequestBody Escolha escolha){
        return escolhaService.save(escolha);
    }

    @PutMapping("/{id}")
    public Escolha update(@PathVariable Long id, @RequestBody Escolha escolha){
        return escolhaService.update(id, escolha);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id){
        Escolha escolha = escolhaService.findById(id).orElseThrow(() -> new RuntimeException("Escolha not found"));
        escolhaService.delete(escolha);
    }

    @GetMapping("/{id}")
    public Optional<Escolha> findById(@PathVariable Long id){
        return escolhaService.findById(id);
    }

    


}
