package com.example.vucem_catalogos_service.persistence.repo;

import com.example.vucem_catalogos_service.model.dto.CatFundamentoTtraDTO;
import com.example.vucem_catalogos_service.model.entity.CatFundamentoTtra;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ICatFundamentoTtraRepository extends JpaRepository<CatFundamentoTtra, Short> {

    @Query(value = """
            SELECT new com.example.vucem_catalogos_service.model.dto.CatFundamentoTtraDTO(
                e.id,
                CASE WHEN e.idTipoTramite IS NOT NULL THEN e.idTipoTramite.id ELSE NULL END,
                CASE WHEN e.idTipoTramite IS NOT NULL THEN e.idTipoTramite.descSubservicio ELSE NULL END,
                e.ideTipoFundamentoTtra,
                e.descFundamento,
                e.descContenidoFundamento,
                e.fecIniVigencia,
                e.fecFinVigencia,
                e.blnActivo
            )
            FROM CatFundamentoTtra e
            WHERE (:search IS NULL OR
                LOWER(CAST(e.id AS string)) LIKE :search OR
                LOWER(CAST(e.idTipoTramite.id AS string)) LIKE :search OR
                LOWER(e.idTipoTramite.descSubservicio) LIKE :search OR
                LOWER(e.ideTipoFundamentoTtra) LIKE :search OR
                LOWER(e.descFundamento) LIKE :search OR
                LOWER(e.descContenidoFundamento) LIKE :search OR
                LOWER(CAST(e.fecIniVigencia AS string)) LIKE :search OR
                LOWER(CAST(e.fecFinVigencia AS string)) LIKE :search)
            AND (:activo IS NULL OR e.blnActivo = :activo)
            """,
            countQuery = """
            SELECT COUNT(e)
            FROM CatFundamentoTtra e
            WHERE (:search IS NULL OR
                LOWER(CAST(e.id AS string)) LIKE :search OR
                LOWER(CAST(e.idTipoTramite.id AS string)) LIKE :search OR
                LOWER(e.idTipoTramite.descSubservicio) LIKE :search OR
                LOWER(e.ideTipoFundamentoTtra) LIKE :search OR
                LOWER(e.descFundamento) LIKE :search OR
                LOWER(e.descContenidoFundamento) LIKE :search OR
                LOWER(CAST(e.fecIniVigencia AS string)) LIKE :search OR
                LOWER(CAST(e.fecFinVigencia AS string)) LIKE :search)
            AND (:activo IS NULL OR e.blnActivo = :activo)
            """)
    Page<CatFundamentoTtraDTO> search(@Param("search") String search,
                                      @Param("activo") Boolean activo,
                                      Pageable pageable);

    @Query("""
            SELECT new com.example.vucem_catalogos_service.model.dto.CatFundamentoTtraDTO(
                e.id,
                CASE WHEN e.idTipoTramite IS NOT NULL THEN e.idTipoTramite.id ELSE NULL END,
                CASE WHEN e.idTipoTramite IS NOT NULL THEN e.idTipoTramite.descSubservicio ELSE NULL END,
                e.ideTipoFundamentoTtra,
                e.descFundamento,
                e.descContenidoFundamento,
                e.fecIniVigencia,
                e.fecFinVigencia,
                e.blnActivo
            )
            FROM CatFundamentoTtra e
            WHERE e.id = :id
            """)
    Optional<CatFundamentoTtraDTO> findByFundamentoTtraDTO(@Param("id") Short id);
}
