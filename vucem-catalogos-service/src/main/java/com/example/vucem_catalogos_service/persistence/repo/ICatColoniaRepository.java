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

    @Query(value = """
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
                (
                    :search IS NULL OR
                    LOWER(cat.cveColonia) LIKE :search OR
                    LOWER(cat.nombre) LIKE :search OR
                    LOWER(dm.nombre) LIKE :search OR
                    LOWER(loc.nombre) LIKE :search OR
                    LOWER(cat.cp) LIKE :search OR
                    LOWER(CAST(cat.fecCaptura AS string)) LIKE :search OR
                    LOWER(CAST(cat.fecIniVigencia AS string)) LIKE :search OR
                    LOWER(CAST(cat.fecFinVigencia AS string)) LIKE :search OR
                    LOWER(dm.cveDelegMun) LIKE :search OR
                    LOWER(pais.nombre) LIKE :search
                )
                AND (
                    :activo IS NULL OR cat.blnActivo = :activo
                )
            """,
            countQuery = """
            SELECT COUNT(cat)
            FROM CatColonia cat
            LEFT JOIN cat.cveDelegMun dm
            LEFT JOIN dm.cveEntidad en
            LEFT JOIN en.cvePais pais
            LEFT JOIN cat.cveLocalidad loc
            WHERE
                (
                    :search IS NULL OR
                    LOWER(cat.cveColonia) LIKE :search OR
                    LOWER(cat.nombre) LIKE :search OR
                    LOWER(dm.nombre) LIKE :search OR
                    LOWER(loc.nombre) LIKE :search OR
                    LOWER(cat.cp) LIKE :search OR
                    LOWER(CAST(cat.fecCaptura AS string)) LIKE :search OR
                    LOWER(CAST(cat.fecIniVigencia AS string)) LIKE :search OR
                    LOWER(CAST(cat.fecFinVigencia AS string)) LIKE :search OR
                    LOWER(dm.cveDelegMun) LIKE :search OR
                    LOWER(pais.nombre) LIKE :search
                )
                AND (
                    :activo IS NULL OR cat.blnActivo = :activo
                )
            """)
    Page<CatColoniaDTO> search(
            @Param("search") String search,
            @Param("activo") Boolean activo,
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
