package com.labprogweb.unibet2.service;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import com.labprogweb.unibet2.Model.entity.Aposta;
import com.labprogweb.unibet2.Model.Repository.ApostaRepository;
import com.labprogweb.unibet2.Model.Repository.ClienteRepository; // Add this import
import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import com.labprogweb.unibet2.api.dto.ApostaDTO;

@Service
public class ApostaService {

    @Autowired
    private ApostaRepository repository;

    @Autowired
    private ClienteRepository clienteRepository;

    public List<Aposta> findAll(){
        return repository.findAll();
    }

    public Optional<Aposta> findById(Long id){
        return repository.findById(id);
    }

    @Transactional
    public Aposta save(Aposta aposta){
        return repository.save(aposta);
    }   

    @Transactional
    public void delete(Aposta aposta){
        repository.delete(aposta);
    }

    public List<Aposta> findByClienteId(Long clienteId){
        return repository.findByClienteId(clienteId);
    }

    public Aposta update(Long id, ApostaDTO apostaDTO) {
        Optional<Aposta> optional = repository.findById(id);
        if(optional.isPresent()){
            Aposta aposta = optional.get();
            aposta.setValor(apostaDTO.getValor());
            aposta.setCliente(clienteRepository.findById(apostaDTO.getIdCliente())
            .orElseThrow(() -> new RuntimeException("Cliente não encontrado")));
            aposta.setData(apostaDTO.getData());
            aposta.setStatus(apostaDTO.getStatus());
            return repository.save(aposta);
        }
        return null;
    }
}
