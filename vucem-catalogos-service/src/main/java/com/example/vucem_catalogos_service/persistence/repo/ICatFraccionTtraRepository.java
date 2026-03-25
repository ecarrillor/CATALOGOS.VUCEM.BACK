package com.example.vucem_catalogos_service.persistence.repo;

import com.example.vucem_catalogos_service.model.dto.ClasifProductoTraDTO;
import com.example.vucem_catalogos_service.model.dto.FraccionTtra.CatFraccionTtraResponseDTO;
import com.example.vucem_catalogos_service.model.entity.CatFraccionTtra;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ICatFraccionTtraRepository extends JpaRepository<CatFraccionTtra, Long>,
        JpaSpecificationExecutor<CatFraccionTtra> {

    @Query(value = """
            SELECT new com.example.vucem_catalogos_service.model.dto.FraccionTtra.CatFraccionTtraResponseDTO(
                a.id,
                fra.cveFraccion,
                fra.descripcion,
                tt.id,
                COALESCE(tt.descModalidad, tt.descSubservicio),
                a.fecIniVigencia,
                a.fecFinVigencia,
                a.blnActivo,
                a.descFraccionAlt,
                a.ideClasifPartida,
                a.blnFraccionControlada,
                cat.id,
                cat.descripcion,
                a.factorConversion,
                a.valorEquivalencia,
                a.cveUnidadMedida,
                a.reglaAplicable
            )
            FROM CatFraccionTtra a
            JOIN a.cveFraccion fra
            JOIN a.idTipoTramite tt
            LEFT JOIN a.idCategoriaTextil cat
            WHERE
                (
                    :search IS NULL OR
                    LOWER(CAST(a.id AS string)) LIKE :search OR
                    LOWER(fra.cveFraccion) LIKE :search OR
                    LOWER(fra.descripcion) LIKE :search OR
                    LOWER(CAST(tt.id AS string)) LIKE :search OR
                    LOWER(CAST(a.fecIniVigencia AS string)) LIKE :search OR
                    LOWER(CAST(a.fecFinVigencia AS string)) LIKE :search OR
                    LOWER(a.descFraccionAlt) LIKE :search OR
                    LOWER(a.ideClasifPartida) LIKE :search OR
                    LOWER(CAST(cat.id AS string)) LIKE :search OR
                    LOWER(cat.descripcion) LIKE :search OR
                    LOWER(CAST(a.factorConversion AS string)) LIKE :search OR
                    LOWER(CAST(a.valorEquivalencia AS string)) LIKE :search OR
                    LOWER(a.cveUnidadMedida) LIKE :search OR
                    LOWER(a.reglaAplicable) LIKE :search
                )
                AND (:activo IS NULL OR a.blnActivo = :activo)
                AND (:idTipoTramite IS NULL OR tt.id = :idTipoTramite)
            """,
            countQuery = """
            SELECT COUNT(a)
            FROM CatFraccionTtra a
            JOIN a.cveFraccion fra
            JOIN a.idTipoTramite tt
            LEFT JOIN a.idCategoriaTextil cat
            WHERE
                (
                    :search IS NULL OR
                    LOWER(CAST(a.id AS string)) LIKE :search OR
                    LOWER(fra.cveFraccion) LIKE :search OR
                    LOWER(fra.descripcion) LIKE :search OR
                    LOWER(CAST(tt.id AS string)) LIKE :search OR
                    LOWER(CAST(a.fecIniVigencia AS string)) LIKE :search OR
                    LOWER(CAST(a.fecFinVigencia AS string)) LIKE :search OR
                    LOWER(a.descFraccionAlt) LIKE :search OR
                    LOWER(a.ideClasifPartida) LIKE :search OR
                    LOWER(CAST(cat.id AS string)) LIKE :search OR
                    LOWER(cat.descripcion) LIKE :search OR
                    LOWER(CAST(a.factorConversion AS string)) LIKE :search OR
                    LOWER(CAST(a.valorEquivalencia AS string)) LIKE :search OR
                    LOWER(a.cveUnidadMedida) LIKE :search OR
                    LOWER(a.reglaAplicable) LIKE :search
                )
                AND (:activo IS NULL OR a.blnActivo = :activo)
                AND (:idTipoTramite IS NULL OR tt.id = :idTipoTramite)
            """)
    Page<CatFraccionTtraResponseDTO> search(
            @Param("search") String search,
            @Param("activo") Boolean activo,
            @Param("idTipoTramite") Long idTipoTramite,
            Pageable pageable);



    @Query("""
            SELECT new com.example.vucem_catalogos_service.model.dto.ClasifProductoTraDTO(
                f.id,
                CONCAT(fra.cveFraccion, ' - ', fra.descripcion)
            )
            FROM CatFraccionTtra f
            JOIN f.cveFraccion fra
            WHERE f.blnActivo = true
              AND f.idTipoTramite.id = :idTipoTramite
            ORDER BY fra.cveFraccion ASC
            """)
    List<ClasifProductoTraDTO> listadoFraccionTtraByTipoTramite(@Param("idTipoTramite") Long idTipoTramite);

    List<CatFraccionTtra> findByIdTipoTramiteId(Long id);

    @Query("SELECT MAX(r.id) FROM CatFraccionTtraDescProd r")
    Long findMaxId();

    CatFraccionTtra findTopByOrderByIdDesc();
}
