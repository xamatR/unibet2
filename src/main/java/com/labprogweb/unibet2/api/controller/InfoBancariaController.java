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
import java.util.List;
import com.labprogweb.unibet2.api.dto.InfoBancariaDTO;


import lombok.RequiredArgsConstructor;

import com.labprogweb.unibet2.service.InfoBancariaService;
import com.labprogweb.unibet2.Model.entity.InfoBancaria;

@RestController
@RequestMapping("/api/v2/info-bancaria")
@RequiredArgsConstructor
public class InfoBancariaController {
    private final InfoBancariaService infoBancariaService;

    @GetMapping
    public ResponseEntity<List<InfoBancaria>> findAll(){
        return ResponseEntity.ok(infoBancariaService.findAll());
    }

    @PostMapping
    public ResponseEntity<InfoBancariaDTO> save(@RequestBody InfoBancariaDTO infoBancariaDTO){
        InfoBancaria infoBancaria = convertToEntity(infoBancariaDTO);
        return ResponseEntity.ok(convertToDto(infoBancariaService.save(infoBancaria)));
    }

    @PutMapping("/{id}")
    public ResponseEntity<InfoBancariaDTO> update(@PathVariable Long id, @RequestBody InfoBancariaDTO infoBancariaDTO){
        if(!infoBancariaService.findById(id).isPresent()){
            return ResponseEntity.notFound().build();
        }
        InfoBancaria infoBancaria = convertToEntity(infoBancariaDTO);
        infoBancaria.setId(id);
        return ResponseEntity.ok(convertToDto(infoBancariaService.update(id, infoBancaria)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        InfoBancaria infoBancaria = infoBancariaService.findById(id).orElseThrow(() -> new RuntimeException("InfoBancaria not found"));
        infoBancariaService.delete(infoBancaria);
        return ResponseEntity.noContent().build();
    }

    private InfoBancariaDTO convertToDto(InfoBancaria infoBancaria){
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(infoBancaria, InfoBancariaDTO.class);
    }

    private InfoBancaria convertToEntity(InfoBancariaDTO infoBancariaDTO){
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(infoBancariaDTO, InfoBancaria.class);
    }

}
