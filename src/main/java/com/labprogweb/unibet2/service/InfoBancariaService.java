package com.labprogweb.unibet2.service;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import com.labprogweb.unibet2.Model.Repository.InfoBancariaRepository;
import com.labprogweb.unibet2.Model.entity.InfoBancaria;
import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;

@Service
public class InfoBancariaService {
    @Autowired
    private InfoBancariaRepository repository;

    public List<InfoBancaria> findAll(){
        return repository.findAll();
    }

    public Optional<InfoBancaria> findById(Long id){
        return repository.findById(id);
    }

    @Transactional
    public InfoBancaria save(InfoBancaria infoBancaria){
        return repository.save(infoBancaria);
    }

    public void delete(InfoBancaria infoBancaria){
        repository.delete(infoBancaria);
    }

    public List<InfoBancaria> findByClienteId(Long clienteId){
        return repository.findByClienteId(clienteId);
    }

    public InfoBancaria update(Long id, InfoBancaria infoBancaria){
        Optional<InfoBancaria> optional = repository.findById(id);
        if(optional.isPresent()){
            infoBancaria.setId(id);
            return repository.save(infoBancaria);
        }
        return null;
    }
    
    
}
