package com.labprogweb.unibet2.service;

import org.springframework.stereotype.Service;
import java.util.List;
import com.labprogweb.unibet2.Model.Repository.EscolhaRepository;
import com.labprogweb.unibet2.Model.entity.Escolha;

import java.util.Optional;
import javax.transaction.Transactional;




@Service
public class EscolhaService {
    private EscolhaRepository repository;

    public List<Escolha> findAll(){
        return repository.findAll();
    }

    public Optional<Escolha> findById(Long id){
        return repository.findById(id);
    }

    @Transactional
    public Escolha save(Escolha escolha){
        return repository.save(escolha);
    }   

    public void delete(Escolha escolha){
        repository.delete(escolha);
    }

    @Transactional
    public Escolha update(Long id, Escolha escolha){
        Escolha escolhaToUpdate = repository.findById(id)
                                            .orElseThrow(() -> new RuntimeException("Escolha not found"));
        escolhaToUpdate.setStatus(escolha.getStatus());
        escolhaToUpdate.setDataFim(escolha.getDataFim());
        escolhaToUpdate.setDataInicio(escolha.getDataInicio());
        
        return repository.save(escolhaToUpdate);
    }

    
    
}
