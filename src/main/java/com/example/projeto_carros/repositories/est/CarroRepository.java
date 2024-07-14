package com.example.projeto_carros.repositories.est;

import com.example.projeto_carros.domain.est.Carro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CarroRepository extends JpaRepository<Carro, Long> {
}
