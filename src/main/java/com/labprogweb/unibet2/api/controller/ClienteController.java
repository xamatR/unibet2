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


import lombok.RequiredArgsConstructor;
import com.labprogweb.unibet2.service.ClienteService;
import com.labprogweb.unibet2.Model.entity.Cliente;
import com.labprogweb.unibet2.Model.entity.Aposta;
import com.labprogweb.unibet2.api.dto.ClienteDTO;
import org.modelmapper.ModelMapper;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v2/cliente")
public class ClienteController {
    private final ClienteService clienteService;

    @GetMapping
    public ResponseEntity<List<Cliente>> findAll(){
        return ResponseEntity.ok(clienteService.findAll());
    }

    @PostMapping
    public ResponseEntity<Cliente> save(@RequestBody Cliente cliente){
        return ResponseEntity.ok(clienteService.save(cliente));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ClienteDTO> update(@PathVariable Long id, @RequestBody ClienteDTO clienteDTO){
        if(!clienteService.findById(id).isPresent()){
            return ResponseEntity.notFound().build();
        }
        Cliente clienteUpdated = convertToEntity(clienteDTO);
        clienteUpdated.setId(id);
        return ResponseEntity.ok(convertToDto(clienteService.update(id, clienteUpdated)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        Cliente cliente = clienteService.findById(id)
                                        .orElseThrow(() -> new RuntimeException("Cliente not found"));
        clienteService.delete(cliente);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<ClienteDTO>> findById(@PathVariable Long id){
        return ResponseEntity.ok(clienteService.findById(id).map(this::convertToDto));
    }

    @GetMapping("/cpf/{cpf}")
    public ResponseEntity<Boolean> existsByCpf(@PathVariable String cpf){
        return ResponseEntity.ok(clienteService.existsByCpf(cpf).isPresent());
    }

    @GetMapping("/{id}/aposta")
    public ResponseEntity<List<Aposta>> findApostasByClienteId(@PathVariable Long id){
        return ResponseEntity.ok(clienteService.findApostasByClienteId(id));
    }

    private ClienteDTO convertToDto(Cliente cliente) {
        ModelMapper mapper = new ModelMapper();
        return mapper.map(cliente, ClienteDTO.class);
    }

    private Cliente convertToEntity(ClienteDTO clienteDTO) {
        ModelMapper mapper = new ModelMapper();
        return mapper.map(clienteDTO, Cliente.class);
    }
}
