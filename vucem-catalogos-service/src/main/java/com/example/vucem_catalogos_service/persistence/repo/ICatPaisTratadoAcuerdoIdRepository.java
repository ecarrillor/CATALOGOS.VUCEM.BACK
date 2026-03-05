package com.example.vucem_catalogos_service.persistence.repo;

import com.example.vucem_catalogos_service.model.dto.CatPaisTratadoAcuerdoResponseDTO;
import com.example.vucem_catalogos_service.model.entity.CatPaisTratadoAcuerdo;
import com.example.vucem_catalogos_service.model.entity.CatPaisTratadoAcuerdoId;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ICatPaisTratadoAcuerdoIdRepository extends JpaRepository<CatPaisTratadoAcuerdo, CatPaisTratadoAcuerdoId> {

    @Query("""
            SELECT new com.example.vucem_catalogos_service.model.dto.CatPaisTratadoAcuerdoResponseDTO(
                a.id.cvePais,
                p.nombre,
                t.id,
                t.cveTratadoAcuerdo,
                a.fecIniVigencia,
                a.fecFinVigencia,
                a.fecCaptura,
                a.blnActivo
            )
            FROM CatPaisTratadoAcuerdo a
            JOIN a.cvePais p
            JOIN a.idTratadoAcuerdo t
            WHERE (:cvePais IS NULL OR a.id.cvePais = :cvePais)
              AND (:idTratadoAcuerdo IS NULL OR a.id.idTratadoAcuerdo = :idTratadoAcuerdo)
              AND (:blnActivo IS NULL OR a.blnActivo = :blnActivo)
            ORDER BY a.id.cvePais ASC, t.cveTratadoAcuerdo ASC
            """)
    Page<CatPaisTratadoAcuerdoResponseDTO> search(
            @Param("cvePais") String cvePais,
            @Param("idTratadoAcuerdo") Short idTratadoAcuerdo,
            @Param("blnActivo") Boolean blnActivo,
            Pageable pageable);

    @Query("""
            SELECT new com.example.vucem_catalogos_service.model.dto.CatPaisTratadoAcuerdoResponseDTO(
                a.id.cvePais,
                p.nombre,
                t.id,
                t.cveTratadoAcuerdo,
                a.fecIniVigencia,
                a.fecFinVigencia,
                a.fecCaptura,
                a.blnActivo
            )
            FROM CatPaisTratadoAcuerdo a
            JOIN a.cvePais p
            JOIN a.idTratadoAcuerdo t
            WHERE a.id.cvePais = :cvePais AND a.id.idTratadoAcuerdo = :idTratadoAcuerdo
            """)
    Optional<CatPaisTratadoAcuerdoResponseDTO> findByPaisTratado(
            @Param("cvePais") String cvePais,
            @Param("idTratadoAcuerdo") Short idTratadoAcuerdo);
}
