package com.example.projeto_carros.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;


public record CarroRequestDTO (String marcaCarro, String modeloCarro, Long anoCarro, String imagemCarro, Long idEstacionamento){
}