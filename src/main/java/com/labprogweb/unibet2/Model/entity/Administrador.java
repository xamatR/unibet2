package com.labprogweb.unibet2.Model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Administrador extends Pessoa{

    @Column(nullable = false)
    private String email;

}
