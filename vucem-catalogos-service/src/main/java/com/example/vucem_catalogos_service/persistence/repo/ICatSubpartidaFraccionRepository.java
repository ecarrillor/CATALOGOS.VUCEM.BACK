package com.example.vucem_catalogos_service.persistence.repo;

import com.example.vucem_catalogos_service.model.dto.CatSubpartidaFraccionDTO;
import com.example.vucem_catalogos_service.model.entity.CatSubpartidaFraccion;
import com.example.vucem_catalogos_service.model.entity.CatSubpartidaFraccionId;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface ICatSubpartidaFraccionRepository extends JpaRepository<CatSubpartidaFraccion, CatSubpartidaFraccionId> {

    @Query("""
            SELECT new com.example.vucem_catalogos_service.model.dto.CatSubpartidaFraccionDTO(
                e.id.cveSubpartidaFraccion,
                e.id.cveCapituloFraccion,
                e.id.cvePartidaFraccion,
                e.id.cvePartidaFraccion,
                e.nombre,
                e.fecIniVigencia,
                e.fecFinVigencia,
                e.blnActivo
            )
            FROM CatSubpartidaFraccion e
            WHERE (:search IS NULL OR LOWER(e.nombre) LIKE LOWER(CONCAT('%', CAST(:search AS string), '%')))
              AND (:activo IS NULL OR e.blnActivo = :activo)
            """)
    Page<CatSubpartidaFraccionDTO> search(
            @Param("search") String search,
            @Param("activo") Boolean activo,
            Pageable pageable
    );

    @Query("""
            SELECT new com.example.vucem_catalogos_service.model.dto.CatSubpartidaFraccionDTO(
                e.id.cveSubpartidaFraccion,
                e.id.cveCapituloFraccion,
                e.id.cvePartidaFraccion,
                e.id.cvePartidaFraccion,
                e.nombre,
                e.fecIniVigencia,
                e.fecFinVigencia,
                e.blnActivo
            )
            FROM CatSubpartidaFraccion e
            WHERE e.id.cveSubpartidaFraccion = :cveSubpartidaFraccion
              AND e.id.cveCapituloFraccion    = :cveCapituloFraccion
              AND e.id.cvePartidaFraccion     = :cvePartidaFraccion
            """)
    Optional<CatSubpartidaFraccionDTO> findBySubpartidaFraccionDTO(
            @Param("cveSubpartidaFraccion") String cveSubpartidaFraccion,
            @Param("cveCapituloFraccion") String cveCapituloFraccion,
            @Param("cvePartidaFraccion") String cvePartidaFraccion
    );
}
