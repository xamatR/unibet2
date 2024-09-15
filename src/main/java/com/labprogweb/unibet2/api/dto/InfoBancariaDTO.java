package com.labprogweb.unibet2.api.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import com.labprogweb.unibet2.Model.entity.InfoBancaria;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class InfoBancariaDTO {
    private Long id;
    private String agencia;
    private String conta;
    private String banco;
    private String tipoConta;
    private Long idCliente;

    public static InfoBancariaDTO create(InfoBancaria infoBancaria){
        ModelMapper mapper = new ModelMapper();
        return mapper.map(infoBancaria, InfoBancariaDTO.class);
    }
}
