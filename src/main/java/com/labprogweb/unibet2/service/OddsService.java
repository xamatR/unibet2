package com.labprogweb.unibet2.service;

import com.labprogweb.unibet2.Model.Repository.OddsRepository;
import com.labprogweb.unibet2.Model.entity.Odds;
import com.labprogweb.unibet2.Model.entity.Escolha;
import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
public class OddsService {
    private OddsRepository repository;

    public List<Odds> findAll(){
        return repository.findAll();
    }

    public Optional<Odds> findById(Long id){
        return repository.findById(id);
    }

    @Transactional
    public Odds save(Odds odds){
        return repository.save(odds);
    }

    public void delete(Odds odds){
        repository.delete(odds);
    }

    public List<Odds> findByEscolha(Optional<Escolha> escolha){
        return repository.findByEscolha(escolha);
    }

    @Transactional
    public Odds update(Long id, Odds odds){
        Odds oddsUpdated = repository.findById(id).orElseThrow(() -> new RuntimeException("Odd not found"));
        oddsUpdated.setMultiplicador(odds.getMultiplicador());
        oddsUpdated.setResultado(odds.getResultado());
        
        return repository.save(oddsUpdated);
    }
    
}
