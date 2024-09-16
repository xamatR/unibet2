package com.labprogweb.unibet2.api.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.http.ResponseEntity;
import org.modelmapper.ModelMapper;
import com.labprogweb.unibet2.service.OddsService;
import com.labprogweb.unibet2.Model.entity.Odds;
import com.labprogweb.unibet2.api.dto.OddsDTO;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;

@RestController
@RequestMapping("/api/v2/odds")
public class OddController {
    
    @Autowired
    private OddsService oddsService;

    @GetMapping
    public ResponseEntity<List<OddsDTO>> findAll(){
        return ResponseEntity.ok(oddsService.findAll().stream().map(this::convertToDto).collect(Collectors.toList()));
    }

    @PostMapping
    public ResponseEntity<OddsDTO> save(@RequestBody OddsDTO oddsDTO){
        Odds odds = convertToEntity(oddsDTO);
        return ResponseEntity.ok(convertToDto(oddsService.save(odds)));
    }

    @PutMapping("/{id}")
    public ResponseEntity<OddsDTO> update(@PathVariable Long id, @RequestBody OddsDTO oddsDTO){
        if(!oddsService.findById(id).isPresent()){
            return ResponseEntity.notFound().build();
        }
        Odds odds = convertToEntity(oddsDTO);
        odds.setId(id);
        return ResponseEntity.ok(convertToDto(oddsService.update(id, odds)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        Odds odds = oddsService.findById(id).orElseThrow(() -> new RuntimeException("Odd not found"));
        oddsService.delete(odds);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<OddsDTO>> findById(@PathVariable Long id){
        return ResponseEntity.ok(oddsService.findById(id).map(this::convertToDto));
    }

    private OddsDTO convertToDto(Odds odds) {
        ModelMapper mapper = new ModelMapper();
        return mapper.map(odds, OddsDTO.class);
    }

    private Odds convertToEntity(OddsDTO oddsDTO) {
        ModelMapper mapper = new ModelMapper();
        return mapper.map(oddsDTO, Odds.class);
    }
}
