package com.labprogweb.unibet2.Model.entity;

import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Odds {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private float multiplicador;
    private String tipo;
    private String nome;
    private String resultado;

    @ManyToOne
    private Escolha escolha;


}