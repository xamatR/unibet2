package com.labprogweb.unibet2.Model.Repository;

import com.labprogweb.unibet2.Model.entity.Aposta;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ApostaRepository extends JpaRepository<Aposta, Long> {

    List<Aposta> findByClienteId(Long clienteId);
}