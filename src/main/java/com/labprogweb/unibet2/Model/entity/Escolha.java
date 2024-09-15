package com.labprogweb.unibet2.Model.entity;

import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Escolha {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String status;
    private LocalDate dataInicio;
    private LocalDate dataFim;

    @ManyToOne
    private Aposta aposta;

    @ManyToOne
    private Evento evento;


    
}
