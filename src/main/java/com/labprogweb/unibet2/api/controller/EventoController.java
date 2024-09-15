package com.labprogweb.unibet2.api.controller;

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

import lombok.RequiredArgsConstructor;

import com.labprogweb.unibet2.service.EventoService;
import com.labprogweb.unibet2.Model.entity.Evento;



@RestController
@RequestMapping("/api/v2/evento")
@RequiredArgsConstructor
public class EventoController {
    private final EventoService eventoService;

    @GetMapping
    public List<Evento> findAll(){
        return eventoService.findAll();
    }

    @PostMapping
    public Evento save(@RequestBody Evento evento){
        return eventoService.save(evento);
    }

    @PutMapping("/{id}")
    public Evento update(@PathVariable Long id, @RequestBody Evento evento){
        return eventoService.update(id, evento);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id){
        Evento evento = eventoService.findById(id).orElseThrow(() -> new RuntimeException("Evento not found"));
        eventoService.delete(evento);
    }

    @GetMapping("/{id}")
    public Optional<Evento> findById(@PathVariable Long id){
        return eventoService.findById(id);
    }
    
    
}
