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
import java.util.Optional;

@Repository
public interface ICatDescripcionProdRepository extends JpaRepository<CatDescripcionProd, Integer>,
        JpaSpecificationExecutor<CatDescripcionProd> {

    @Query(value = """
            SELECT new com.example.vucem_catalogos_service.model.dto.DescripcionProd.CatDescripcionProdResponseDTO(
                    d.id,
                    d.descripcionProducto,
                    d.fecCaptura,
                    d.fecIniVigencia,
                    d.fecFinVigencia,
                    d.blnActivo,
                    t.id,
                    t.descModalidad
                )
                FROM CatTipoTramite t
                JOIN CatFraccionTtra f
                    ON f.idTipoTramite.id = t.id
                JOIN CatFraccionTtraDescProd dp
                    ON dp.idFraccionGob.id = f.id
                JOIN CatDescripcionProd d
                    ON d.id = dp.idDescripcionProd.id
                 WHERE
                (
                   :search IS NULL OR
                   LOWER(CAST(d.id AS string)) LIKE :search OR
                   LOWER(d.descripcionProducto) LIKE :search OR
                   LOWER(CAST(d.fecCaptura AS string)) LIKE :search OR
                   LOWER(CAST(d.fecIniVigencia AS string)) LIKE :search OR
                   LOWER(CAST(d.fecFinVigencia AS string)) LIKE :search OR
                   LOWER(CAST(t.id AS string)) LIKE :search OR
                   LOWER(t.descModalidad) LIKE :search
                )
                AND (:activo IS NULL OR d.blnActivo = :activo)
                AND (:idTipoTramite IS NULL OR t.id = :idTipoTramite)
                GROUP BY
                    d.id,
                    d.descripcionProducto,
                    d.fecCaptura,
                    d.fecIniVigencia,
                    d.fecFinVigencia,
                    d.blnActivo,
                    t.id,
                    t.descModalidad
            """,
            countQuery = """
                SELECT COUNT(DISTINCT d.id)
                FROM CatTipoTramite t
                JOIN CatFraccionTtra f
                    ON f.idTipoTramite.id = t.id
                JOIN CatFraccionTtraDescProd dp
                    ON dp.idFraccionGob.id = f.id
                JOIN CatDescripcionProd d
                    ON d.id = dp.idDescripcionProd.id
                WHERE
                (
                   :search IS NULL OR
                   LOWER(CAST(d.id AS string)) LIKE :search OR
                   LOWER(d.descripcionProducto) LIKE :search OR
                   LOWER(CAST(d.fecCaptura AS string)) LIKE :search OR
                   LOWER(CAST(d.fecIniVigencia AS string)) LIKE :search OR
                   LOWER(CAST(d.fecFinVigencia AS string)) LIKE :search OR
                   LOWER(CAST(t.id AS string)) LIKE :search OR
                   LOWER(t.descModalidad) LIKE :search
                )
                AND (:activo IS NULL OR d.blnActivo = :activo)
                AND (:idTipoTramite IS NULL OR t.id = :idTipoTramite)
            """)
    Page<CatDescripcionProdResponseDTO> search(
            @Param("search") String search,
            @Param("activo") Boolean activo,
            @Param("idTipoTramite") Long idTipoTramite,
            Pageable pageable);

    @Query("""
            SELECT DISTINCT new com.example.vucem_catalogos_service.model.dto.ClasifProductoTraDTO(
               t.id,
               t.descModalidad
            )
            FROM CatTipoTramite t
            JOIN CatFraccionTtra f ON f.idTipoTramite.id = t.id
            JOIN CatFraccionTtraDescProd dp ON dp.idFraccionGob.id = f.id
            JOIN CatDescripcionProd d ON d.id = dp.idDescripcionProd.id
            WHERE t.blnActivo = true
              AND t.cveServicio IN ('23', '25')
            ORDER BY t.descModalidad ASC
""")
    List<ClasifProductoTraDTO> listadoTipoTramite();

    List<CatDescripcionProd> findAllByBlnActivoTrueOrderByDescripcionProductoAsc();

    Optional<CatDescripcionProd> findTopByOrderByIdDesc();

    @Query("""
            SELECT  new com.example.vucem_catalogos_service.model.dto.DescripcionProd.CatDescripcionProdResponseDTO(
                    d.id,
                    d.descripcionProducto,
                    d.fecCaptura,
                    d.fecIniVigencia,
                    d.fecFinVigencia,
                    d.blnActivo,
                    t.id,
                    t.descModalidad
                )
                FROM CatTipoTramite t
                JOIN CatFraccionTtra f
                    ON f.idTipoTramite.id = t.id
                JOIN CatFraccionTtraDescProd dp
                    ON dp.idFraccionGob.id = f.id
                JOIN CatDescripcionProd d
                    ON d.id = dp.idDescripcionProd.id
                 WHERE
                        d.id = :id AND t.id = :idTipoTramite
               GROUP BY
                    d.id,
                    d.descripcionProducto,
                    d.fecCaptura,
                    d.fecIniVigencia,
                    d.fecFinVigencia,
                    d.blnActivo,
                    t.id,
                    t.descModalidad
""")
    CatDescripcionProdResponseDTO findByIdTipoTramite(
            @Param("id") Integer id,
            @Param("idTipoTramite") Long idTipoTramite);
}
