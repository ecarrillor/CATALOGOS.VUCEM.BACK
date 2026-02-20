package com.example.vucem_catalogos_service.persistence.repo;

import com.example.vucem_catalogos_service.model.dto.CatLocalidadDTO;
import com.example.vucem_catalogos_service.model.dto.LocalidadDTO;
import com.example.vucem_catalogos_service.model.entity.CatLocalidad;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ICatLocalidadRepository extends JpaRepository<CatLocalidad, String> {

    @Query("""
                SELECT new com.example.vucem_catalogos_service.model.dto.LocalidadDTO(
                    loc.cveLocalidad,
                    CONCAT(loc.nombre, ' - CP ', loc.cp)
                )
                FROM CatLocalidad loc
                JOIN loc.cveDelegMun dm
                JOIN dm.cveEntidad en
                JOIN en.cvePais pais
                WHERE pais.cvePais = :cvePais
                  AND en.cveEntidad = :cveEntidad
                  AND dm.cveDelegMun = :cveMunicipio
                  AND loc.blnActivo = true
            """)
    List<LocalidadDTO> findLocalidades(@Param("cvePais") String cvePais,
                                       @Param("cveEntidad") String cveEntidad,
                                       @Param("cveMunicipio") String cveMunicipio);


    @Query("""
            SELECT new com.example.vucem_catalogos_service.model.dto.CatLocalidadDTO(
                e.cveLocalidad,
                e.nombre,
                e.cveDelegMun.cveDelegMun,
                e.cveDelegMun.nombre,
                e.cp,
                e.fecIniVigencia,
                e.fecFinVigencia,
                e.blnActivo
            )
            FROM CatLocalidad e
             WHERE
                (:search IS NULL OR
                    LOWER(e.nombre) LIKE LOWER(CONCAT('%', CAST(:search AS string), '%')) OR
                    LOWER(e.cp) LIKE LOWER(CONCAT('%', CAST(:search AS string), '%')) OR
                    LOWER(e.cveDelegMun.nombre) LIKE LOWER(CONCAT('%', CAST(:search AS string), '%'))
                )
                AND (
                    :activo IS NULL OR e.blnActivo = :activo
                )
            """)
    Page<CatLocalidadDTO> search(@Param("search") String search,
                                 @Param("activo") Boolean activo,
                                 Pageable pageable);


    @Query("""
            SELECT new com.example.vucem_catalogos_service.model.dto.CatLocalidadDTO(
                e.cveLocalidad,
                e.nombre,
                e.cveDelegMun.cveDelegMun,
                e.cveDelegMun.nombre,
                e.cp,
                e.fecIniVigencia,
                e.fecFinVigencia,
                e.blnActivo
            )
            FROM CatLocalidad e
             WHERE e.cveLocalidad = :cveLocalidad
            """)
    CatLocalidadDTO findByLocalidadDTO(@Param("cveLocalidad") String cveLocalidad);
}
