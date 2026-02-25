package com.example.vucem_catalogos_service.persistence.repo;


import com.example.vucem_catalogos_service.model.entity.CatTipoCertificado;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ICatTipoCertificado extends JpaRepository<CatTipoCertificado, Long> {
}
