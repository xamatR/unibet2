package com.labprogweb.unibet2.Model.Repository;
import com.labprogweb.unibet2.Model.entity.Escolha;
import java.util.List;
import java.util.Optional;
import com.labprogweb.unibet2.Model.entity.Odds;
import org.springframework.data.jpa.repository.JpaRepository;



public interface OddsRepository extends JpaRepository<Odds, Long> {
    List<Odds> findByEscolha(Optional<Escolha> escolha);
}
