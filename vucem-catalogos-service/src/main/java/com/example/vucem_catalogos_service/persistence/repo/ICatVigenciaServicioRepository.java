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

    @Query("""
            SELECT new com.example.vucem_catalogos_service.model.dto.CatVigenciaServicioDTO(
                e.id,
                e.numVigencia,
                e.ideTipoVigencia,
                e.ideTipoServicioCeror,
                CASE WHEN e.catPaisTratadoAcuerdo IS NOT NULL THEN e.catPaisTratadoAcuerdo.id.cvePais ELSE NULL END,
                CASE WHEN e.catPaisTratadoAcuerdo IS NOT NULL THEN e.catPaisTratadoAcuerdo.id.idTratadoAcuerdo ELSE NULL END,
                CASE WHEN e.idBloque IS NOT NULL THEN e.idBloque.id ELSE NULL END,
                CASE WHEN e.idBloque IS NOT NULL THEN e.idBloque.nombre ELSE NULL END,
                e.fecIniVigencia,
                e.blnActivo
            )
            FROM CatVigenciaServicio e
            WHERE (:search IS NULL OR LOWER(e.numVigencia) LIKE LOWER(CONCAT('%', CAST(:search AS string), '%')))
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
                CASE WHEN e.catPaisTratadoAcuerdo IS NOT NULL THEN e.catPaisTratadoAcuerdo.id.cvePais ELSE NULL END,
                CASE WHEN e.catPaisTratadoAcuerdo IS NOT NULL THEN e.catPaisTratadoAcuerdo.id.idTratadoAcuerdo ELSE NULL END,
                CASE WHEN e.idBloque IS NOT NULL THEN e.idBloque.id ELSE NULL END,
                CASE WHEN e.idBloque IS NOT NULL THEN e.idBloque.nombre ELSE NULL END,
                e.fecIniVigencia,
                e.blnActivo
            )
            FROM CatVigenciaServicio e
            WHERE e.id = :id
            """)
    Optional<CatVigenciaServicioDTO> findByVigenciaServicioDTO(@Param("id") Short id);
}
