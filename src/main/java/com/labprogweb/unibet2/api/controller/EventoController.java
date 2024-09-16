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
import java.util.Optional;
import com.labprogweb.unibet2.api.dto.EventoDTO;

import lombok.RequiredArgsConstructor;

import com.labprogweb.unibet2.service.EventoService;
import com.labprogweb.unibet2.Model.entity.Evento;

@RestController
@RequestMapping("/api/v2/evento")
@RequiredArgsConstructor
public class EventoController {
    private final EventoService eventoService;

    @GetMapping
    public ResponseEntity<List<Evento>> findAll(){
        return ResponseEntity.ok(eventoService.findAll());
    }

    @PostMapping
    public ResponseEntity<EventoDTO> save(@RequestBody EventoDTO eventoDTO){
        Evento evento = convertToEntity(eventoDTO);
        return ResponseEntity.ok(convertToDto(eventoService.save(evento)));
    }

    @PutMapping("/{id}")
    public ResponseEntity<EventoDTO> update(@PathVariable Long id, @RequestBody EventoDTO eventoDTO ){
        if(!eventoService.findById(id).isPresent()){
            return ResponseEntity.notFound().build();
        }
        Evento evento = convertToEntity(eventoDTO);
        evento.setId(id);
        return ResponseEntity.ok(convertToDto(eventoService.update(id, evento)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        Evento evento = eventoService.findById(id).orElseThrow(() -> new RuntimeException("Evento not found"));
        eventoService.delete(evento);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<EventoDTO>> findById(@PathVariable Long id){
        return ResponseEntity.ok(eventoService.findById(id).map(this::convertToDto));
    }

    private EventoDTO convertToDto(Evento evento) {
        ModelMapper mapper = new ModelMapper();
        return mapper.map(evento, EventoDTO.class);
    }

    private Evento convertToEntity(EventoDTO eventoDTO) {
        ModelMapper mapper = new ModelMapper();
        return mapper.map(eventoDTO, Evento.class);
    }

}
