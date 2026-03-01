package com.example.vucem_catalogos_service.persistence.repo;

import com.example.vucem_catalogos_service.model.entity.CatCa;
import com.example.vucem_catalogos_service.model.dto.CatCas.CatCaResponseDTO;
import com.example.vucem_catalogos_service.model.dto.ClasifProductoTraDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ICatCasRepository extends JpaRepository<CatCa, Short> {


    @Query("""
    SELECT a
    FROM CatCa a
    WHERE a.blActivo = :blActivo
    ORDER BY a.descCas ASC
""")
    List<CatCa> findAllByBlActivoOrderByDescCasAsc(@Param("blActivo") Boolean blActivo);


    @Query("""
    SELECT DISTINCT new com.example.vucem_catalogos_service.model.dto.CatCas.CatCaResponseDTO(
        cas.id,
        cas.descCas,
        cas.fecIniVigencia,
        cas.fecFinVigencia,
        cas.blActivo,
        tt.id,
        tt.descModalidad
    )
    FROM CatCa cas
    JOIN CatCasFraccionTtra cf ON cf.idCas.id = cas.id
    JOIN CatTipoTramite tt ON tt.id = cf.idTipoTramite.id
    WHERE
                (
                   :search IS NULL OR
                   LOWER(cas.descCas) LIKE :search OR
                   STR(cas.id) LIKE :search
                )
                AND (:activo IS NULL OR cas.blActivo = :activo)
                AND (:idTipoTramite IS NULL OR tt.id = :idTipoTramite)
        AND tt.cveServicio IN ('23','26')
    ORDER BY cas.id ASC
""")
    Page<CatCaResponseDTO> search(
            @Param("search") String search,
            @Param("activo") Boolean activo,
            @Param("idTipoTramite") Long idTipoTramite,
            Pageable pageable
    );

    @Query("""
    SELECT DISTINCT new com.example.vucem_catalogos_service.model.dto.ClasifProductoTraDTO(
        tt.id,
        CONCAT(
            CAST(tt.id AS string),
            '  ',
            COALESCE(tt.descModalidad, tt.descSubservicio)
        )
    )
    FROM CatCasFraccionTtra cf
    JOIN cf.idCas cas
    JOIN cf.idTipoTramite tt
    WHERE tt.blnActivo = true
      AND tt.cveServicio IN ('23', '26')
    ORDER BY 2 ASC
""")
    List<ClasifProductoTraDTO> listadoTipoTramite();


    @Query("""
       SELECT c
       FROM CatCa c
       WHERE c.blActivo = :activo
       ORDER BY c.descCas ASC
       """)
    List<CatCa> findAllActivos(@Param("activo") Boolean activo);

    @Query("SELECT MAX(c.id) FROM CatCasFraccionTtra c")
    Short findMaxId();
}
