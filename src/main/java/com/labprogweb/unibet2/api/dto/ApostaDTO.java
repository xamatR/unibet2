package com.labprogweb.unibet2.api.dto;

import org.modelmapper.ModelMapper;
import java.time.LocalDateTime;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.labprogweb.unibet2.Model.entity.Aposta;


@Data
@NoArgsConstructor
public class ApostaDTO {
    private long id;
    private LocalDateTime data;
    private float valor;
    private String status;
    private long idCliente;
    private long idEvento;
    
    public static ApostaDTO create(Aposta aposta){
        ModelMapper mapper = new ModelMapper();
        return mapper.map(aposta, ApostaDTO.class);
    }
}
