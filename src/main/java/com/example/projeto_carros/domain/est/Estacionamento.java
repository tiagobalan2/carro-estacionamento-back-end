package com.example.projeto_carros.domain.est;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "estacionamentos")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Estacionamento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idEstacionamento;

    private String nomeEstacionamento;
    private String localizacaoEstacionamento;


    @JsonIgnore
    @OneToMany(mappedBy = "estacionamento", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    // aqui vou ter que por relacionamento
    private List<Carro> listaDosCarros;
}
