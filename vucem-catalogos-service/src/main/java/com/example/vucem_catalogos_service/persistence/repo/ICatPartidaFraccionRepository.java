package com.example.vucem_catalogos_service.persistence.repo;

import com.example.vucem_catalogos_service.model.dto.CatPartidaFraccionDTO;
import com.example.vucem_catalogos_service.model.entity.CatPartidaFraccion;
import com.example.vucem_catalogos_service.model.entity.CatPartidaFraccionId;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ICatPartidaFraccionRepository extends JpaRepository<CatPartidaFraccion, CatPartidaFraccionId> {

    @Query("""
            SELECT new com.example.vucem_catalogos_service.model.dto.CatPartidaFraccionDTO(
                e.id.cveCapituloFraccion,
                e.id.cvePartidaFraccion,
                cap.nombre,
                e.nombre,
                e.fecIniVigencia,
                e.fecFinVigencia,
                e.blnActivo
            )
            FROM CatPartidaFraccion e
            LEFT JOIN e.cveCapituloFraccion cap
            WHERE (:search IS NULL OR
                LOWER(e.nombre) LIKE LOWER(CONCAT('%', CAST(:search AS string), '%')) OR
                LOWER(e.id.cvePartidaFraccion) LIKE LOWER(CONCAT('%', CAST(:search AS string), '%')) OR
                LOWER(e.id.cveCapituloFraccion) LIKE LOWER(CONCAT('%', CAST(:search AS string), '%'))
            )
            AND (:activo IS NULL OR e.blnActivo = :activo)
            """)
    Page<CatPartidaFraccionDTO> search(@Param("search") String search,
                                       @Param("activo") Boolean activo,
                                       Pageable pageable);

    @Query("""
            SELECT new com.example.vucem_catalogos_service.model.dto.CatPartidaFraccionDTO(
                e.id.cveCapituloFraccion,
                e.id.cvePartidaFraccion,
                cap.nombre,
                e.nombre,
                e.fecIniVigencia,
                e.fecFinVigencia,
                e.blnActivo
            )
            FROM CatPartidaFraccion e
            LEFT JOIN e.cveCapituloFraccion cap
            WHERE e.id.cveCapituloFraccion = :cveCapituloFraccion
              AND e.id.cvePartidaFraccion   = :cvePartidaFraccion
            """)
    CatPartidaFraccionDTO findByPartidaFraccionDTO(@Param("cveCapituloFraccion") String cveCapituloFraccion,
                                                   @Param("cvePartidaFraccion") String cvePartidaFraccion);
}
