package com.labprogweb.unibet2.api.dto;

import org.modelmapper.ModelMapper;
import com.labprogweb.unibet2.Model.entity.Cliente;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClienteDTO {
    
    private long id;
    private String nome;
    private String email;
    private String login;
    private String cpf;
    private String agencia;
    private String conta;
    private String banco;
    private String tipoConta;

    public static ClienteDTO create(Cliente cliente){
        ModelMapper mapper = new ModelMapper();
        ClienteDTO dto = mapper.map(cliente, ClienteDTO.class);
        return dto;
    }
}
