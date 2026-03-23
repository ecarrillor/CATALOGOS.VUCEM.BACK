package com.example.vucem_catalogos_service.persistence.repo;

import com.example.vucem_catalogos_service.model.dto.ClasifProductoTraDTO;
import com.example.vucem_catalogos_service.model.dto.VucRepoFirmaGralSe.VucRepoFirmaGralSeResponseDTO;
import com.example.vucem_catalogos_service.model.entity.VucRepoFirmaGralSe;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IVucRepoFirmaGralSeRepository extends JpaRepository<VucRepoFirmaGralSe, Integer> {


    @Query(value = """
            SELECT DISTINCT new com.example.vucem_catalogos_service.model.dto.VucRepoFirmaGralSe.VucRepoFirmaGralSeResponseDTO(
                r.id,
                r.ideTipoFirma,
                t.id,
                COALESCE(t.descModalidad, t.descSubservicio),
                r.rfc,
                r.puesto,
                r.fecIniVigenia,
                r.fecFinVigenia,
                r.blnActivo,
                CASE WHEN r.cert     IS NOT NULL THEN TRUE ELSE FALSE END,
                CASE WHEN r.key      IS NOT NULL THEN TRUE ELSE FALSE END
            
            )
            FROM VucRepoFirmaGralSe r
            LEFT JOIN r.idTipoTramite t
            WHERE (
                        :search IS NULL OR
                  CAST(r.id AS string) LIKE :search OR
                  CAST(t.id AS string) LIKE :search OR
                  LOWER(r.ideTipoFirma) LIKE :search OR
                  LOWER(r.rfc) LIKE :search OR
                  LOWER(r.puesto) LIKE :search OR
                  CAST(r.fecIniVigenia AS string) LIKE :search OR
                  CAST(r.fecFinVigenia AS string) LIKE :search
                  )
              AND (:tipoFirma IS NULL OR r.ideTipoFirma = :tipoFirma)
              AND (:activo    IS NULL OR r.blnActivo = :activo)
            """,
            countQuery = """
            SELECT COUNT(DISTINCT r.id)
            FROM VucRepoFirmaGralSe r
            LEFT JOIN r.idTipoTramite t
            WHERE (
                        :search IS NULL OR
                  CAST(r.id AS string) LIKE :search OR
                  CAST(t.id AS string) LIKE :search OR
                  LOWER(r.ideTipoFirma) LIKE :search OR
                  LOWER(r.rfc) LIKE :search OR
                  LOWER(r.puesto) LIKE :search OR
                  CAST(r.fecIniVigenia AS string) LIKE :search OR
                  CAST(r.fecFinVigenia AS string) LIKE :search
                  )
              AND (:tipoFirma IS NULL OR r.ideTipoFirma = :tipoFirma)
              AND (:activo    IS NULL OR r.blnActivo = :activo)
            """)
    Page<VucRepoFirmaGralSeResponseDTO> search(
            @Param("search") String search,
            @Param("tipoFirma") String tipoFirma,
            @Param("activo") Boolean activo,
            Pageable pageable);

    /**
     * Busca registros que coincidan con ideTipoFirma + idTipoTramite + blnActivo.
     * Usado en validación de unicidad.
     */
    @Query("""
            SELECT r FROM VucRepoFirmaGralSe r
            WHERE r.ideTipoFirma            = :tipoFirma
              AND r.idTipoTramite.id        = :idTipoTramite
              AND r.blnActivo               = :activo
            """)
    List<VucRepoFirmaGralSe> findByTipoFirmaAndTipoTramiteAndActivo(
            @Param("tipoFirma") String tipoFirma,
            @Param("idTipoTramite") Long idTipoTramite,
            @Param("activo") Boolean activo);

    /**
     * Tipos de firma desde cat_enumeracion_d_tr (grupo ENU_TIPO_FIRMA_REPOSITORIO).
     */
    @Query(value = """
        SELECT tr.cve_enumeracion AS clave,
               tr.descripcion     AS descripcion
        FROM cat_enumeracion_h h
        JOIN cat_enumeracion_d d
              ON h.cve_enumeracion_h = d.cve_enumeracion_h
        JOIN cat_enumeracion_d_tr tr
              ON tr.cve_enumeracion = d.cve_enumeracion
        WHERE h.cve_enumeracion_h = 'ENU_TIPO_FIRMA_REPOSITORIO'
        ORDER BY tr.descripcion ASC
        """, nativeQuery = true)
    List<ComboProyeccion> obtenerTiposFirma();

    /**
     * Tipos de trámite activos para el combo del formulario.
     * Filtra servicios SE (cveServicio 23 y 26).
     */
    @Query("""
            SELECT DISTINCT new com.example.vucem_catalogos_service.model.dto.ClasifProductoTraDTO(
                t.id,
                CONCAT(CAST(t.id AS string), '  ', COALESCE(t.descModalidad, t.descSubservicio))
            )
            FROM CatTipoTramite t
            WHERE t.blnActivo = true
              AND t.cveServicio IN ('23', '26')
            ORDER BY 2 ASC
            """)
    List<ClasifProductoTraDTO> obtenerTiposTramite();

    @Query("SELECT MAX(r.id) FROM VucRepoFirmaGralSe r")
    Integer findMaxId();

    /** Proyección para la native query de tipos de firma */
    interface ComboProyeccion {
        String getClave();
        String getDescripcion();
    }
}
