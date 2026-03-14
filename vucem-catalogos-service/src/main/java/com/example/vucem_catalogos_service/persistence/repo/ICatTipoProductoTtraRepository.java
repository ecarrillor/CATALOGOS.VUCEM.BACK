package com.example.vucem_catalogos_service.persistence.repo;

import com.example.vucem_catalogos_service.model.dto.CatTipoProductoTtraDTO;
import com.example.vucem_catalogos_service.model.entity.CatTipoProductoTtra;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface ICatTipoProductoTtraRepository extends JpaRepository<CatTipoProductoTtra, Short> {

    @Query("""
            SELECT new com.example.vucem_catalogos_service.model.dto.CatTipoProductoTtraDTO(
                e.id,
                t.id,
                t.descSubservicio,
                e.descTipoProducto,
                e.fecIniVigencia,
                e.fecFinVigencia,
                e.blnActivo,
                e.ideTipoCertificadoMerc
            )
            FROM CatTipoProductoTtra e
            JOIN e.idTipoTramite t
            WHERE (:search IS NULL OR
                LOWER(CAST(e.id AS string)) LIKE LOWER(CONCAT('%', CAST(:search AS string), '%')) OR
                LOWER(CAST(t.id AS string)) LIKE LOWER(CONCAT('%', CAST(:search AS string), '%')) OR
                LOWER(t.descSubservicio) LIKE LOWER(CONCAT('%', CAST(:search AS string), '%')) OR
                LOWER(e.descTipoProducto) LIKE LOWER(CONCAT('%', CAST(:search AS string), '%')) OR
                LOWER(CAST(e.fecIniVigencia AS string)) LIKE LOWER(CONCAT('%', CAST(:search AS string), '%')) OR
                LOWER(CAST(e.fecFinVigencia AS string)) LIKE LOWER(CONCAT('%', CAST(:search AS string), '%')) OR
                LOWER(e.ideTipoCertificadoMerc) LIKE LOWER(CONCAT('%', CAST(:search AS string), '%')))
              AND (:activo IS NULL OR e.blnActivo = :activo)
            """)
    Page<CatTipoProductoTtraDTO> search(
            @Param("search") String search,
            @Param("activo") Boolean activo,
            Pageable pageable
    );

    @Query("""
            SELECT new com.example.vucem_catalogos_service.model.dto.CatTipoProductoTtraDTO(
                e.id,
                t.id,
                t.descSubservicio,
                e.descTipoProducto,
                e.fecIniVigencia,
                e.fecFinVigencia,
                e.blnActivo,
                e.ideTipoCertificadoMerc
            )
            FROM CatTipoProductoTtra e
            JOIN e.idTipoTramite t
            WHERE e.id = :id
            """)
    Optional<CatTipoProductoTtraDTO> findByTipoProductoTtraDTO(@Param("id") Short id);
}
