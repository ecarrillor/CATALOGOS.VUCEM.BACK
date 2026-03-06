package com.example.vucem_catalogos_service.persistence.repo;

import com.example.vucem_catalogos_service.model.dto.CatTratadoBloquePaiResponseDTO;
import com.example.vucem_catalogos_service.model.entity.CatTratadoBloquePai;
import com.example.vucem_catalogos_service.model.entity.CatTratadoBloquePaiId;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ICatTratadoBloquePaiRepository extends JpaRepository<CatTratadoBloquePai, CatTratadoBloquePaiId> {

    @Query("""
            SELECT new com.example.vucem_catalogos_service.model.dto.CatTratadoBloquePaiResponseDTO(
                tbp.id.cvePais,
                p.nombre,
                tbp.id.idTratadoAcuerdo,
                t.cveTratadoAcuerdo,
                tbp.fecCaptura,
                tbp.fecIniVigencia,
                tbp.fecFinVigencia,
                tbp.blnActivo,
                tbp.blnEnvioElectronico,
                tbp.blnMuestraCertificado
            )
            FROM CatTratadoBloquePai tbp, CatPais p, CatTratadoAcuerdo t
            WHERE tbp.id.cvePais = p.cvePais AND tbp.id.idTratadoAcuerdo = t.id
              AND (:cvePais IS NULL OR tbp.id.cvePais = :cvePais)
              AND (:idTratadoAcuerdo IS NULL OR tbp.id.idTratadoAcuerdo = :idTratadoAcuerdo)
              AND (:blnActivo IS NULL OR tbp.blnActivo = :blnActivo)
            ORDER BY tbp.id.cvePais ASC, t.cveTratadoAcuerdo ASC
            """)
    Page<CatTratadoBloquePaiResponseDTO> search(
            @Param("cvePais") String cvePais,
            @Param("idTratadoAcuerdo") Short idTratadoAcuerdo,
            @Param("blnActivo") Boolean blnActivo,
            Pageable pageable);

    @Query("""
            SELECT new com.example.vucem_catalogos_service.model.dto.CatTratadoBloquePaiResponseDTO(
                tbp.id.cvePais,
                p.nombre,
                tbp.id.idTratadoAcuerdo,
                t.cveTratadoAcuerdo,
                tbp.fecCaptura,
                tbp.fecIniVigencia,
                tbp.fecFinVigencia,
                tbp.blnActivo,
                tbp.blnEnvioElectronico,
                tbp.blnMuestraCertificado
            )
            FROM CatTratadoBloquePai tbp, CatPais p, CatTratadoAcuerdo t
            WHERE tbp.id.cvePais = p.cvePais AND tbp.id.idTratadoAcuerdo = t.id
              AND tbp.id.cvePais = :cvePais AND tbp.id.idTratadoAcuerdo = :idTratadoAcuerdo
            """)
    Optional<CatTratadoBloquePaiResponseDTO> findByPaisTratado(
            @Param("cvePais") String cvePais,
            @Param("idTratadoAcuerdo") Short idTratadoAcuerdo);

    @Query("SELECT tbp FROM CatTratadoBloquePai tbp WHERE tbp.id.cvePais = :cvePais AND tbp.id.idTratadoAcuerdo = :idTratadoAcuerdo AND tbp.blnActivo = true")
    List<CatTratadoBloquePai> findActivosByPaisAndTratado(
            @Param("cvePais") String cvePais,
            @Param("idTratadoAcuerdo") Short idTratadoAcuerdo);

    @Query("""
                SELECT DISTINCT p.cvePais AS clave,
                       p.nombre AS descripcion
                FROM CatTratadoBloquePai tbp
                JOIN tbp.pais p
                WHERE tbp.id.idTratadoAcuerdo IN :idsTratado
                  AND tbp.blnActivo = true
                ORDER BY p.nombre ASC
            """)
    List<ComboProyeccion> findPaisesGuardadosByTratados(
            @Param("idsTratado") List<Short> idsTratado);

    @Query("""
    SELECT DISTINCT ta.id AS clave,
           ta.cveTratadoAcuerdo AS descripcion
    FROM CatTratadoBloquePai tbp
    JOIN tbp.tratadoAcuerdo ta
    WHERE ta.blnActivo = true
      AND tbp.blnActivo = true
      AND (ta.fecFinVigencia IS NULL OR ta.fecFinVigencia >= CURRENT_DATE)
      AND tbp.id.cvePais IN :clavePaises
    ORDER BY ta.cveTratadoAcuerdo ASC
""")
    List<ComboProyeccion> findTratadosGuardadosByPaises(
            @Param("clavePaises") List<String> clavePaises);

    @Query("""
    SELECT DISTINCT cp.cvePais AS clave,
           cp.nombre AS descripcion
    FROM CatPaisTratadoAcuerdo pta
    JOIN pta.cvePais cp
    WHERE pta.idTratadoAcuerdo.id IN :idsTratado
      AND pta.blnActivo = true
      AND cp.cvePais NOT IN (
            SELECT tbp.id.cvePais
            FROM CatTratadoBloquePai tbp
            WHERE tbp.tratadoAcuerdo.id IN :idsTratado
              AND tbp.blnActivo = true
      )
    ORDER BY cp.nombre ASC
""")
    List<ComboProyeccion> findPaisesNoGuardadosByTratados(
            @Param("idsTratado") List<Short> idsTratado);

    interface ComboProyeccion {
        String getClave();

        String getDescripcion();
    }
}
