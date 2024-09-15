package com.labprogweb.unibet2.Model.Repository;

import com.labprogweb.unibet2.Model.entity.Evento;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface EventoRepository extends JpaRepository<Evento ,Long> {
    List<Evento> findByNome(String nome);
}
