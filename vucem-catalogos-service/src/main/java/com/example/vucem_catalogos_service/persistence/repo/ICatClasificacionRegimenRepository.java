package com.example.vucem_catalogos_service.persistence.repo;

import com.example.vucem_catalogos_service.model.dto.CatClasificacionRegimenDTO;
import com.example.vucem_catalogos_service.model.entity.CatClasificacionRegimen;
import com.example.vucem_catalogos_service.model.entity.CatClasificacionRegimanId;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ICatClasificacionRegimenRepository extends JpaRepository<CatClasificacionRegimen, CatClasificacionRegimanId> {

    @Query("""
                SELECT new com.example.vucem_catalogos_service.model.dto.CatClasificacionRegimenDTO(
                    e.id.cveClasificacionRegimen,
                    e.id.cveRegimen,
                    re.nombre,
                    e.nombre,
                    e.codRegimen,
                    e.fecIniVigencia,
                    e.fecFinVigencia,
                    e.blnActivo
                )
                FROM CatClasificacionRegimen e
                JOIN e.cveRegimen re
                WHERE (:search IS NULL 
                    OR LOWER(e.nombre) LIKE LOWER(CONCAT('%', CAST(:search AS string), '%')) OR
                    LOWER(re.nombre) LIKE LOWER(CONCAT('%', CAST(:search AS string), '%'))
                )
                AND (:activo IS NULL OR e.blnActivo = :activo)
            """)
    Page<CatClasificacionRegimenDTO> search(@Param("search") String search,
                                            @Param("activo") Boolean activo,
                                            Pageable pageable);

    @Query("""
            SELECT new com.example.vucem_catalogos_service.model.dto.CatClasificacionRegimenDTO(
                e.id.cveClasificacionRegimen,
                e.id.cveRegimen,
                e.cveRegimen.nombre,
                e.nombre,
                e.codRegimen,
                e.fecIniVigencia,
                e.fecFinVigencia,
                e.blnActivo
            )
            FROM CatClasificacionRegimen e
            WHERE e.id.cveClasificacionRegimen = :cveClasificacionRegimen
              AND e.id.cveRegimen = :cveRegimen
            """)
    CatClasificacionRegimenDTO findByClasificacionRegimenDTO(
            @Param("cveClasificacionRegimen") String cveClasificacionRegimen,
            @Param("cveRegimen") String cveRegimen);
}
