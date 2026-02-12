package com.example.vucem_catalogos_service.persistence.repo;

import com.example.vucem_catalogos_service.model.entity.CatDocumentoTramite;
import com.example.vucem_catalogos_service.model.entity.CatDocumentoTramiteId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ICatDocumentoTramiteRepository extends JpaRepository<CatDocumentoTramite, CatDocumentoTramiteId> {
}
