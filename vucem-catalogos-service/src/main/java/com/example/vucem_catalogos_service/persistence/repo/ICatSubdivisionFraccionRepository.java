package com.example.vucem_catalogos_service.persistence.repo;

import com.example.vucem_catalogos_service.model.dto.CatSubdivisionFraccionDTO;
import com.example.vucem_catalogos_service.model.entity.CatSubdivisionFraccion;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface ICatSubdivisionFraccionRepository extends JpaRepository<CatSubdivisionFraccion, String> {

    @Query(value = """
            SELECT new com.example.vucem_catalogos_service.model.dto.CatSubdivisionFraccionDTO(
                e.cveSubdivision,
                e.cveFraccion.cveFraccion,
                e.cveFraccion.descripcion,
                e.codSubdivision,
                e.descripcion,
                e.precioEstimado,
                e.fecIniVigencia,
                e.fecFinVigencia,
                e.blnActivo
            )
            FROM CatSubdivisionFraccion e
            WHERE (:search IS NULL OR
                LOWER(e.cveSubdivision) LIKE :search OR
                LOWER(e.cveFraccion.cveFraccion) LIKE :search OR
                LOWER(e.cveFraccion.descripcion) LIKE :search OR
                LOWER(e.codSubdivision) LIKE :search OR
                LOWER(e.descripcion) LIKE :search OR
                LOWER(CAST(e.precioEstimado AS string)) LIKE :search OR
                LOWER(CAST(e.fecIniVigencia AS string)) LIKE :search OR
                LOWER(CAST(e.fecFinVigencia AS string)) LIKE :search)
              AND (:activo IS NULL OR e.blnActivo = :activo)
            """,
            countQuery = """
            SELECT COUNT(e)
            FROM CatSubdivisionFraccion e
            WHERE (:search IS NULL OR
                LOWER(e.cveSubdivision) LIKE :search OR
                LOWER(e.cveFraccion.cveFraccion) LIKE :search OR
                LOWER(e.cveFraccion.descripcion) LIKE :search OR
                LOWER(e.codSubdivision) LIKE :search OR
                LOWER(e.descripcion) LIKE :search OR
                LOWER(CAST(e.precioEstimado AS string)) LIKE :search OR
                LOWER(CAST(e.fecIniVigencia AS string)) LIKE :search OR
                LOWER(CAST(e.fecFinVigencia AS string)) LIKE :search)
              AND (:activo IS NULL OR e.blnActivo = :activo)
            """)
    Page<CatSubdivisionFraccionDTO> search(
            @Param("search") String search,
            @Param("activo") Boolean activo,
            Pageable pageable
    );

    @Query("""
            SELECT new com.example.vucem_catalogos_service.model.dto.CatSubdivisionFraccionDTO(
                e.cveSubdivision,
                e.cveFraccion.cveFraccion,
                e.cveFraccion.descripcion,
                e.codSubdivision,
                e.descripcion,
                e.precioEstimado,
                e.fecIniVigencia,
                e.fecFinVigencia,
                e.blnActivo
            )
            FROM CatSubdivisionFraccion e
            WHERE e.cveSubdivision = :cveSubdivision
            """)
    Optional<CatSubdivisionFraccionDTO> findBySubdivisionFraccionDTO(@Param("cveSubdivision") String cveSubdivision);
}
