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

import java.util.List;




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
    public Aposta save(@RequestBody ApostaDTO apostaDTO){
        Aposta aposta = convertToEntity(apostaDTO);
        return apostaService.save(aposta);
    }

    @PutMapping("/{id}")
    public Aposta update(@PathVariable Long id, @RequestBody ApostaDTO apostaDTO){
        return apostaService.update(id, apostaDTO);
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
        Aposta aposta = new Aposta();
        
        aposta.setValor(apostaDTO.getValor());
        aposta.setData(apostaDTO.getData());
        aposta.setStatus(apostaDTO.getStatus());
        return aposta;
    }
}
