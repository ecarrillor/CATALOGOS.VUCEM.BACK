package com.example.vucem_catalogos_service.persistence.repo;

import com.example.vucem_catalogos_service.model.dto.CatFraccionHtsUsaDTO;
import com.example.vucem_catalogos_service.model.entity.CatFraccionHtsUsa;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ICatFraccionHtsUsaRepository extends JpaRepository<CatFraccionHtsUsa, Long> {

    @Query(value = """
            SELECT new com.example.vucem_catalogos_service.model.dto.CatFraccionHtsUsaDTO(
                e.id,
                e.cveFraccionHtsUsa,
                e.descripcion,
                e.fecCaptura,
                e.fecIniVigencia,
                e.fecFinVigencia,
                e.ideTipoBienFraccion,
                e.blnExenta,
                e.blnActivo,
                um.cveUnidadMedida,
                um.descripcion
            )
            FROM CatFraccionHtsUsa e
            LEFT JOIN e.cveUnidadMedida um
             WHERE
                (:search IS NULL OR
                    LOWER(CAST(e.id AS string)) LIKE :search OR
                    LOWER(e.cveFraccionHtsUsa) LIKE :search OR
                    LOWER(e.descripcion) LIKE :search OR
                    LOWER(CAST(e.fecCaptura AS string)) LIKE :search OR
                    LOWER(CAST(e.fecIniVigencia AS string)) LIKE :search OR
                    LOWER(CAST(e.fecFinVigencia AS string)) LIKE :search OR
                    LOWER(e.ideTipoBienFraccion) LIKE :search OR
                    LOWER(um.cveUnidadMedida) LIKE :search OR
                    LOWER(um.descripcion) LIKE :search
                )
                AND (
                    :activo IS NULL OR e.blnActivo = :activo
                )
            """,
            countQuery = """
            SELECT COUNT(e)
            FROM CatFraccionHtsUsa e
            LEFT JOIN e.cveUnidadMedida um
             WHERE
                (:search IS NULL OR
                    LOWER(CAST(e.id AS string)) LIKE :search OR
                    LOWER(e.cveFraccionHtsUsa) LIKE :search OR
                    LOWER(e.descripcion) LIKE :search OR
                    LOWER(CAST(e.fecCaptura AS string)) LIKE :search OR
                    LOWER(CAST(e.fecIniVigencia AS string)) LIKE :search OR
                    LOWER(CAST(e.fecFinVigencia AS string)) LIKE :search OR
                    LOWER(e.ideTipoBienFraccion) LIKE :search OR
                    LOWER(um.cveUnidadMedida) LIKE :search OR
                    LOWER(um.descripcion) LIKE :search
                )
                AND (
                    :activo IS NULL OR e.blnActivo = :activo
                )
            """)
    Page<CatFraccionHtsUsaDTO> search(@Param("search") String search,
                                       @Param("activo") Boolean activo,
                                       Pageable pageable);

    @Query("""
            SELECT new com.example.vucem_catalogos_service.model.dto.CatFraccionHtsUsaDTO(
                e.id,
                e.cveFraccionHtsUsa,
                e.descripcion,
                e.fecCaptura,
                e.fecIniVigencia,
                e.fecFinVigencia,
                e.ideTipoBienFraccion,
                e.blnExenta,
                e.blnActivo,
                um.cveUnidadMedida,
                um.descripcion
            )
            FROM CatFraccionHtsUsa e
            LEFT JOIN e.cveUnidadMedida um
            WHERE e.id = :id
            """)
    CatFraccionHtsUsaDTO findByFraccionHtsUsaDTO(@Param("id") Long id);
}
