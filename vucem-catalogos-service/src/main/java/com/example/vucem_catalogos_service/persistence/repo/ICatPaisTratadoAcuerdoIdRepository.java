package com.example.vucem_catalogos_service.persistence.repo;

import com.example.vucem_catalogos_service.model.dto.CatPaisTratadoAcuerdoResponseDTO;
import com.example.vucem_catalogos_service.model.dto.CatPlazoTtraDTO;
import com.example.vucem_catalogos_service.model.entity.CatPaisTratadoAcuerdo;
import com.example.vucem_catalogos_service.model.entity.CatPaisTratadoAcuerdoId;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface ICatPaisTratadoAcuerdoIdRepository extends JpaRepository<CatPaisTratadoAcuerdo, CatPaisTratadoAcuerdoId> {
    @Query("""
            SELECT new com.example.vucem_catalogos_service.model.dto.CatPaisTratadoAcuerdoResponseDTO(
                p.cvePais,
                p.nombre,
                t.id,
                t.cveTratadoAcuerdo,
                a.fecIniVigencia,
                a.fecFinVigencia,
                a.blnActivo
            )
            FROM CatPaisTratadoAcuerdo a
            JOIN a.cvePais p
            JOIN a.idTratadoAcuerdo t
             WHERE
                            (
                                        :search IS NULL OR
                                        LOWER(p.nombre) LIKE :search OR
                                        LOWER(t.nombre) LIKE :search
                           )
                                         AND
                           (
                               :activo IS NULL OR a.blnActivo = :activo
                           )
            """)
    Page<CatPaisTratadoAcuerdoResponseDTO> search(@Param("search") String search,
                                 @Param("activo") Boolean activo,
                                 Pageable pageable);

    @Query("""
            SELECT new com.example.vucem_catalogos_service.model.dto.CatPaisTratadoAcuerdoResponseDTO(
               p.cvePais,
                p.nombre,
                t.id,
                t.cveTratadoAcuerdo,
                a.fecIniVigencia,
                a.fecFinVigencia,
                a.blnActivo
            )
            FROM CatPaisTratadoAcuerdo a
            JOIN a.cvePais p
            JOIN a.idTratadoAcuerdo t
            WHERE p.cvePais = :idPais AND t.id = :idTratado
            """)
    Optional<CatPaisTratadoAcuerdoResponseDTO> findByPaisTratado(@Param("idPais") String idPais,
                                                 @Param("idTratado") Short idTratado);
}
