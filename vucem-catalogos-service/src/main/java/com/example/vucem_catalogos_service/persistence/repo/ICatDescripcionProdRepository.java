package com.example.vucem_catalogos_service.persistence.repo;

import com.example.vucem_catalogos_service.model.dto.ClasifProductoTraDTO;
import com.example.vucem_catalogos_service.model.dto.DescripcionProd.CatDescripcionProdResponseDTO;
import com.example.vucem_catalogos_service.model.entity.CatDescripcionProd;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ICatDescripcionProdRepository extends JpaRepository<CatDescripcionProd, Integer>,
        JpaSpecificationExecutor<CatDescripcionProd> {

    @Query("""
            SELECT new com.example.vucem_catalogos_service.model.dto.DescripcionProd.CatDescripcionProdResponseDTO(
                d.id,
                d.descripcionProducto,
                d.fecCaptura,
                d.fecIniVigencia,
                d.fecFinVigencia,
                d.blnActivo
            )
            FROM CatDescripcionProd d
            WHERE
                (:search IS NULL OR LOWER(d.descripcionProducto) LIKE LOWER(CONCAT('%', CAST(:search AS string), '%')))
                AND (:activo IS NULL OR d.blnActivo = :activo)
            """)
    Page<CatDescripcionProdResponseDTO> search(
            @Param("search") String search,
            @Param("activo") Boolean activo,
            Pageable pageable);

    @Query("""
            SELECT DISTINCT new com.example.vucem_catalogos_service.model.dto.ClasifProductoTraDTO(
                t.id,
                COALESCE(t.descModalidad, t.descSubservicio)
            )
            FROM CatClasifProducto cp
            JOIN cp.idTipoTramite t
            WHERE t.blnActivo = true
              AND t.cveServicio = '26'
            ORDER BY COALESCE(t.descModalidad, t.descSubservicio) ASC
            """)
    List<ClasifProductoTraDTO> listadoTipoTramite();

    List<CatDescripcionProd> findAllByBlnActivoTrueOrderByDescripcionProductoAsc();

}
