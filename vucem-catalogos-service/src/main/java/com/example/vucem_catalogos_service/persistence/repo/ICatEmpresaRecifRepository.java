package com.example.vucem_catalogos_service.persistence.repo;

import com.example.vucem_catalogos_service.model.entity.CatEmpresaRecif;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ICatEmpresaRecifRepository extends JpaRepository<CatEmpresaRecif, String> {
}
