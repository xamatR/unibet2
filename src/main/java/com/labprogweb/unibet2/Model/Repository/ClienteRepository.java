package com.labprogweb.unibet2.Model.Repository;

import com.labprogweb.unibet2
.Model.entity.Cliente;
import com.labprogweb.unibet2.Model.entity.Aposta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;
import java.util.Optional;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {
    Optional<Cliente> findByEmail(String email);
    boolean existsByCpf(String cpf);
    
    @Query("SELECT a FROM Aposta a WHERE a.cliente.id = :clienteId")
    List<Aposta> findApostasByClienteId(@Param("clienteId") Long clienteId);
    
    Optional<Cliente> findByCpf(String cpf);
}