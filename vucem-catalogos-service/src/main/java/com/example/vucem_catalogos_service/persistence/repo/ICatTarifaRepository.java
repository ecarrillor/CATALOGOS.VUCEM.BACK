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
import java.time.LocalDate;
import java.util.Optional;

public interface ICatTarifaRepository extends JpaRepository<CatTarifa, CatTarifaId> {

    @Query("""
            SELECT new com.example.vucem_catalogos_service.model.dto.CatTarifaDTO(
                CAST(t.id AS long),
                t.descSubservicio,
                e.id.fecIniVigencia,
                e.id.ideTipoTarifa,
                e.id.ideTipoTarifa,
                e.fecFinVigencia,
                e.tarifa,
                e.blnActivo
            )
            FROM CatTarifa e
            JOIN e.idTipoTramite t
            WHERE (:search IS NULL OR LOWER(t.descServicio) LIKE LOWER(CONCAT('%', CAST(:search AS string), '%')))
              AND (:activo IS NULL OR e.blnActivo = :activo)
            """)
    Page<CatTarifaDTO> search(
            @Param("search") String search,
            @Param("activo") Boolean activo,
            Pageable pageable
    );

    @Query("""
            SELECT new com.example.vucem_catalogos_service.model.dto.CatTarifaDTO(
                 CAST(t.id AS long),
                t.descSubservicio,
                e.id.fecIniVigencia,
                e.id.ideTipoTarifa,
                e.id.ideTipoTarifa,
                e.fecFinVigencia,
                e.tarifa,
                e.blnActivo
            )
            FROM CatTarifa e
            JOIN e.idTipoTramite t
            WHERE e.id.idTipoTramite = :idTipoTramite
              AND e.id.fecIniVigencia = :fecIniVigencia
              AND e.id.ideTipoTarifa  = :ideTipoTarifa
            """)
    Optional<CatTarifaDTO> findByTarifaDTO(
            @Param("idTipoTramite") Integer idTipoTramite,
            @Param("fecIniVigencia") LocalDate fecIniVigencia,
            @Param("ideTipoTarifa") String ideTipoTarifa
    );
}
