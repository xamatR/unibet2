package com.labprogweb.unibet2.Model.Repository;

import com.labprogweb.unibet2.Model.entity.InfoBancaria;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface InfoBancariaRepository extends JpaRepository<InfoBancaria, Long> {


    List<InfoBancaria> findByClienteId(Long clienteId);

}
