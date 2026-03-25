package com.example.vucem_catalogos_service.persistence.repo;

import com.example.vucem_catalogos_service.model.dto.CatActividadEconomicaSatDTO;
import com.example.vucem_catalogos_service.model.entity.CatActividadEconomicaSat;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ICatActividadEconomicaSatRepository extends JpaRepository<CatActividadEconomicaSat, Long> {

    @Query(value = """
            SELECT new com.example.vucem_catalogos_service.model.dto.CatActividadEconomicaSatDTO(
                e.id,
                e.idActividadEconomicaR.id,
                e.idActividadEconomicaR.descripcion,
                e.descripcion,
                e.descScian,
                e.descNotas,
                c.descripcion,
                c.cveTipoEmpresaRecif,
                e.fecIniVigencia,
                e.fecFinVigencia,
                e.fecCaptura,
                e.fecActualizacion,
                e.cveTipoIndustriaIdc,
                e.blnActivo
            )
            FROM CatActividadEconomicaSat e
            LEFT JOIN e.cveTipoEmpresaRecif c
            WHERE (:search IS NULL OR
                LOWER(CAST(e.id AS string)) LIKE :search OR
                (e.idActividadEconomicaR IS NOT NULL AND LOWER(CAST(e.idActividadEconomicaR.id AS string)) LIKE :search) OR
                (e.idActividadEconomicaR IS NOT NULL AND LOWER(e.idActividadEconomicaR.descripcion) LIKE :search) OR
                LOWER(e.descripcion) LIKE :search OR
                LOWER(e.descScian) LIKE :search OR
                LOWER(e.descNotas) LIKE :search OR
                LOWER(c.descripcion) LIKE :search OR
                LOWER(c.cveTipoEmpresaRecif) LIKE :search OR
                LOWER(CAST(e.fecIniVigencia AS string)) LIKE :search OR
                LOWER(CAST(e.fecFinVigencia AS string)) LIKE :search OR
                LOWER(CAST(e.fecCaptura AS string)) LIKE :search OR
                LOWER(CAST(e.fecActualizacion AS string)) LIKE :search OR
                LOWER(e.cveTipoIndustriaIdc) LIKE :search)
            AND (:activo IS NULL OR e.blnActivo = :activo)
            """,
            countQuery = """
            SELECT COUNT(e)
            FROM CatActividadEconomicaSat e
            LEFT JOIN e.cveTipoEmpresaRecif c
            WHERE (:search IS NULL OR
                LOWER(CAST(e.id AS string)) LIKE :search OR
                (e.idActividadEconomicaR IS NOT NULL AND LOWER(CAST(e.idActividadEconomicaR.id AS string)) LIKE :search) OR
                (e.idActividadEconomicaR IS NOT NULL AND LOWER(e.idActividadEconomicaR.descripcion) LIKE :search) OR
                LOWER(e.descripcion) LIKE :search OR
                LOWER(e.descScian) LIKE :search OR
                LOWER(e.descNotas) LIKE :search OR
                LOWER(c.descripcion) LIKE :search OR
                LOWER(c.cveTipoEmpresaRecif) LIKE :search OR
                LOWER(CAST(e.fecIniVigencia AS string)) LIKE :search OR
                LOWER(CAST(e.fecFinVigencia AS string)) LIKE :search OR
                LOWER(CAST(e.fecCaptura AS string)) LIKE :search OR
                LOWER(CAST(e.fecActualizacion AS string)) LIKE :search OR
                LOWER(e.cveTipoIndustriaIdc) LIKE :search)
            AND (:activo IS NULL OR e.blnActivo = :activo)
            """)
    Page<CatActividadEconomicaSatDTO> search(@Param("search") String search,
                                              @Param("activo") Boolean activo,
                                              Pageable pageable);

    @Query("""
                        SELECT new com.example.vucem_catalogos_service.model.dto.CatActividadEconomicaSatDTO(
                            e.id,
                            CASE WHEN e.idActividadEconomicaR IS NOT NULL THEN e.idActividadEconomicaR.id ELSE NULL END,
                            CASE WHEN e.idActividadEconomicaR IS NOT NULL THEN e.idActividadEconomicaR.descripcion ELSE NULL END,
                            e.descripcion,
                            e.descScian,
                            e.descNotas,
                            cv.descripcion,
                            cv.cveTipoEmpresaRecif,
                            e.fecIniVigencia,
                            e.fecFinVigencia,
                            e.fecCaptura,
                            e.fecActualizacion,
                            e.cveTipoIndustriaIdc,
                            e.blnActivo
                        )
                        FROM CatActividadEconomicaSat e
                        LEFT JOIN e.cveTipoEmpresaRecif cv
                        WHERE e.id = :id
            """)
    Optional<CatActividadEconomicaSatDTO> findByActividadEconomicaSatDTO(@Param("id") Long id);

    List<CatActividadEconomicaSat> findByBlnActivoTrue();

    List<CatActividadEconomicaSat> findByBlnActivoTrueOrderByDescripcionAsc();
}
