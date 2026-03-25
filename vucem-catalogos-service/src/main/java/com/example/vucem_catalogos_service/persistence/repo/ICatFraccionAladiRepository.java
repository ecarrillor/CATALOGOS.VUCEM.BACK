package com.example.vucem_catalogos_service.persistence.repo;

import com.example.vucem_catalogos_service.model.dto.FraccionALADI.CatFraccionALADIResponseDTO;
import com.example.vucem_catalogos_service.model.entity.CatFraccionAladi;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ICatFraccionAladiRepository extends JpaRepository<CatFraccionAladi, Long>, JpaSpecificationExecutor<CatFraccionAladi> {
    @Query(value = """
            SELECT new com.example.vucem_catalogos_service.model.dto.FraccionALADI.CatFraccionALADIResponseDTO(
                a.id,
                a.ideTipoFraccionAladi,
                a.cveFraccion,
                a.descripcion,
                a.fecIniVigencia,
                a.fecFinVigencia,
                a.blnActivo
            )
            FROM CatFraccionAladi a
            WHERE
                     (
                     :search IS NULL OR
                    LOWER(a.ideTipoFraccionAladi) LIKE :search OR
                    LOWER(a.cveFraccion) LIKE :search OR
                    LOWER(a.descripcion) LIKE :search
                     )
                           AND
                           (
                               :activo IS NULL OR a.blnActivo = :activo
                           )
            """,
            countQuery = """
            SELECT COUNT(a)
            FROM CatFraccionAladi a
            WHERE
                     (
                     :search IS NULL OR
                    LOWER(a.ideTipoFraccionAladi) LIKE :search OR
                    LOWER(a.cveFraccion) LIKE :search OR
                    LOWER(a.descripcion) LIKE :search
                     )
                           AND
                           (
                               :activo IS NULL OR a.blnActivo = :activo
                           )
            """)
    Page<CatFraccionALADIResponseDTO> search(
            @Param("search") String search,
            @Param("activo") Boolean activo,
            Pageable pageable
    );

    @Query("SELECT COALESCE(MAX(e.id),0) FROM CatFraccionAladi e")
    Long findMaxId();
}
