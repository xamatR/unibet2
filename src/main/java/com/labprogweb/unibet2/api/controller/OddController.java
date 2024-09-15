package com.labprogweb.unibet2.api.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import com.labprogweb.unibet2.service.OddsService;
import com.labprogweb.unibet2.Model.entity.Odds;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

public class OddController {
    
    @Autowired
    private OddsService oddsService;

    @GetMapping
    public List<Odds> findAll(){
        return oddsService.findAll();
    }

    @PostMapping
        public Odds save(@RequestBody Odds odds){
        return oddsService.save(odds);
    }

    @PutMapping("/{id}")
    public Odds update(@PathVariable Long id, @RequestBody Odds odds){
        return oddsService.update(id, odds);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id){
        Odds odds = oddsService.findById(id).orElseThrow(() -> new RuntimeException("Odd not found"));
        oddsService.delete(odds);
    }

    @GetMapping("/{id}")
    public Optional<Odds> findById(@PathVariable Long id){
        return oddsService.findById(id);
    }
    
    
}
