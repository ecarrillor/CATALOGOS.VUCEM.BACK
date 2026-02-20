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
             WHERE
                (:search IS NULL OR
                    LOWER(e.descripcion) LIKE LOWER(CONCAT('%', CAST(:search AS string), '%')) OR
                    LOWER(e.cveUnidadMedida.descripcion) LIKE LOWER(CONCAT('%', CAST(:search AS string), '%'))
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
