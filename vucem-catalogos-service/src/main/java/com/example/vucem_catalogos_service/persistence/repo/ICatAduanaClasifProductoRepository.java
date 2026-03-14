package com.example.vucem_catalogos_service.persistence.repo;

import com.example.vucem_catalogos_service.model.dto.AduanaClasifProducto.CatAduanaClasifProdResponseDTO;
import com.example.vucem_catalogos_service.model.dto.CatAprobCertSeResponseDTO;
import com.example.vucem_catalogos_service.model.dto.ClasifProductoTraDTO;
import com.example.vucem_catalogos_service.model.entity.CatAduanaClasifProd;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ICatAduanaClasifProductoRepository extends JpaRepository<CatAduanaClasifProd, Long>, JpaSpecificationExecutor<CatAduanaClasifProd> {

    @Query("""
            SELECT new com.example.vucem_catalogos_service.model.dto.AduanaClasifProducto.CatAduanaClasifProdResponseDTO(
                a.id,
                a.aduana.cveAduana,
                a.aduana.nombre,
                a.idClasifProducto.idClasifProduct,
                a.idClasifProducto.nombre,
                a.fecIniVigencia,
                a.fecFinVigencia,
                a.blnActivo
            )
            FROM CatAduanaClasifProd a
            JOIN a.idClasifProducto cp
            JOIN cp.idTipoTramite tr
            WHERE
                (
                    :search IS NULL OR
                    LOWER(a.aduana.nombre) LIKE LOWER(CONCAT('%', CAST(:search AS string), '%')) OR
                    LOWER(a.aduana.nombre) LIKE LOWER(CONCAT('%', CAST(:search AS string), '%')) OR
                    LOWER(a.idClasifProducto.nombre) LIKE LOWER(CONCAT('%', CAST(:search AS string), '%')) )
                AND (:activo IS NULL OR a.blnActivo = :activo)
                AND (:idTipoTramite IS NULL OR (tr.id IS NOT NULL AND tr.id = :idTipoTramite))
            """)
    Page<CatAduanaClasifProdResponseDTO> search(
            @Param("search") String search,
            @Param("activo") Boolean activo,
            @Param("idTipoTramite") Long idTipoTramite,
            Pageable pageable);

    @Query("""
            SELECT new com.example.vucem_catalogos_service.model.dto.AduanaClasifProducto.CatAduanaClasifProdResponseDTO(
                a.id,
                u.cveAduana,
                u.nombre,
                b.idClasifProduct,
                b.nombre,
                a.fecIniVigencia,
                a.fecFinVigencia,
                a.blnActivo
            )
            FROM CatAduanaClasifProd a
            JOIN a.aduana u
            JOIN a.idClasifProducto b
            WHERE a.id = :id
            """)
    CatAduanaClasifProdResponseDTO findByIdAduanaClasif(@Param("id") Long id);


    @Query("""
                SELECT DISTINCT new com.example.vucem_catalogos_service.model.dto.ClasifProductoTraDTO(
                    tr.id,
                    CONCAT(
                        tr.id, ' ',
                        COALESCE(tr.descModalidad, tr.descSubservicio)
                    )
                )
                FROM CatAduanaClasifProd cat
                JOIN cat.idClasifProducto cp
                JOIN cp.idTipoTramite tr
                WHERE tr.id IN (
                    260301,260302,260303,260304,
                    260603,260604,
                    261101,261102,261103,261104
                )
                AND cat.blnActivo = true
                ORDER BY tr.id ASC
            """)
    List<ClasifProductoTraDTO> listadoClasifPrR();


}
