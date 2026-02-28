package com.example.vucem_catalogos_service.persistence.repo;

import com.example.vucem_catalogos_service.model.entity.CatCa;
import com.example.vucem_catalogos_service.model.dto.CatCas.CatCaResponseDTO;
import com.example.vucem_catalogos_service.model.dto.ClasifProductoTraDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ICatCasRepository extends JpaRepository<CatCa, Short>,
        JpaSpecificationExecutor<CatCa> {

    @Query("""
    SELECT a
    FROM CatCa a
    WHERE a.blActivo = :blActivo
    ORDER BY a.descCas ASC
""")
    List<CatCa> findAllByBlActivoOrderByDescCasAsc(@Param("blActivo") Boolean blActivo);

    @Query("""
            SELECT new com.example.vucem_catalogos_service.model.dto.CatCas.CatCaResponseDTO(
                a.id,
                a.descCas,
                a.fecIniVigencia,
                a.fecFinVigencia,
                a.blActivo
            )
            FROM CatCa a
            WHERE
                (:search IS NULL OR
                 LOWER(a.descCas) LIKE LOWER(CONCAT('%', CAST(:search AS string), '%')))
                AND (:activo IS NULL OR a.blActivo = :activo)
            """)
    Page<CatCaResponseDTO> search(
            @Param("search") String search,
            @Param("activo") Boolean activo,
            Pageable pageable);

    @Query("""
            SELECT DISTINCT new com.example.vucem_catalogos_service.model.dto.ClasifProductoTraDTO(
                tt.id,
                CONCAT(CAST(tt.id AS string), '  ', COALESCE(tt.descModalidad, tt.descSubservicio))
            )
            FROM CatCasFraccionTtra cf
            JOIN cf.idCas cas
            JOIN cf.idTipoTramite tt
            WHERE tt.blnActivo = true
              AND tt.cveServicio IN ('23', '26')
            ORDER BY UPPER(TRIM(COALESCE(tt.descModalidad, tt.descSubservicio))) ASC
            """)
    List<ClasifProductoTraDTO> listadoTipoTramite();
}
