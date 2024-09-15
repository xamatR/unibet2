package com.labprogweb.unibet2.api.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;
import com.labprogweb.unibet2.Model.entity.Administrador;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AdministradorDTO {
    private long id;
    private String nome;
    private String email;
    
    public static AdministradorDTO create(Administrador administrador){
        ModelMapper mapper = new ModelMapper();
        return mapper.map(administrador, AdministradorDTO.class);
    }


}
