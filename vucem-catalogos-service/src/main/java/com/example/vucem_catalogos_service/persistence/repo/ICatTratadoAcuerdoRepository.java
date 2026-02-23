package com.example.vucem_catalogos_service.persistence.repo;

import com.example.vucem_catalogos_service.model.dto.CatTratadoAcuerdoDTO;
import com.example.vucem_catalogos_service.model.entity.CatTratadoAcuerdo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ICatTratadoAcuerdoRepository extends JpaRepository<CatTratadoAcuerdo, Short> {

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
            WHERE (:search IS NULL OR LOWER(e.cveTratadoAcuerdo) LIKE LOWER(CONCAT('%', CAST(:search AS string), '%'))
                OR LOWER(e.nombre) LIKE LOWER(CONCAT('%', CAST(:search AS string), '%')))
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
}
