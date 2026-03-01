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

    @Query("""
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
                    LOWER(fra.cveFraccion) LIKE LOWER(CONCAT('%', CAST(:search AS string), '%')) OR
                    LOWER(fra.descripcion) LIKE LOWER(CONCAT('%', CAST(:search AS string), '%')) OR
                    LOWER(a.descFraccionAlt) LIKE LOWER(CONCAT('%', CAST(:search AS string), '%'))
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
}
