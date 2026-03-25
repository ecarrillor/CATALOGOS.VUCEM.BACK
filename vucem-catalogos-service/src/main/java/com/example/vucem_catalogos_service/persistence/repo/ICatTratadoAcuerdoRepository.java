package com.example.vucem_catalogos_service.persistence.repo;

import com.example.vucem_catalogos_service.model.dto.CatTratadoAcuerdoDTO;
import com.example.vucem_catalogos_service.model.entity.CatTratadoAcuerdo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ICatTratadoAcuerdoRepository extends JpaRepository<CatTratadoAcuerdo, Short> {

    @Query(value = """
            SELECT new com.example.vucem_catalogos_service.model.dto.CatTratadoAcuerdoDTO(
                e.id,
                e.ideTipoTratadoAcuerdo,
                e.cveTratadoAcuerdo,
                e.nombre,
                e.blnPexim,
                e.fecCaptura,
                e.fecFinVigencia,
                e.ideTipoCupoSaai,
                e.fecIniVigencia,
                e.blnActivo,
                e.blnEvaluarIndividual
            )
            FROM CatTratadoAcuerdo e
            WHERE (:search IS NULL OR
                LOWER(CAST(e.id AS string)) LIKE :search OR
                LOWER(e.ideTipoTratadoAcuerdo) LIKE :search OR
                LOWER(e.cveTratadoAcuerdo) LIKE :search OR
                LOWER(e.nombre) LIKE :search OR
                LOWER(CAST(e.fecCaptura AS string)) LIKE :search OR
                LOWER(CAST(e.fecFinVigencia AS string)) LIKE :search OR
                LOWER(e.ideTipoCupoSaai) LIKE :search OR
                LOWER(CAST(e.fecIniVigencia AS string)) LIKE :search)
            AND (:activo IS NULL OR e.blnActivo = :activo)
            """,
            countQuery = """
            SELECT COUNT(e)
            FROM CatTratadoAcuerdo e
            WHERE (:search IS NULL OR
                LOWER(CAST(e.id AS string)) LIKE :search OR
                LOWER(e.ideTipoTratadoAcuerdo) LIKE :search OR
                LOWER(e.cveTratadoAcuerdo) LIKE :search OR
                LOWER(e.nombre) LIKE :search OR
                LOWER(CAST(e.fecCaptura AS string)) LIKE :search OR
                LOWER(CAST(e.fecFinVigencia AS string)) LIKE :search OR
                LOWER(e.ideTipoCupoSaai) LIKE :search OR
                LOWER(CAST(e.fecIniVigencia AS string)) LIKE :search)
            AND (:activo IS NULL OR e.blnActivo = :activo)
            """)
    Page<CatTratadoAcuerdoDTO> search(@Param("search") String search,
                                       @Param("activo") Boolean activo,
                                       Pageable pageable);

    @Query("""
            SELECT new com.example.vucem_catalogos_service.model.dto.CatTratadoAcuerdoDTO(
                e.id,
                e.ideTipoTratadoAcuerdo,
                e.cveTratadoAcuerdo,
                e.nombre,
                e.blnPexim,
                e.fecCaptura,
                e.fecFinVigencia,
                e.ideTipoCupoSaai,
                e.fecIniVigencia,
                e.blnActivo,
                e.blnEvaluarIndividual
            )
            FROM CatTratadoAcuerdo e
            WHERE e.id = :id
            """)
    Optional<CatTratadoAcuerdoDTO> findByTratadoAcuerdoDTO(@Param("id") Short id);

    @Query("SELECT t.id AS id, t.nombre AS nombre FROM CatTratadoAcuerdo t WHERE t.blnActivo = true ORDER BY t.nombre ASC")
    List<ComboProyeccion> listadoTratadosActivos();

    interface ComboProyeccion {
        Short getId();
        String getNombre();
    }
}
