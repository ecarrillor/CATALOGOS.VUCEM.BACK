package com.example.vucem_catalogos_service.persistence.repo;

import com.example.vucem_catalogos_service.model.entity.CatTipoTramite;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ICatTipoTramiteRepository extends JpaRepository<CatTipoTramite, Long>,
        JpaSpecificationExecutor<CatTipoTramite> {


    @Query("""
    SELECT t
    FROM CatTipoTramite t
    WHERE t.blnActivo = true
      AND t.descModalidad IS NOT NULL
      AND TRIM(t.descModalidad) <> ''
    ORDER BY t.id DESC
""")
    List<CatTipoTramite> findByBlnActivoTrueOrderByIdDesc();

    List<CatTipoTramite> findByBlnActivoTrue();
}
