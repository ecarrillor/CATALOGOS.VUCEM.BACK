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
            FROM CatTratadoBloquePai tbp
            JOIN CatPais p ON tbp.id.cvePais = p.cvePais
            JOIN CatTratadoAcuerdo t ON tbp.id.idTratadoAcuerdo = t.id
            WHERE (:cvePais IS NULL OR tbp.id.cvePais = :cvePais)
              AND (:idTratadoAcuerdo IS NULL OR tbp.id.idTratadoAcuerdo = :idTratadoAcuerdo)
              AND (:blnActivo IS NULL OR tbp.blnActivo = :blnActivo)
              AND (:search IS NULL OR
                   LOWER(tbp.id.cvePais) LIKE LOWER(CONCAT('%', :search, '%')) OR
                   LOWER(p.nombre) LIKE LOWER(CONCAT('%', :search, '%')) OR
                   LOWER(CAST(tbp.id.idTratadoAcuerdo AS string)) LIKE LOWER(CONCAT('%', :search, '%')) OR
                   LOWER(t.cveTratadoAcuerdo) LIKE LOWER(CONCAT('%', :search, '%')) OR
                   LOWER(CAST(tbp.fecCaptura AS string)) LIKE LOWER(CONCAT('%', :search, '%')) OR
                   LOWER(CAST(tbp.fecIniVigencia AS string)) LIKE LOWER(CONCAT('%', :search, '%')) OR
                   LOWER(CAST(tbp.fecFinVigencia AS string)) LIKE LOWER(CONCAT('%', :search, '%')))
            ORDER BY tbp.id.cvePais ASC, t.cveTratadoAcuerdo ASC
            """)
    Page<CatTratadoBloquePaiResponseDTO> search(
            @Param("cvePais") String cvePais,
            @Param("idTratadoAcuerdo") Short idTratadoAcuerdo,
            @Param("blnActivo") Boolean blnActivo,
            @Param("search") String search,
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

    @Query("SELECT tbp FROM CatTratadoBloquePai tbp WHERE (tbp.id.cvePais IN :cvePaises OR tbp.id.idTratadoAcuerdo IN :idsTratado) AND tbp.blnActivo = true")
    List<CatTratadoBloquePai> findActivosByPaisesOrTratados(
            @Param("cvePaises") List<String> cvePaises,
            @Param("idsTratado") List<Short> idsTratado);

    @Query("""
        SELECT DISTINCT p.cvePais AS clave,
               p.nombre AS descripcion
        FROM CatTratadoBloquePai tbp
        JOIN tbp.pais p
        WHERE tbp.id.idTratadoAcuerdo IN :idsTratado
          AND (p.fecFinVigencia IS NULL OR p.fecFinVigencia >= CURRENT_DATE)
          AND p.blnActivo <> false
          AND (tbp.fecFinVigencia IS NULL OR tbp.fecFinVigencia >= CURRENT_DATE)
          AND tbp.blnActivo <> false
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
       """)
    List<ComboProyeccion> findTratadosGuardadosByPaises(
            @Param("clavePaises") List<String> clavePaises);

    @Query("""
        SELECT DISTINCT ta.id AS clave,
               ta.cveTratadoAcuerdo AS descripcion
        FROM CatPaisTratadoAcuerdo pta
        JOIN pta.idTratadoAcuerdo ta
        WHERE ta.blnActivo = true
          AND pta.blnActivo = true
          AND (ta.fecFinVigencia IS NULL OR ta.fecFinVigencia >= CURRENT_DATE)
          AND pta.cvePais.cvePais IN :clavePaises
          AND ta.id NOT IN (
                SELECT tbp.tratadoAcuerdo.id
                FROM CatTratadoBloquePai tbp
                WHERE tbp.blnActivo = true
                  AND tbp.id.cvePais IN :clavePaises
          )
        ORDER BY ta.cveTratadoAcuerdo ASC
       """)
    List<ComboProyeccion> findTratadosNoGuardadosByPaises(
            @Param("clavePaises") List<String> clavePaises);

    @Query("""
        SELECT DISTINCT cp.cvePais AS clave,
               cp.nombre AS descripcion
        FROM CatPaisTratadoAcuerdo pta
        JOIN pta.cvePais cp
        WHERE pta.idTratadoAcuerdo.id IN :idsTratado
          AND (cp.fecFinVigencia IS NULL OR cp.fecFinVigencia >= CURRENT_DATE)
          AND cp.blnActivo <> false
          AND (pta.fecFinVigencia IS NULL OR pta.fecFinVigencia >= CURRENT_DATE)
          AND pta.blnActivo <> false
          AND NOT EXISTS (
                SELECT 1
                FROM CatTratadoBloquePai tbp
                WHERE tbp.pais = cp
                  AND tbp.id.idTratadoAcuerdo IN :idsTratado
                  AND (tbp.fecFinVigencia IS NULL OR tbp.fecFinVigencia >= CURRENT_DATE)
                  AND tbp.blnActivo <> false
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
