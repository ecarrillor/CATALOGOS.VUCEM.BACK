package com.example.vucem_catalogos_service.persistence.repo;

import com.example.vucem_catalogos_service.model.dto.CatEmpresaRecifDTO;
import com.example.vucem_catalogos_service.model.entity.CatEmpresaRecif;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ICatEmpresaRecifRepository extends JpaRepository<CatEmpresaRecif, String> {

    @Query("""
            SELECT new com.example.vucem_catalogos_service.model.dto.CatEmpresaRecifDTO(
                e.recif,
                e.rfc,
                e.razonSocial,
                e.blnActivo,
                e.fecIniVigencia,
                e.fecFinVigencia,
                ua.cveUnidadAdministrativa,
                ua.nombre
            )
            FROM CatEmpresaRecif e
            LEFT JOIN e.cveUnidadAdministrativa ua
             WHERE
                (:search IS NULL OR
                    LOWER(e.recif) LIKE LOWER(CONCAT('%', CAST(:search AS string), '%')) OR
                    LOWER(ua.nombre) LIKE LOWER(CONCAT('%', CAST(:search AS string), '%')) OR
                    LOWER(e.razonSocial) LIKE LOWER(CONCAT('%', CAST(:search AS string), '%'))
                )
                AND (
                    :activo IS NULL OR e.blnActivo = :activo
                )
            """)
    Page<CatEmpresaRecifDTO> search(@Param("search") String search,
                                     @Param("activo") Boolean activo,
                                     Pageable pageable);

    @Query("""
            SELECT new com.example.vucem_catalogos_service.model.dto.CatEmpresaRecifDTO(
                e.recif,
                e.rfc,
                e.razonSocial,
                e.blnActivo,
                e.fecIniVigencia,
                e.fecFinVigencia,
                ua.cveUnidadAdministrativa,
                ua.nombre
            )
            FROM CatEmpresaRecif e
            LEFT JOIN e.cveUnidadAdministrativa ua
            WHERE e.recif = :recif
            """)
    CatEmpresaRecifDTO findByEmpresaRecifDTO(@Param("recif") String recif);
}
