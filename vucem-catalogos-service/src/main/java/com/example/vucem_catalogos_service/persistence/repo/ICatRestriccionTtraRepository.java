package com.example.vucem_catalogos_service.persistence.repo;

import com.example.vucem_catalogos_service.model.dto.ClasifProductoTraDTO;
import com.example.vucem_catalogos_service.model.dto.RestriccionTtra.CatRestriccionTtraResponseDTO;
import com.example.vucem_catalogos_service.model.entity.CatRestriccionTtra;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ICatRestriccionTtraRepository extends JpaRepository<CatRestriccionTtra, Short>,
        JpaSpecificationExecutor<CatRestriccionTtra> {

    @Query("""
            SELECT new com.example.vucem_catalogos_service.model.dto.RestriccionTtra.CatRestriccionTtraResponseDTO(
                rt.id,
                rt.idTipoTramite.id,
                CONCAT(rt.idTipoTramite.id, ' ', COALESCE(rt.idTipoTramite.descModalidad, rt.idTipoTramite.descSubservicio)),
                rt.descRestriccion,
                rt.descContenidoRestriccion,
                rt.fecIniVigencia,
                rt.fecFinVigencia,
                rt.blnActivo,
                rt.ideSentDictamen,
                rt.ideTipoRestriccionTtra,
                rt.ideMotivoRechazoDict
            )
            FROM CatRestriccionTtra rt
            WHERE
                (
                    :search IS NULL OR
                    LOWER(CAST(rt.id AS string)) LIKE LOWER(CONCAT('%', CAST(:search AS string), '%')) OR
                    LOWER(CAST(rt.idTipoTramite.id AS string)) LIKE LOWER(CONCAT('%', CAST(:search AS string), '%')) OR
                    LOWER(rt.descRestriccion) LIKE LOWER(CONCAT('%', CAST(:search AS string), '%')) OR
                    LOWER(rt.descContenidoRestriccion) LIKE LOWER(CONCAT('%', CAST(:search AS string), '%')) OR
                    LOWER(CAST(rt.fecIniVigencia AS string)) LIKE LOWER(CONCAT('%', CAST(:search AS string), '%')) OR
                    LOWER(CAST(rt.fecFinVigencia AS string)) LIKE LOWER(CONCAT('%', CAST(:search AS string), '%')) OR
                    LOWER(rt.ideSentDictamen) LIKE LOWER(CONCAT('%', CAST(:search AS string), '%')) OR
                    LOWER(rt.ideTipoRestriccionTtra) LIKE LOWER(CONCAT('%', CAST(:search AS string), '%')) OR
                    LOWER(rt.ideMotivoRechazoDict) LIKE LOWER(CONCAT('%', CAST(:search AS string), '%'))
                )
                AND (:activo IS NULL OR rt.blnActivo = :activo)
                AND (:idTipoTramite IS NULL OR rt.idTipoTramite.id = :idTipoTramite)
            """)
    Page<CatRestriccionTtraResponseDTO> search(
            @Param("search") String search,
            @Param("activo") Boolean activo,
            @Param("idTipoTramite") Long idTipoTramite,
            Pageable pageable);

    @Query("""
            SELECT new com.example.vucem_catalogos_service.model.dto.ClasifProductoTraDTO(
                t.id,
                CONCAT(t.id, ' ', COALESCE(t.descModalidad, t.descSubservicio))
            )
            FROM CatTipoTramite t
            WHERE t.id IN (
                220101,220102,220103,220201,220202,220203,220501,220502,220503,
                221601,221602,221603,
                230101,230901,230902,230903,
                240101,240102,240103,240104,240105,240106,240107,240108,240109,
                240111,240112,240114,240115,240116,240117,240118,240119,240120,
                240121,240122,240123,
                240301,240302,240303,240304,240305,240306,240307,240308,240309,
                240311,240312,240314,240315,240316,240317,240318,240319,240320,
                240321,240322,240323,
                240401,240402,240403,240404,240405,240406,240407,240408,240409,
                240411,240412,240414,240415,240416,240417,240418,240419,240420,
                240421,240422,240423,
                260101,260102,260103,260104,
                260201,260202,260203,260204,260205,260206,260207,260208,260209,
                260210,260211,260212,260213,260214,260215,260216,260217,260218,260219,
                260501,260502,260503,260504,260505,260506,260507,260508,260509,
                260510,260511,260512,260513,260514,260515,260516,260517,
                260701,260702,260703,260704,
                260901,260902,260903,260904,260905,260906,260907,260908,260909,
                260910,260911,260912,260913,260914,260915,260916,260917,260918,260919
            )
            AND t.blnActivo = true
            ORDER BY UPPER(TRIM(t.descModalidad)) ASC
            """)
    List<ClasifProductoTraDTO> listadoTipoTramite();

    List<CatRestriccionTtra> findByBlnActivoTrueAndIdTipoTramiteId(Long idTipoTramite);

}
