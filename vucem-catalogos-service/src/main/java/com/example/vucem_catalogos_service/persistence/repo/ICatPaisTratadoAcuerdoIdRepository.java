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

import java.util.List;
import java.util.Optional;

@Repository
public interface ICatPaisTratadoAcuerdoIdRepository extends JpaRepository<CatPaisTratadoAcuerdo, CatPaisTratadoAcuerdoId> {

    @Query(value = """
            SELECT new com.example.vucem_catalogos_service.model.dto.CatPaisTratadoAcuerdoResponseDTO(
                a.id.cvePais,
                p.nombre,
                t.id,
                t.cveTratadoAcuerdo,
                a.fecIniVigencia,
                a.fecFinVigencia,
                a.fecCaptura,
                a.blnActivo,
                a.blnEnvioElectronico
            )
            FROM CatPaisTratadoAcuerdo a
            JOIN a.cvePais p
            JOIN a.idTratadoAcuerdo t
            WHERE (:cvePais IS NULL OR a.id.cvePais = :cvePais)
              AND (:idTratadoAcuerdo IS NULL OR a.id.idTratadoAcuerdo = :idTratadoAcuerdo)
              AND (:blnActivo IS NULL OR a.blnActivo = :blnActivo)
              AND (:search IS NULL OR
                   LOWER(a.id.cvePais) LIKE LOWER(CONCAT('%', :search, '%')) OR
                   LOWER(p.nombre) LIKE LOWER(CONCAT('%', :search, '%')) OR
                   LOWER(CAST(t.id AS string)) LIKE LOWER(CONCAT('%', :search, '%')) OR
                   LOWER(t.cveTratadoAcuerdo) LIKE LOWER(CONCAT('%', :search, '%')) OR
                   LOWER(CAST(a.fecIniVigencia AS string)) LIKE LOWER(CONCAT('%', :search, '%')) OR
                   LOWER(CAST(a.fecFinVigencia AS string)) LIKE LOWER(CONCAT('%', :search, '%')) OR
                   LOWER(CAST(a.fecCaptura AS string)) LIKE LOWER(CONCAT('%', :search, '%')))
            """,
            countQuery = """
            SELECT COUNT(a)
            FROM CatPaisTratadoAcuerdo a
            JOIN a.cvePais p
            JOIN a.idTratadoAcuerdo t
            WHERE (:cvePais IS NULL OR a.id.cvePais = :cvePais)
              AND (:idTratadoAcuerdo IS NULL OR a.id.idTratadoAcuerdo = :idTratadoAcuerdo)
              AND (:blnActivo IS NULL OR a.blnActivo = :blnActivo)
              AND (:search IS NULL OR
                   LOWER(a.id.cvePais) LIKE LOWER(CONCAT('%', :search, '%')) OR
                   LOWER(p.nombre) LIKE LOWER(CONCAT('%', :search, '%')) OR
                   LOWER(CAST(t.id AS string)) LIKE LOWER(CONCAT('%', :search, '%')) OR
                   LOWER(t.cveTratadoAcuerdo) LIKE LOWER(CONCAT('%', :search, '%')) OR
                   LOWER(CAST(a.fecIniVigencia AS string)) LIKE LOWER(CONCAT('%', :search, '%')) OR
                   LOWER(CAST(a.fecFinVigencia AS string)) LIKE LOWER(CONCAT('%', :search, '%')) OR
                   LOWER(CAST(a.fecCaptura AS string)) LIKE LOWER(CONCAT('%', :search, '%')))
            """)
    Page<CatPaisTratadoAcuerdoResponseDTO> search(
            @Param("cvePais") String cvePais,
            @Param("idTratadoAcuerdo") Short idTratadoAcuerdo,
            @Param("blnActivo") Boolean blnActivo,
            @Param("search") String search,
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
                a.blnActivo,
                a.blnEnvioElectronico
            )
            FROM CatPaisTratadoAcuerdo a
            JOIN a.cvePais p
            JOIN a.idTratadoAcuerdo t
            WHERE a.id.cvePais = :cvePais AND a.id.idTratadoAcuerdo = :idTratadoAcuerdo
            """)
    Optional<CatPaisTratadoAcuerdoResponseDTO> findByPaisTratado(
            @Param("cvePais") String cvePais,
            @Param("idTratadoAcuerdo") Short idTratadoAcuerdo);


    @Query("""
       SELECT DISTINCT t.id AS id, t.cveTratadoAcuerdo AS nombre
       FROM CatPaisTratadoAcuerdo pt
       JOIN pt.idTratadoAcuerdo t
       WHERE t.blnActivo = true
         AND pt.blnActivo <> false
         AND (t.fecFinVigencia IS NULL OR t.fecFinVigencia >= CURRENT_DATE)
       ORDER BY t.cveTratadoAcuerdo ASC
       """)
    List<ICatTratadoAcuerdoRepository.ComboProyeccion> listadoTratadosActivos();
}
