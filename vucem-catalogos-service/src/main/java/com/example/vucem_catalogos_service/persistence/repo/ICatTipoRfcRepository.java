package com.example.vucem_catalogos_service.persistence.repo;

import com.example.vucem_catalogos_service.model.dto.CatTipoRfcDTO;
import com.example.vucem_catalogos_service.model.entity.CatTipoRfc;
import com.example.vucem_catalogos_service.model.entity.CatTipoRfcId;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ICatTipoRfcRepository extends JpaRepository<CatTipoRfc, CatTipoRfcId> {

    @Query(value = """
        SELECT new com.example.vucem_catalogos_service.model.dto.CatTipoRfcDTO(
            e.id.rfc,
            e.id.ideTipoRfc,
            e.razonSocial,
            e.fecFinVigencia,
            e.fecIniVigencia,
            e.direccion,
            e.telefono,
            e.clave,
            e.blnActivo,
            e.correoElectronico,
            e.fax,
            e.blnLabAcreditado,
            ua.cveUnidadAdministrativa,
            ua.nombre
        )
        FROM CatTipoRfc e
        LEFT JOIN e.cveUnidadAdministrativa ua
        WHERE (:search IS NULL OR
            LOWER(e.id.rfc) LIKE :search OR
            LOWER(e.id.ideTipoRfc) LIKE :search OR
            LOWER(e.razonSocial) LIKE :search OR
            LOWER(CAST(e.fecFinVigencia AS string)) LIKE :search OR
            LOWER(CAST(e.fecIniVigencia AS string)) LIKE :search OR
            LOWER(e.direccion) LIKE :search OR
            LOWER(e.telefono) LIKE :search OR
            LOWER(e.clave) LIKE :search OR
            LOWER(e.correoElectronico) LIKE :search OR
            LOWER(e.fax) LIKE :search OR
            LOWER(ua.cveUnidadAdministrativa) LIKE :search OR
            LOWER(ua.nombre) LIKE :search)
        AND (:activo IS NULL OR e.blnActivo = :activo) """,
            countQuery = """
            SELECT COUNT(e)
        FROM CatTipoRfc e
        LEFT JOIN e.cveUnidadAdministrativa ua
        WHERE (:search IS NULL OR
            LOWER(e.id.rfc) LIKE :search OR
            LOWER(e.id.ideTipoRfc) LIKE :search OR
            LOWER(e.razonSocial) LIKE :search OR
            LOWER(CAST(e.fecFinVigencia AS string)) LIKE :search OR
            LOWER(CAST(e.fecIniVigencia AS string)) LIKE :search OR
            LOWER(e.direccion) LIKE :search OR
            LOWER(e.telefono) LIKE :search OR
            LOWER(e.clave) LIKE :search OR
            LOWER(e.correoElectronico) LIKE :search OR
            LOWER(e.fax) LIKE :search OR
            LOWER(ua.cveUnidadAdministrativa) LIKE :search OR
            LOWER(ua.nombre) LIKE :search)
        AND (:activo IS NULL OR e.blnActivo = :activo) """)
    Page<CatTipoRfcDTO> search(@Param("search") String search,
                               @Param("activo") Boolean activo,
                               Pageable pageable);

    @Query("""
            SELECT new com.example.vucem_catalogos_service.model.dto.CatTipoRfcDTO(
            e.id.rfc,
            e.id.ideTipoRfc,
            e.razonSocial,
            e.fecFinVigencia,
            e.fecIniVigencia,
            e.direccion,
            e.telefono,
            e.clave,
            e.blnActivo,
            e.correoElectronico,
            e.fax,
            e.blnLabAcreditado,
            ua.cveUnidadAdministrativa,
            ua.nombre
            )
            FROM CatTipoRfc e
            LEFT JOIN e.cveUnidadAdministrativa ua
            WHERE e.id.rfc = :rfc and e.id.ideTipoRfc = :ideTipoRfc
            """)
    Optional<CatTipoRfcDTO> findByTipoRfcDTO(@Param("rfc") String rfc,
                                               @Param("ideTipoRfc") String ideTipoRfc);
}
