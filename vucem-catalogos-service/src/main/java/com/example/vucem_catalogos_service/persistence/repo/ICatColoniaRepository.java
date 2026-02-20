package com.example.vucem_catalogos_service.persistence.repo;

import com.example.vucem_catalogos_service.model.dto.CatColoniaDTO;
import com.example.vucem_catalogos_service.model.entity.CatColonia;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ICatColoniaRepository extends JpaRepository<CatColonia, String> {

    @Query("""
            SELECT new com.example.vucem_catalogos_service.model.dto.CatColoniaDTO(
                cat.cveColonia,
                cat.nombre,
                dm.nombre,
                loc.nombre,
                cat.cp,
                cat.fecCaptura,
                cat.fecIniVigencia,
                cat.fecFinVigencia,
                cat.blnActivo,
                dm.cveDelegMun,
                loc.cveLocalidad,
                pais.nombre
            )
            FROM CatColonia cat
            LEFT JOIN cat.cveDelegMun dm
            LEFT JOIN dm.cveEntidad en
            LEFT JOIN en.cvePais pais
            LEFT JOIN cat.cveLocalidad loc
            WHERE
                (:nombre IS NULL OR LOWER(cat.nombre) LIKE LOWER(CONCAT('%', CAST(:nombre AS string), '%')))
                AND (:cp IS NULL OR LOWER(cat.cp) LIKE LOWER(CONCAT('%', CAST(:cp AS string), '%')))
                AND (:municipio IS NULL OR LOWER(dm.nombre) LIKE LOWER(CONCAT('%', CAST(:municipio AS string), '%')))
                AND (:nombrePais IS NULL OR LOWER(pais.nombre) LIKE LOWER(CONCAT('%', CAST(:nombrePais AS string), '%')))
                AND (:blnActivo IS NULL OR cat.blnActivo = :blnActivo)
            """)
    Page<CatColoniaDTO> search(
            @Param("nombre") String nombre,
            @Param("cp") String cp,
            @Param("municipio") String municipio,
            @Param("nombrePais") String nombrePais,
            @Param("blnActivo") Boolean blnActivo,
            Pageable pageable);

    @Query("""
            SELECT new com.example.vucem_catalogos_service.model.dto.CatColoniaDTO(
                cat.cveColonia,
                cat.nombre,
                dm.nombre,
                loc.nombre,
                cat.cp,
                cat.fecCaptura,
                cat.fecIniVigencia,
                cat.fecFinVigencia,
                cat.blnActivo,
                dm.cveDelegMun,
                loc.cveLocalidad,
                pais.nombre
            )
            FROM CatColonia cat
            LEFT JOIN cat.cveDelegMun dm
            LEFT JOIN dm.cveEntidad en
            LEFT JOIN en.cvePais pais
            LEFT JOIN cat.cveLocalidad loc
            WHERE
                (:nombre IS NULL OR LOWER(cat.nombre) LIKE LOWER(CONCAT('%', CAST(:nombre AS string), '%')))
                AND (:cp IS NULL OR LOWER(cat.cp) LIKE LOWER(CONCAT('%', CAST(:cp AS string), '%')))
                AND (:municipio IS NULL OR LOWER(dm.nombre) LIKE LOWER(CONCAT('%', CAST(:municipio AS string), '%')))
                AND (:nombrePais IS NULL OR LOWER(pais.nombre) LIKE LOWER(CONCAT('%', CAST(:nombrePais AS string), '%')))
                AND (:blnActivo IS NULL OR cat.blnActivo = :blnActivo)
            """)
    List<CatColoniaDTO> searchAll(
            @Param("nombre") String nombre,
            @Param("cp") String cp,
            @Param("municipio") String municipio,
            @Param("nombrePais") String nombrePais,
            @Param("blnActivo") Boolean blnActivo);

    @Query("""
            SELECT new com.example.vucem_catalogos_service.model.dto.CatColoniaDTO(
                cat.cveColonia,
                cat.nombre,
                dm.nombre,
                loc.nombre,
                cat.cp,
                cat.fecCaptura,
                cat.fecIniVigencia,
                cat.fecFinVigencia,
                cat.blnActivo,
                dm.cveDelegMun,
                loc.cveLocalidad,
                pais.nombre
            )
            FROM CatColonia cat
            LEFT JOIN cat.cveDelegMun dm
            LEFT JOIN dm.cveEntidad en
            LEFT JOIN en.cvePais pais
            LEFT JOIN cat.cveLocalidad loc
            WHERE cat.cveColonia = :cveColonia
            """)
    CatColoniaDTO findByColoniaDTO(@Param("cveColonia") String cveColonia);

    @Query("""
            SELECT new com.example.vucem_catalogos_service.model.dto.CatColoniaDTO(
                cat.cveColonia,
                cat.nombre,
                dm.nombre,
                loc.nombre,
                cat.cp,
                cat.fecCaptura,
                cat.fecIniVigencia,
                cat.fecFinVigencia,
                cat.blnActivo,
                dm.cveDelegMun,
                loc.cveLocalidad,
                pais.nombre
            )
              FROM CatColonia cat
              LEFT JOIN cat.cveDelegMun dm
              LEFT JOIN dm.cveEntidad en
              LEFT JOIN en.cvePais pais
              LEFT JOIN cat.cveLocalidad loc
              WHERE cat.cp = :cp
            """)
    List<CatColoniaDTO> findByCp(@Param("cp") String cp);
}
