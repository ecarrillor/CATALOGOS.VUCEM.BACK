package com.example.vucem_catalogos_service.persistence.repo;

import com.example.vucem_catalogos_service.model.dto.CatAduanaClasifProdDTO;
import com.example.vucem_catalogos_service.model.entity.CatAduanaClasifProd;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ICatAduanaClasifProdRepository extends JpaRepository<CatAduanaClasifProd, Long> {

    @Query("""
            SELECT new com.example.vucem_catalogos_service.model.dto.CatAduanaClasifProdDTO(
                e.id,
                CASE WHEN e.aduana IS NOT NULL THEN e.aduana.cveAduana ELSE NULL END,
                CASE WHEN e.aduana IS NOT NULL THEN e.aduana.nombre ELSE NULL END,
                CASE WHEN e.idClasifProducto IS NOT NULL THEN e.idClasifProducto.idClasifProduct ELSE NULL END,
                CASE WHEN e.idClasifProducto IS NOT NULL THEN e.idClasifProducto.nombre ELSE NULL END,
                e.fecIniVigencia,
                e.fecFinVigencia,
                e.blnActivo
            )
            FROM CatAduanaClasifProd e
            WHERE (:texto IS NULL OR
                (e.aduana IS NOT NULL AND LOWER(e.aduana.nombre) LIKE LOWER(CONCAT('%', CAST(:texto AS string), '%'))) OR
                LOWER(CAST(e.id AS string)) LIKE LOWER(CONCAT('%', CAST(:texto AS string), '%')) OR
                (e.aduana IS NOT NULL AND LOWER(e.aduana.cveAduana) LIKE LOWER(CONCAT('%', CAST(:texto AS string), '%'))) OR
                (e.idClasifProducto IS NOT NULL AND LOWER(CAST(e.idClasifProducto.idClasifProduct AS string)) LIKE LOWER(CONCAT('%', CAST(:texto AS string), '%'))) OR
                (e.idClasifProducto IS NOT NULL AND LOWER(e.idClasifProducto.nombre) LIKE LOWER(CONCAT('%', CAST(:texto AS string), '%'))) OR
                LOWER(CAST(e.fecIniVigencia AS string)) LIKE LOWER(CONCAT('%', CAST(:texto AS string), '%')) OR
                LOWER(CAST(e.fecFinVigencia AS string)) LIKE LOWER(CONCAT('%', CAST(:texto AS string), '%')))
            AND (:activo IS NULL OR e.blnActivo = :activo)
            AND (:idClasifProducto IS NULL OR (e.idClasifProducto IS NOT NULL AND e.idClasifProducto.idClasifProduct = :idClasifProducto))
            """)
    Page<CatAduanaClasifProdDTO> search(@Param("texto") String texto,
                                        @Param("activo") Boolean activo,
                                        @Param("idClasifProducto") Long idClasifProducto,
                                        Pageable pageable);

    @Query("""
            SELECT new com.example.vucem_catalogos_service.model.dto.CatAduanaClasifProdDTO(
                e.id,
                CASE WHEN e.aduana IS NOT NULL THEN e.aduana.cveAduana ELSE NULL END,
                CASE WHEN e.aduana IS NOT NULL THEN e.aduana.nombre ELSE NULL END,
                CASE WHEN e.idClasifProducto IS NOT NULL THEN e.idClasifProducto.idClasifProduct ELSE NULL END,
                CASE WHEN e.idClasifProducto IS NOT NULL THEN e.idClasifProducto.nombre ELSE NULL END,
                e.fecIniVigencia,
                e.fecFinVigencia,
                e.blnActivo
            )
            FROM CatAduanaClasifProd e
            WHERE e.id = :id
            """)
    Optional<CatAduanaClasifProdDTO> findByAduanaClasifProdDTO(@Param("id") Long id);

    @Query(value = """
            SELECT DISTINCT tt.id_tipo_tramite,
                   tt.id_tipo_tramite || ' ' || COALESCE(tt.desc_modalidad, tt.desc_subservicio) AS descripcion
            FROM cat_tipo_tramite tt
            INNER JOIN cat_clasif_producto cp ON cp.id_tipo_tramite = tt.id_tipo_tramite
            INNER JOIN cat_aduana_clasif_prod acp ON acp.id_clasif_producto = cp.id_clasif_producto
            INNER JOIN cat_aduana aduana ON aduana.cve_aduana = acp.cve_aduana
            WHERE tt.id_tipo_tramite IN (
                '260301','260302','260303','260304',
                '260603','260604',
                '261101','261102','261103','261104'
            )
            AND tt.bln_activo = TRUE
            ORDER BY UPPER(TRIM(tt.id_tipo_tramite || ' ' || COALESCE(tt.desc_modalidad, tt.desc_subservicio))) ASC
            """, nativeQuery = true)
    List<Object[]> findTipoTramiteListado();
}
