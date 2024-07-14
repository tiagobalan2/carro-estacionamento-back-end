package com.example.projeto_carros.dto.est;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

public record EstacionamentoResponseDTO(Long idEstacionamento, String nomeEstacionamento, String localizacaoEstacionamento, List<String> listaDosCarros) {
}
