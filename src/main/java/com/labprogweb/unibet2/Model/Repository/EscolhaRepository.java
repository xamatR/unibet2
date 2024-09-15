package com.labprogweb.unibet2.Model.Repository;

import com.labprogweb.unibet2.Model.entity.Aposta;
import com.labprogweb.unibet2.Model.entity.Escolha;
import com.labprogweb.unibet2.Model.entity.Evento;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public interface EscolhaRepository extends JpaRepository<Escolha,Long> {
    List<Escolha> findAllByEvento(Optional<Evento> evento);

    Optional<Escolha> findEscolhaByApostaId(Long idAposta);
}
