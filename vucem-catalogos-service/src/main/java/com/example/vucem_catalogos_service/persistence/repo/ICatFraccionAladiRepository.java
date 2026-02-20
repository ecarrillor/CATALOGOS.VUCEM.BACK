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
    @Query("""
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
                    LOWER(a.ideTipoFraccionAladi) LIKE LOWER(CONCAT('%', CAST(:search AS string), '%')) OR
                    LOWER(a.cveFraccion) LIKE LOWER(CONCAT('%', CAST(:search AS string), '%')) OR
                    LOWER(a.descripcion) LIKE LOWER(CONCAT('%', CAST(:search AS string), '%'))
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
    );}
