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

    @Query(value = """
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
            WHERE (:search IS NULL OR
                LOWER(CAST(t.id AS string)) LIKE :search OR
                LOWER(t.descSubservicio) LIKE :search OR
                LOWER(t.descServicio) LIKE :search OR
                LOWER(CAST(e.id.fecIniVigencia AS string)) LIKE :search OR
                LOWER(e.id.ideTipoTarifa) LIKE :search OR
                LOWER(CAST(e.fecFinVigencia AS string)) LIKE :search OR
                LOWER(CAST(e.tarifa AS string)) LIKE :search)
              AND (:activo IS NULL OR e.blnActivo = :activo)
            """,
            countQuery = """
            SELECT COUNT(e)
            FROM CatTarifa e
            JOIN e.idTipoTramite t
            WHERE (:search IS NULL OR
                LOWER(CAST(t.id AS string)) LIKE :search OR
                LOWER(t.descSubservicio) LIKE :search OR
                LOWER(t.descServicio) LIKE :search OR
                LOWER(CAST(e.id.fecIniVigencia AS string)) LIKE :search OR
                LOWER(e.id.ideTipoTarifa) LIKE :search OR
                LOWER(CAST(e.fecFinVigencia AS string)) LIKE :search OR
                LOWER(CAST(e.tarifa AS string)) LIKE :search)
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
