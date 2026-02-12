package com.example.vucem_catalogos_service.persistence.repo;

import com.example.vucem_catalogos_service.model.entity.CatLeyendaTexto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ICatLeyendaTextoRepository extends JpaRepository<CatLeyendaTexto, Integer> {
}
