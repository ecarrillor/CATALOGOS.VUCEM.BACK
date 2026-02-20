package com.example.vucem_catalogos_service.persistence.repo;

import com.example.vucem_catalogos_service.model.dto.LocalidadDTO;
import com.example.vucem_catalogos_service.model.entity.CatLocalidad;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ICatLocalidadRepository extends JpaRepository<CatLocalidad, String> {

    @Query("""
                SELECT new com.example.vucem_catalogos_service.model.dto.LocalidadDTO(
                    loc.cp,
                    loc.nombre
                )
                FROM CatLocalidad loc
                JOIN loc.cveDelegMun dm
                JOIN dm.cveEntidad en
                JOIN en.cvePais pais
                WHERE pais.cvePais = :cvePais
                  AND en.cveEntidad = :cveEntidad
                  AND dm.cveDelegMun = :cveMunicipio
                  AND loc.blnActivo = true
            """)
    List<LocalidadDTO> findLocalidades(@Param("cvePais") String cvePais,
                                       @Param("cveEntidad") String cveEntidad,
                                       @Param("cveMunicipio") String cveMunicipio);

}
