package com.example.vucem_catalogos_service.persistence.repo;

import com.example.vucem_catalogos_service.model.dto.CatDiaNoLaborableDTO;
import com.example.vucem_catalogos_service.model.entity.CatDiaNoLaborable;
import com.example.vucem_catalogos_service.model.entity.CatDiaNoLaborableId;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.Instant;
import java.time.LocalDate;
import java.util.Optional;

@Repository
public interface ICatDiaNoLaborableRepository extends JpaRepository<CatDiaNoLaborable, CatDiaNoLaborableId> {

    @Query(value = """
            SELECT new com.example.vucem_catalogos_service.model.dto.CatDiaNoLaborableDTO(
                e.id.fecNoLaborable,
                e.id.cveCalendario,
                e.cveCalendario.nombre,
                e.descripcion,
                e.fecIniVigencia,
                e.fecFinVigencia,
                e.blnActivo
            )
            FROM CatDiaNoLaborable e
            WHERE (:search IS NULL OR
                LOWER(CAST(e.id.fecNoLaborable AS string)) LIKE :search OR
                LOWER(e.id.cveCalendario) LIKE :search OR
                LOWER(e.cveCalendario.nombre) LIKE :search OR
                LOWER(e.descripcion) LIKE :search OR
                LOWER(CAST(e.fecIniVigencia AS string)) LIKE :search OR
                LOWER(CAST(e.fecFinVigencia AS string)) LIKE :search)
            AND (:activo IS NULL OR e.blnActivo = :activo)
            """,
            countQuery = """
            SELECT COUNT(e)
            FROM CatDiaNoLaborable e
            WHERE (:search IS NULL OR
                LOWER(CAST(e.id.fecNoLaborable AS string)) LIKE :search OR
                LOWER(e.id.cveCalendario) LIKE :search OR
                LOWER(e.cveCalendario.nombre) LIKE :search OR
                LOWER(e.descripcion) LIKE :search OR
                LOWER(CAST(e.fecIniVigencia AS string)) LIKE :search OR
                LOWER(CAST(e.fecFinVigencia AS string)) LIKE :search)
            AND (:activo IS NULL OR e.blnActivo = :activo)
            """)
    Page<CatDiaNoLaborableDTO> search(@Param("search") String search,
                                       @Param("activo") Boolean activo,
                                       Pageable pageable);

    @Query("""
            SELECT new com.example.vucem_catalogos_service.model.dto.CatDiaNoLaborableDTO(
                e.id.fecNoLaborable,
                e.id.cveCalendario,
                e.cveCalendario.nombre,
                e.descripcion,
                e.fecIniVigencia,
                e.fecFinVigencia,
                e.blnActivo
            )
            FROM CatDiaNoLaborable e
            WHERE e.id.fecNoLaborable = :fecNoLaborable AND e.id.cveCalendario = :cveCalendario
            """)
    Optional<CatDiaNoLaborableDTO> findByDiaNoLaborableDTO(@Param("fecNoLaborable") LocalDate fecNoLaborable,
                                                            @Param("cveCalendario") String cveCalendario);
}
