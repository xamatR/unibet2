package com.labprogweb.unibet2.service;

import com.labprogweb.unibet2.Model.entity.Aposta;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import com.labprogweb.unibet2.Model.entity.Cliente;
import com.labprogweb.unibet2.Model.Repository.ClienteRepository;
import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository repository;

    public List<Cliente> findAll(){
        return repository.findAll();
    }

    public Optional<Cliente> findById(Long id){
        return repository.findById(id);
    }

    @Transactional
    public Cliente save(Cliente cliente){
        return repository.save(cliente);
    }

    public void delete(Cliente cliente){
        repository.delete(cliente);
    }

    public void validarCpf(String cpf){
        if(repository.existsByCpf(cpf)){
            throw new RuntimeException("CPF já cadastrado");
        }
    }   

    public void validar(Cliente cliente){
        if(cliente.getNome() == null || cliente.getNome().trim().equals("")){
            throw new RuntimeException("Nome é obrigatório");
        }
    }     

    public Cliente update(Long id, Cliente cliente) {
        Optional<Cliente> optional = repository.findById(id);
        if(optional.isPresent()){
            Cliente clienteUpdate = optional.get();
            clienteUpdate.setNome(cliente.getNome());
            clienteUpdate.setCpf(cliente.getCpf());
            return repository.save(cliente);
        }
        return null;
    }

    public Optional<Cliente> existsByCpf(String cpf){
        return repository.findByCpf(cpf);
    }

    public List<Aposta> findApostasByClienteId(Long id){
        return repository.findApostasByClienteId(id);
    }
}
