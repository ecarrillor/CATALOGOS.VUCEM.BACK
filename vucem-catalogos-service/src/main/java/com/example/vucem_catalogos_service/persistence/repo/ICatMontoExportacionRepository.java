package com.example.vucem_catalogos_service.persistence.repo;

import com.example.vucem_catalogos_service.model.entity.CatMontoExportacion;
import com.example.vucem_catalogos_service.model.entity.CatMontoExportacionId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ICatMontoExportacionRepository extends JpaRepository<CatMontoExportacion, CatMontoExportacionId> {
}
