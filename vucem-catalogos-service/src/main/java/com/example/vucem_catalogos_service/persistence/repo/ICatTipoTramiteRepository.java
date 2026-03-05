package com.example.vucem_catalogos_service.persistence.repo;

import com.example.vucem_catalogos_service.model.dto.ClasifProductoTraDTO;
import com.example.vucem_catalogos_service.model.entity.CatTipoTramite;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ICatTipoTramiteRepository extends JpaRepository<CatTipoTramite, Long>,
        JpaSpecificationExecutor<CatTipoTramite> {


    @Query("""
    SELECT t
    FROM CatTipoTramite t
    WHERE t.blnActivo = true
      AND t.descModalidad IS NOT NULL
      AND TRIM(t.descModalidad) <> ''
    ORDER BY t.id DESC
""")
    List<CatTipoTramite> findByBlnActivoTrueOrderByIdDesc();

    List<CatTipoTramite> findByBlnActivoTrue();


    @Query("""
    SELECT DISTINCT new com.example.vucem_catalogos_service.model.dto.ClasifProductoTraDTO(
        t.id,
        CONCAT(
            t.id, ' ',
            CASE 
                WHEN t.descModalidad IS NULL 
                THEN t.descSubservicio 
                ELSE t.descModalidad 
            END
        )
    )
    FROM CatTipoTramite t
    WHERE t.blnActivo = true
    AND t.id IN (
    120103,120302,
        220101,220102,220103,220201,220202,220203,220301,
        220401,220402,220403,220802,220803,220901,220902,
        220903,221601,221602,221603,
        230101,230201,230202,230401,230501,230601,230602,
        230603,230604,230605,230901,230902,230903,231001,
        231002,231003,
        240101,240102,240103,240104,240105,240106,240107,
        240108,240109,240111,240112,240114,240115,240116,
        240117,240118,240119,240120,240121,240122,240123,
        240301,240302,240303,240304,240305,240306,240307,
        240308,240309,240311,240312,240314,240315,240316,
        240317,240318,240319,240320,240321,240322,240323,
        240401,240402,240403,240404,240405,240406,240407,
        240408,240409,240411,240412,240414,240415,240416,
        240417,240418,240419,240420,240421,240422,240423,
        250101,250102,250103,250104,250105,
        260101,260102,260103,260104,
        260201,260202,260203,260204,260205,260206,260207,
        260208,260209,260210,260211,260212,260213,260214,
        260215,260216,260217,260218,260219,
        260301,260302,260303,260304,
        260401,260402,
        260501,260502,260503,260504,260505,260506,260507,
        260508,260509,260510,260511,260512,260513,260514,
        260515,260516,260517,
        260601,260602,260603,260604,
        260701,260702,260703,260704,
        260901,260902,260903,260904,260905,260906,260907,
        260908,260909,260910,260911,260912,260913,260914,
        260915,260916,260917,260918,260919,
        261101,261102,261103,261104,
        261201,261202,261203,261204,
        261301,261302,261303,261304,
        261401,261402,
        270101,270201,270301,270401,270501,
        280101,
        290201,
        300103,300105,300106,300107,300108
    )
   ORDER BY CONCAT(
                  t.id, ' ',
                  CASE\s
                      WHEN t.descModalidad IS NULL\s
                      THEN t.descSubservicio\s
                      ELSE t.descModalidad\s
                  END
          )
""")
    List<ClasifProductoTraDTO> listadoTipoTramite();

}
