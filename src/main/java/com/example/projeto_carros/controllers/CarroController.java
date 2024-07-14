package com.example.projeto_carros.controllers;


import com.example.projeto_carros.domain.est.Carro;
import com.example.projeto_carros.domain.est.Estacionamento;
import com.example.projeto_carros.dto.est.CarroRequestDTO;
import com.example.projeto_carros.repositories.est.CarroRepository;
import com.example.projeto_carros.repositories.est.EstacionamentoRepository;
import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

// endpoint para os carros
@RestController
@RequestMapping("/carros")
public class CarroController {

    @Autowired
    private CarroRepository carroRepository;
    @Autowired
    private EstacionamentoRepository estacionamentoRepository;

    @GetMapping
    public List<Carro> getAllCarros() {
        return carroRepository.findAll();
    }

    @PostMapping
    public ResponseEntity<Carro> createCarro(@RequestBody CarroRequestDTO body) {
        Optional<Estacionamento> estacionamentoOptional = estacionamentoRepository.findById(body.idEstacionamento());

        if(!estacionamentoOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }

        Estacionamento estacionamento = estacionamentoOptional.get();
        Carro newCarro = new Carro();
        newCarro.setMarcaCarro(body.marcaCarro());
        newCarro.setModeloCarro(body.modeloCarro());
        newCarro.setAnoCarro(body.anoCarro());
        newCarro.setImagemCarro(body.imagemCarro());
        newCarro.setEstacionamento(estacionamento);

        Carro savedCarro = carroRepository.save(newCarro);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedCarro);

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCarro(@PathVariable Long id) {
        Optional<Carro> carroOptional = carroRepository.findById(id);
        if (carroOptional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        carroRepository.delete(carroOptional.get());
        return ResponseEntity.ok().build();

    }



}
