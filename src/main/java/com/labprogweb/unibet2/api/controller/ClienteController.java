package com.labprogweb.unibet2.api.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import lombok.RequiredArgsConstructor;
import com.labprogweb.unibet2.service.ClienteService;
import com.labprogweb.unibet2.Model.entity.Cliente;
import com.labprogweb.unibet2.Model.entity.Aposta;
import java.util.List;
import java.util.Optional;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v2/cliente")
public class ClienteController {
    private final ClienteService clienteService;

    @GetMapping
    public List<Cliente> findAll(){
        return clienteService.findAll();
    }

    @PostMapping
    public Cliente save(@RequestBody Cliente cliente){
        return clienteService.save(cliente);
    }

    @PutMapping("/{id}")
    public Cliente update(@PathVariable Long id, @RequestBody Cliente cliente){
        return clienteService.update(id, cliente);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id){
        Cliente cliente = clienteService.findById(id)
                                        .orElseThrow(() -> new RuntimeException("Cliente not found")); // Handle Optional
        clienteService.delete(cliente); // Delete the fetched entity
    }

    @GetMapping("/{id}")
    public Optional<Cliente> findById(@PathVariable Long id){
        return clienteService.findById(id);
    }

    @GetMapping("/cpf/{cpf}")
    public boolean existsByCpf(@PathVariable String cpf){
        return clienteService.existsByCpf(cpf).isPresent();
    }

    @GetMapping("/{id}/aposta")
    public List<Aposta> findApostasByClienteId(@PathVariable Long id){
        return clienteService.findApostasByClienteId(id);
    }



    
    
    
}
