package com.labprogweb.unibet2.service;

import com.labprogweb.unibet2.Model.Repository.AdministradorRepository;
import com.labprogweb.unibet2.Model.entity.Administrador;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AdimistradorService {
    private final AdministradorRepository administradorRepository;

    public AdimistradorService(AdministradorRepository administradorRepository) {
        this.administradorRepository = administradorRepository;
    }

    public List<Administrador> findAll(){
        return administradorRepository.findAll();
    }

    public Administrador save(Administrador administrador){
        return administradorRepository.save(administrador);
    }

    public Administrador update(Long id, Administrador administrador){
        return administradorRepository.save(administrador);
    }

    public void delete(Administrador administrador){
        administradorRepository.delete(administrador);
    }

    public Optional<Administrador> findById(Long id){
        return administradorRepository.findById(id);
    }
    
    
}
