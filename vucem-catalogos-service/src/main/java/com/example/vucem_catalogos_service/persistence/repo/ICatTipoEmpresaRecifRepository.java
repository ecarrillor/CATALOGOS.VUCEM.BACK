package com.example.vucem_catalogos_service.persistence.repo;

import com.example.vucem_catalogos_service.model.dto.CatTipoEmpresaRecifDTO;
import com.example.vucem_catalogos_service.model.entity.CatTipoEmpresaRecif;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ICatTipoEmpresaRecifRepository extends JpaRepository<CatTipoEmpresaRecif, String> {

    List<CatTipoEmpresaRecif> findByBlnActivoTrue();

    @Query("""
            SELECT new com.example.vucem_catalogos_service.model.dto.CatTipoEmpresaRecifDTO(
                e.cveTipoEmpresaRecif,
                CASE WHEN e.cveTipoEmpresaRecifR IS NOT NULL THEN e.cveTipoEmpresaRecifR.cveTipoEmpresaRecif ELSE NULL END,
                CASE WHEN e.cveTipoEmpresaRecifR IS NOT NULL THEN e.cveTipoEmpresaRecifR.descripcion ELSE NULL END,
                e.descripcion,
                e.fecIniVigencia,
                e.fecFinVigencia,
                e.blnActivo
            )
            FROM CatTipoEmpresaRecif e
            WHERE (:search IS NULL OR
                LOWER(e.cveTipoEmpresaRecif) LIKE LOWER(CONCAT('%', CAST(:search AS string), '%')) OR
                LOWER(CAST(e.cveTipoEmpresaRecifR.cveTipoEmpresaRecif AS string)) LIKE LOWER(CONCAT('%', CAST(:search AS string), '%')) OR
                LOWER(e.cveTipoEmpresaRecifR.descripcion) LIKE LOWER(CONCAT('%', CAST(:search AS string), '%')) OR
                LOWER(e.descripcion) LIKE LOWER(CONCAT('%', CAST(:search AS string), '%')) OR
                LOWER(CAST(e.fecIniVigencia AS string)) LIKE LOWER(CONCAT('%', CAST(:search AS string), '%')) OR
                LOWER(CAST(e.fecFinVigencia AS string)) LIKE LOWER(CONCAT('%', CAST(:search AS string), '%')))
              AND (:activo IS NULL OR e.blnActivo = :activo)
            """)
    Page<CatTipoEmpresaRecifDTO> search(
            @Param("search") String search,
            @Param("activo") Boolean activo,
            Pageable pageable
    );

    @Query("""
            SELECT new com.example.vucem_catalogos_service.model.dto.CatTipoEmpresaRecifDTO(
                e.cveTipoEmpresaRecif,
                CASE WHEN e.cveTipoEmpresaRecifR IS NOT NULL THEN e.cveTipoEmpresaRecifR.cveTipoEmpresaRecif ELSE NULL END,
                CASE WHEN e.cveTipoEmpresaRecifR IS NOT NULL THEN e.cveTipoEmpresaRecifR.descripcion ELSE NULL END,
                e.descripcion,
                e.fecIniVigencia,
                e.fecFinVigencia,
                e.blnActivo
            )
            FROM CatTipoEmpresaRecif e
            WHERE e.cveTipoEmpresaRecif = :cveTipoEmpresaRecif
            """)
    Optional<CatTipoEmpresaRecifDTO> findByTipoEmpresaRecifDTO(@Param("cveTipoEmpresaRecif") String cveTipoEmpresaRecif);
}
