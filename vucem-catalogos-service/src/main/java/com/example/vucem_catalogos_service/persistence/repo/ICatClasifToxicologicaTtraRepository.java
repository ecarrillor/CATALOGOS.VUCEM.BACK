package com.example.vucem_catalogos_service.persistence.repo;

import com.example.vucem_catalogos_service.model.dto.CatClasifToxicologicaTtraDTO;
import com.example.vucem_catalogos_service.model.entity.CatClasifToxicologicaTtra;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ICatClasifToxicologicaTtraRepository extends JpaRepository<CatClasifToxicologicaTtra, Short> {

    @Query("""
            SELECT new com.example.vucem_catalogos_service.model.dto.CatClasifToxicologicaTtraDTO(
                e.id,
                CASE WHEN e.idTipoTramite IS NOT NULL THEN e.idTipoTramite.id ELSE NULL END,
                CASE WHEN e.idTipoTramite IS NOT NULL THEN e.idTipoTramite.nombre ELSE NULL END,
                e.descClasifToxicologica,
                e.fecIniVigencia,
                e.fecFinVigencia,
                e.blnActivo
            )
            FROM CatClasifToxicologicaTtra e
            WHERE (:search IS NULL OR LOWER(e.descClasifToxicologica) LIKE LOWER(CONCAT('%', CAST(:search AS string), '%')))
            AND (:activo IS NULL OR e.blnActivo = :activo)
            """)
    Page<CatClasifToxicologicaTtraDTO> search(@Param("search") String search,
                                               @Param("activo") Boolean activo,
                                               Pageable pageable);

    @Query("""
            SELECT new com.example.vucem_catalogos_service.model.dto.CatClasifToxicologicaTtraDTO(
                e.id,
                CASE WHEN e.idTipoTramite IS NOT NULL THEN e.idTipoTramite.id ELSE NULL END,
                CASE WHEN e.idTipoTramite IS NOT NULL THEN e.idTipoTramite.nombre ELSE NULL END,
                e.descClasifToxicologica,
                e.fecIniVigencia,
                e.fecFinVigencia,
                e.blnActivo
            )
            FROM CatClasifToxicologicaTtra e
            WHERE e.id = :id
            """)
    Optional<CatClasifToxicologicaTtraDTO> findByClasifToxicologicaTtraDTO(@Param("id") Short id);
}
