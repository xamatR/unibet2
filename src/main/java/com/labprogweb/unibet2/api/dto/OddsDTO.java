package com.labprogweb.unibet2.api.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import com.labprogweb.unibet2.Model.entity.Odds;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class OddsDTO {
    private Long id;
    private Float multiplicador;
    private String tipo;
    private String nome;
    private String resultado;
    private Long idEscolha;

    public static OddsDTO create(Odds odds){
        ModelMapper mapper = new ModelMapper();
        return mapper.map(odds, OddsDTO.class);
    }
}