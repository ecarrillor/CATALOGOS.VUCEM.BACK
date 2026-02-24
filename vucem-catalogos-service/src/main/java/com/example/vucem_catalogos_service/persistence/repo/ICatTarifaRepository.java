package com.example.vucem_catalogos_service.persistence.repo;

import com.example.vucem_catalogos_service.model.dto.CatTarifaDTO;
import com.example.vucem_catalogos_service.model.entity.CatTarifa;
import com.example.vucem_catalogos_service.model.entity.CatTarifaId;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.Instant;
import java.util.Optional;

public interface ICatTarifaRepository extends JpaRepository<CatTarifa, CatTarifaId> {

    @Query("""
            SELECT new com.example.vucem_catalogos_service.model.dto.CatTarifaDTO(
                CAST(e.id.idTipoTramite AS long),
                e.id.fecIniVigencia,
                e.id.ideTipoTarifa,
                e.idTipoTramite.nombre,
                e.fecFinVigencia,
                e.tarifa,
                e.blnActivo
            )
            FROM CatTarifa e
            WHERE (:search IS NULL OR LOWER(e.id.ideTipoTarifa) LIKE LOWER(CONCAT('%', CAST(:search AS string), '%')))
              AND (:activo IS NULL OR e.blnActivo = :activo)
            """)
    Page<CatTarifaDTO> search(
            @Param("search") String search,
            @Param("activo") Boolean activo,
            Pageable pageable
    );

    @Query("""
            SELECT new com.example.vucem_catalogos_service.model.dto.CatTarifaDTO(
                CAST(e.id.idTipoTramite AS long),
                e.id.fecIniVigencia,
                e.id.ideTipoTarifa,
                e.idTipoTramite.nombre,
                e.fecFinVigencia,
                e.tarifa,
                e.blnActivo
            )
            FROM CatTarifa e
            WHERE e.id.idTipoTramite = :idTipoTramite
              AND e.id.fecIniVigencia = :fecIniVigencia
              AND e.id.ideTipoTarifa  = :ideTipoTarifa
            """)
    Optional<CatTarifaDTO> findByTarifaDTO(
            @Param("idTipoTramite") Integer idTipoTramite,
            @Param("fecIniVigencia") Instant fecIniVigencia,
            @Param("ideTipoTarifa") String ideTipoTarifa
    );
}
