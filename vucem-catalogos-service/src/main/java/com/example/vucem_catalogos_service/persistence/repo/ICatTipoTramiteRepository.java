package com.example.vucem_catalogos_service.persistence.repo;

import com.example.vucem_catalogos_service.model.entity.CatTipoTramite;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface ICatTipoTramiteRepository extends JpaRepository<CatTipoTramite, Long>,
        JpaSpecificationExecutor<CatTipoTramite> {
}
