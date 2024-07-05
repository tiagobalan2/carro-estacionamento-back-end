package com.example.projeto_carros.controllers;

import com.example.projeto_carros.domain.Estacionamento;
import com.example.projeto_carros.dto.EstacionamentoResponseDTO;
import com.example.projeto_carros.repositories.EstacionamentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/estacionamentos")
public class EstacionamentoController {

    @Autowired
    private EstacionamentoRepository estacionamentoRepository;

    @GetMapping
    public ResponseEntity<List<EstacionamentoResponseDTO>> getAllEstacionamentos() {
        List<Estacionamento> estacionamentos = estacionamentoRepository.findAll();
        List<EstacionamentoResponseDTO> responseDTOS = estacionamentos.stream()
                .map(estacionamento -> new EstacionamentoResponseDTO(
                        estacionamento.getIdEstacionamento(),
                        estacionamento.getNomeEstacionamento(),
                        estacionamento.getLocalizacaoEstacionamento(),
                        estacionamento.getListaDosCarros().stream()
                                .map(carro -> carro.getMarcaCarro() + " " + carro.getModeloCarro())
                                .collect(Collectors.toList())
                ))
                .collect(Collectors.toList());
        return new ResponseEntity<>(responseDTOS, HttpStatus.OK);

    }

    @PostMapping
    public Estacionamento createEstacionamento(@RequestBody Estacionamento estacionamento) {
        return estacionamentoRepository.save(estacionamento);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteEstacionamento(@PathVariable Long id) {
        Optional<Estacionamento> estacionamentoOptional = estacionamentoRepository.findById(id);

        if (estacionamentoOptional.isPresent()) {
            estacionamentoRepository.delete(estacionamentoOptional.get());
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }


}
