package com.labprogweb.unibet2.api.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import com.labprogweb.unibet2.Model.entity.Escolha;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EscolhaDTO {
    private Long id;
    private String status;
    private LocalDate dataInicio;
    private LocalDate dataFim;
    private Long idEvento;
    private Long idAposta;

    public static EscolhaDTO create(Escolha escolha){
        ModelMapper mapper = new ModelMapper();
        return mapper.map(escolha, EscolhaDTO.class);
    }
}
