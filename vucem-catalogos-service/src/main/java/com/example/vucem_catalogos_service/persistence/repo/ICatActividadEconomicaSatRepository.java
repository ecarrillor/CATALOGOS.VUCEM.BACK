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

    @Query("""
            SELECT new com.example.vucem_catalogos_service.model.dto.CatActividadEconomicaSatDTO(
                e.id,
                CASE WHEN e.idActividadEconomicaR IS NOT NULL THEN e.idActividadEconomicaR.id ELSE NULL END,
                CASE WHEN e.idActividadEconomicaR IS NOT NULL THEN e.idActividadEconomicaR.descripcion ELSE NULL END,
                e.descripcion,
                e.descScian,
                e.descNotas,
                e.cveTipoEmpresaRecif.descripcion,
                e.cveTipoEmpresaRecif.cveTipoEmpresaRecif,
                e.fecIniVigencia,
                e.fecFinVigencia,
                e.fecCaptura,
                e.fecActualizacion,
                e.cveTipoIndustriaIdc,
                e.blnActivo
            )
            FROM CatActividadEconomicaSat e
            WHERE (:search IS NULL OR LOWER(e.descripcion) LIKE LOWER(CONCAT('%', CAST(:search AS string), '%'))
                OR LOWER(e.descScian) LIKE LOWER(CONCAT('%', CAST(:search AS string), '%'))
                OR LOWER(e.cveTipoIndustriaIdc) LIKE LOWER(CONCAT('%', CAST(:search AS string), '%')))
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
}
