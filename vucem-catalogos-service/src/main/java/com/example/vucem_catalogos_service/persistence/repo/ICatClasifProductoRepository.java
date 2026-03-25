package com.example.vucem_catalogos_service.persistence.repo;

import com.example.vucem_catalogos_service.model.dto.CatClasifProductoDTO;
import com.example.vucem_catalogos_service.model.dto.ClasifProductoTraDTO;
import com.example.vucem_catalogos_service.model.dto.SelectDTO;
import com.example.vucem_catalogos_service.model.entity.CatClasifProducto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ICatClasifProductoRepository extends JpaRepository<CatClasifProducto, Long> {

    @Query(value = """
                SELECT new com.example.vucem_catalogos_service.model.dto.CatClasifProductoDTO(
                    e.idClasifProduct,
                    t.id,
                    t.descModalidad,
                    r.idClasifProduct,
                    r.nombre,
                    e.nombre,
                    e.ideTipoClasifProducto,
                    e.fecIniVigencia,
                    e.fecFinVigencia,
                    e.blnActivo
                )
                FROM CatClasifProducto e
                LEFT JOIN e.idTipoTramite t
                LEFT JOIN e.idClasifProductoR r
                WHERE (:texto IS NULL OR
                    LOWER(CAST(e.idClasifProduct AS string)) LIKE :texto OR
                    LOWER(CAST(t.id AS string)) LIKE :texto OR
                    LOWER(t.descModalidad) LIKE :texto OR
                    LOWER(CAST(r.idClasifProduct AS string)) LIKE :texto OR
                    LOWER(r.nombre) LIKE :texto OR
                    LOWER(e.nombre) LIKE :texto OR
                    LOWER(e.ideTipoClasifProducto) LIKE :texto OR
                    LOWER(CAST(e.fecIniVigencia AS string)) LIKE :texto OR
                    LOWER(CAST(e.fecFinVigencia AS string)) LIKE :texto)
                AND (:activo IS NULL OR e.blnActivo = :activo)
                AND (:idTipoTramite IS NULL OR t.id = :idTipoTramite)
            """,
            countQuery = """
                SELECT COUNT(e.idClasifProduct)
                FROM CatClasifProducto e
                LEFT JOIN e.idTipoTramite t
                LEFT JOIN e.idClasifProductoR r
                WHERE (:texto IS NULL OR
                    LOWER(CAST(e.idClasifProduct AS string)) LIKE :texto OR
                    LOWER(CAST(t.id AS string)) LIKE :texto OR
                    LOWER(t.descModalidad) LIKE :texto OR
                    LOWER(CAST(r.idClasifProduct AS string)) LIKE :texto OR
                    LOWER(r.nombre) LIKE :texto OR
                    LOWER(e.nombre) LIKE :texto OR
                    LOWER(e.ideTipoClasifProducto) LIKE :texto OR
                    LOWER(CAST(e.fecIniVigencia AS string)) LIKE :texto OR
                    LOWER(CAST(e.fecFinVigencia AS string)) LIKE :texto)
                AND (:activo IS NULL OR e.blnActivo = :activo)
                AND (:idTipoTramite IS NULL OR t.id = :idTipoTramite)
            """)
    Page<CatClasifProductoDTO> search(@Param("texto") String texto,
                                      @Param("activo") Boolean activo,
                                      @Param("idTipoTramite") Long idTipoTramite,
                                      Pageable pageable);

    @Query("""
            SELECT new com.example.vucem_catalogos_service.model.dto.CatClasifProductoDTO(
                 e.idClasifProduct,
                CASE WHEN e.idTipoTramite IS NOT NULL THEN e.idTipoTramite.id ELSE NULL END,
                CASE WHEN e.idTipoTramite IS NOT NULL THEN e.idTipoTramite.descModalidad ELSE NULL END,
                CASE WHEN e.idClasifProductoR IS NOT NULL THEN e.idClasifProductoR.idClasifProduct ELSE NULL END,
                e.idClasifProductoR.nombre,
                e.nombre,
                e.ideTipoClasifProducto,
                e.fecIniVigencia,
                e.fecFinVigencia,
                e.blnActivo
            )
            FROM CatClasifProducto e
            WHERE e.idClasifProduct = :id
            """)
    Optional<CatClasifProductoDTO> findByClasifProductoDTO(@Param("id") Long id);

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
    List<ClasifProductoTraDTO> findTipoTramiteListado();


    @Query("""
            SELECT new com.example.vucem_catalogos_service.model.dto.ClasifProductoTraDTO(
                cp.idClasifProduct,
                cp.nombre
            )
            FROM CatClasifProducto cp
            JOIN cp.idTipoTramite t
            WHERE t.blnActivo = true
              AND t.cveServicio = '26'
              AND t.id = :idTipoTramite
            ORDER BY cp.idClasifProductoR.nombre ASC
            """)
    List<ClasifProductoTraDTO> listadoClasifPrR(
            @Param("idTipoTramite") Long idTipoTramite);

    Optional<CatClasifProducto> findTopByOrderByIdClasifProductDesc();
}
