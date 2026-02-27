package com.example.vucem_catalogos_service.persistence.repo;

import com.example.vucem_catalogos_service.model.dto.ClasifProductoTraDTO;
import com.example.vucem_catalogos_service.model.dto.RestricDescProd.CatRestricDescProdResponseDTO;
import com.example.vucem_catalogos_service.model.entity.CatRestricDescProd;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ICatRestricDescProdRepository extends JpaRepository<CatRestricDescProd, Long>, JpaSpecificationExecutor<CatRestricDescProd> {

    @Query("""
            SELECT new com.example.vucem_catalogos_service.model.dto.RestricDescProd.CatRestricDescProdResponseDTO(
                a.id,
                a.idRestriccionTtra.id,
                a.idRestriccionTtra.descRestriccion,
                a.idDescripcionProd.id,
                a.idDescripcionProd.descripcionProducto,
                a.fecIniVigencia,
                a.fecFinVigencia,
                a.blnActivo
            )
            FROM CatRestricDescProd a
            JOIN a.idRestriccionTtra rt
            JOIN rt.idTipoTramite tr
            WHERE
                (
                    :search IS NULL OR
                    LOWER(a.idRestriccionTtra.descRestriccion) LIKE LOWER(CONCAT('%', CAST(:search AS string), '%')) OR
                    LOWER(a.idDescripcionProd.descripcionProducto) LIKE LOWER(CONCAT('%', CAST(:search AS string), '%'))
                )
                AND (:activo IS NULL OR a.blnActivo = :activo)
                AND (:idTipoTramite IS NULL OR tr.id = :idTipoTramite)
            """)
    Page<CatRestricDescProdResponseDTO> search(
            @Param("search") String search,
            @Param("activo") Boolean activo,
            @Param("idTipoTramite") Long idTipoTramite,
            Pageable pageable);

    @Query("""
            SELECT DISTINCT new com.example.vucem_catalogos_service.model.dto.ClasifProductoTraDTO(
                tr.id,
                COALESCE(tr.descModalidad, tr.descSubservicio)
            )
            FROM CatRestricDescProd a
            JOIN a.idRestriccionTtra rt
            JOIN rt.idTipoTramite tr
            WHERE a.blnActivo = true
              AND tr.blnActivo = true
              AND tr.cveServicio = '23'
            ORDER BY UPPER(TRIM(COALESCE(tr.descModalidad, tr.descSubservicio))) ASC
            """)
    List<ClasifProductoTraDTO> listadoTipoTramite();

}
