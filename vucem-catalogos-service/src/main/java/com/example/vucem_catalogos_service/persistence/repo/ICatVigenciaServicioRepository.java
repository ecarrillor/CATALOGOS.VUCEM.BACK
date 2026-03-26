package com.example.vucem_catalogos_service.persistence.repo;

import com.example.vucem_catalogos_service.model.dto.CatVigenciaServicioDTO;
import com.example.vucem_catalogos_service.model.entity.CatVigenciaServicio;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ICatVigenciaServicioRepository extends JpaRepository<CatVigenciaServicio, Short> {

    @Query(value = """
    SELECT new com.example.vucem_catalogos_service.model.dto.CatVigenciaServicioDTO(
        e.id,
        e.numVigencia,
        e.ideTipoVigencia,
        e.ideTipoServicioCeror,
        cat.cvePais.cvePais,
        cat.cvePais.nombre,
        cat.idTratadoAcuerdo.id,
        b.id,
        b.nombre,
        e.cveCriterioOrigen.cveCriterioOrigen,
        e.fecIniVigencia,
        e.blnActivo
    )
    FROM CatVigenciaServicio e
    LEFT JOIN e.idBloque b
    LEFT JOIN e.catPaisTratadoAcuerdo cat
    WHERE (:search IS NULL OR
        LOWER(CAST(e.id AS string)) LIKE :search OR
        LOWER(e.numVigencia) LIKE :search OR
        LOWER(e.ideTipoVigencia) LIKE :search OR
        LOWER(e.ideTipoServicioCeror) LIKE :search OR
        LOWER(cat.cvePais.cvePais) LIKE :search OR
        LOWER(cat.cvePais.nombre) LIKE :search OR
        LOWER(CAST(cat.idTratadoAcuerdo.id AS string)) LIKE :search OR
        LOWER(CAST(b.id AS string)) LIKE :search OR
        LOWER(b.nombre) LIKE :search OR
        LOWER(e.cveCriterioOrigen.cveCriterioOrigen) LIKE :search OR
        LOWER(CAST(e.fecIniVigencia AS string)) LIKE :search)
    AND (:activo IS NULL OR e.blnActivo = :activo)
    """,
            countQuery = """
    SELECT COUNT(e)
    FROM CatVigenciaServicio e
    LEFT JOIN e.idBloque b
    LEFT JOIN e.catPaisTratadoAcuerdo cat
    WHERE (:search IS NULL OR
        LOWER(CAST(e.id AS string)) LIKE :search OR
        LOWER(e.numVigencia) LIKE :search OR
        LOWER(e.ideTipoVigencia) LIKE :search OR
        LOWER(e.ideTipoServicioCeror) LIKE :search OR
        LOWER(cat.cvePais.cvePais) LIKE :search OR
        LOWER(cat.cvePais.nombre) LIKE :search OR
        LOWER(CAST(cat.idTratadoAcuerdo.id AS string)) LIKE :search OR
        LOWER(CAST(b.id AS string)) LIKE :search OR
        LOWER(b.nombre) LIKE :search OR
        LOWER(e.cveCriterioOrigen.cveCriterioOrigen) LIKE :search OR
        LOWER(CAST(e.fecIniVigencia AS string)) LIKE :search)
    AND (:activo IS NULL OR e.blnActivo = :activo)
    """)
    Page<CatVigenciaServicioDTO> search(@Param("search") String search,
                                        @Param("activo") Boolean activo,
                                        Pageable pageable);

    @Query("""
            SELECT new com.example.vucem_catalogos_service.model.dto.CatVigenciaServicioDTO(
                e.id,
                                              e.numVigencia,
                                              e.ideTipoVigencia,
                                              e.ideTipoServicioCeror,
                                              c.cvePais,
                                              c.nombre,
                                              t.id,
                                              b.id,
                                              b.nombre,
                                              e.cveCriterioOrigen.cveCriterioOrigen,
                                              e.fecIniVigencia,
                                              e.blnActivo
                                          )
                                          FROM CatVigenciaServicio e
                                          LEFT JOIN e.catPaisTratadoAcuerdo cpa
                                          LEFT JOIN cpa.cvePais c
                                          LEFT JOIN cpa.idTratadoAcuerdo t
                                          LEFT JOIN e.idBloque b
            WHERE e.id = :id
            """)
    Optional<CatVigenciaServicioDTO> findByVigenciaServicioDTO(@Param("id") Short id);
}
