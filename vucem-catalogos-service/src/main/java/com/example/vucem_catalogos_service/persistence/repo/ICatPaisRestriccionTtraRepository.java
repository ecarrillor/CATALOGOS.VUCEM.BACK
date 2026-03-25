package com.example.vucem_catalogos_service.persistence.repo;

import com.example.vucem_catalogos_service.model.dto.CatPaisRestriccionTtraDTO;
import com.example.vucem_catalogos_service.model.entity.CatPaisRestriccionTtra;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ICatPaisRestriccionTtraRepository extends JpaRepository<CatPaisRestriccionTtra, Integer> {

    @Query(value = """
            SELECT new com.example.vucem_catalogos_service.model.dto.CatPaisRestriccionTtraDTO(
                e.id,
                tt.id,
                tt.descModalidad,
                p.cvePais,
                p.nombre,
                e.ideTipoRestriccionPaisTtra,
                e.fecIniVigencia,
                e.fecFinVigencia,
                e.blnActivo
            )
            FROM CatPaisRestriccionTtra e
            LEFT JOIN e.idTipoTramite tt
            LEFT JOIN e.cvePais p
            WHERE (:search IS NULL OR
                LOWER(CAST(e.id AS string)) LIKE :search OR
                LOWER(CAST(tt.id AS string)) LIKE :search OR
                LOWER(tt.descModalidad) LIKE :search OR
                LOWER(p.cvePais) LIKE :search OR
                LOWER(p.nombre) LIKE :search OR
                LOWER(e.ideTipoRestriccionPaisTtra) LIKE :search OR
                LOWER(CAST(e.fecIniVigencia AS string)) LIKE :search OR
                LOWER(CAST(e.fecFinVigencia AS string)) LIKE :search
            )
            AND (:activo IS NULL OR e.blnActivo = :activo)
            """,
            countQuery = """
            SELECT COUNT(e)
            FROM CatPaisRestriccionTtra e
            LEFT JOIN e.idTipoTramite tt
            LEFT JOIN e.cvePais p
            WHERE (:search IS NULL OR
                LOWER(CAST(e.id AS string)) LIKE :search OR
                LOWER(CAST(tt.id AS string)) LIKE :search OR
                LOWER(tt.descModalidad) LIKE :search OR
                LOWER(p.cvePais) LIKE :search OR
                LOWER(p.nombre) LIKE :search OR
                LOWER(e.ideTipoRestriccionPaisTtra) LIKE :search OR
                LOWER(CAST(e.fecIniVigencia AS string)) LIKE :search OR
                LOWER(CAST(e.fecFinVigencia AS string)) LIKE :search
            )
            AND (:activo IS NULL OR e.blnActivo = :activo)
            """)
    Page<CatPaisRestriccionTtraDTO> search(@Param("search") String search,
                                           @Param("activo") Boolean activo,
                                           Pageable pageable);

    @Query("""
            SELECT new com.example.vucem_catalogos_service.model.dto.CatPaisRestriccionTtraDTO(
                e.id,
                tt.id,
                tt.descModalidad,
                p.cvePais,
                p.nombre,
                e.ideTipoRestriccionPaisTtra,
                e.fecIniVigencia,
                e.fecFinVigencia,
                e.blnActivo
            )
            FROM CatPaisRestriccionTtra e
            LEFT JOIN e.idTipoTramite tt
            LEFT JOIN e.cvePais p
            WHERE e.id = :id
            """)
    CatPaisRestriccionTtraDTO findByPaisRestriccionTtraDTO(@Param("id") Integer id);
}
