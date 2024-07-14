package com.example.projeto_carros.domain.est;


import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "carros")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Carro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idCarro;

    private String marcaCarro;
    private String modeloCarro;
    private Long anoCarro;
    private String imagemCarro;

    @ManyToOne
    @JoinColumn(name = "idEstacionamento")
    // aqui vou ter que por o relacionamento essa fk se refere a pk da tabelas estacionamentos (id_estacionamento)
    private Estacionamento estacionamento;

}
