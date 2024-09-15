package com.labprogweb.unibet2.service;

import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import com.labprogweb.unibet2.Model.Repository.EventoRepository;
import com.labprogweb.unibet2.Model.entity.Evento;


@Service
public class EventoService {
    private EventoRepository repository;

    public List<Evento> findAll(){
        return repository.findAll();
    }

    public Optional<Evento> findById(Long id){
        return repository.findById(id);
    }

    @Transactional
    public Evento save(Evento evento){
        return repository.save(evento);
    }

    public void delete(Evento evento){
        repository.delete(evento);
    }

    public List<Evento> findByNome(String nome){
        return repository.findByNome(nome);
    }

    @Transactional
    public Evento update(Long id, Evento evento){
        Evento eventoToUpdate = repository.findById(id).orElseThrow(() -> new RuntimeException("Evento not found"));
        eventoToUpdate.setNome(evento.getNome());
        eventoToUpdate.setDescricao(evento.getDescricao());
        return repository.save(eventoToUpdate);
    }
    
    
}
