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

    @Query("""
            SELECT new com.example.vucem_catalogos_service.model.dto.CatPaisRestriccionTtraDTO(
                e.id,
                tt.id,
                tt.nombre,
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
                LOWER(e.ideTipoRestriccionPaisTtra) LIKE LOWER(CONCAT('%', CAST(:search AS string), '%')) OR
                LOWER(p.nombre)                     LIKE LOWER(CONCAT('%', CAST(:search AS string), '%')) OR
                LOWER(tt.nombre)                    LIKE LOWER(CONCAT('%', CAST(:search AS string), '%'))
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
                tt.nombre,
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
