package com.labprogweb.unibet2.api.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import com.labprogweb.unibet2.Model.entity.Evento;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EventoDTO {
    private Long id;
    private String status;
    private String titulo;
    private String descricao;
    private Long idAdm;

    public static EventoDTO create(Evento evento){
        ModelMapper mapper = new ModelMapper();
        return mapper.map(evento, EventoDTO.class);
    }
}
