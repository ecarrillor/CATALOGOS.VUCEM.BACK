package com.example.vucem_catalogos_service.persistence.repo;

import com.example.vucem_catalogos_service.model.dto.CatPlazoMaximoAutTramiteDTO;
import com.example.vucem_catalogos_service.model.entity.CatPlazoMaximoAutTramite;
import com.example.vucem_catalogos_service.model.entity.CatPlazoMaximoAutTramiteId;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.Instant;
import java.time.LocalDate;

@Repository
public interface ICatPlazoMaximoAutTramiteRepository extends JpaRepository<CatPlazoMaximoAutTramite, CatPlazoMaximoAutTramiteId> {

    @Query("SELECT new com.example.vucem_catalogos_service.model.dto.CatPlazoMaximoAutTramiteDTO(" +
            "e.id.idTipoTramite, e.id.fecIniVigencia, e.idTipoTramite.descServicio, " +
            "e.fecFinVigencia, e.plazoAnios, e.idePlazoMeses, e.blnIlimitado, e.plazo, e.blnAsignacionFechaFin, e.blnActivo) " +
            "FROM CatPlazoMaximoAutTramite e " +
            "WHERE (:activo IS NULL OR e.blnActivo = :activo) " +
            "AND (:search IS NULL OR :search = '' OR LOWER(e.idePlazoMeses) LIKE LOWER(CONCAT('%', :search, '%')) " +
            "OR LOWER(e.plazo) LIKE LOWER(CONCAT('%', :search, '%'))" +
            ")")
    Page<CatPlazoMaximoAutTramiteDTO> search(@Param("search") String search,
                                              @Param("activo") Boolean activo,
                                              Pageable pageable);

    @Query("SELECT new com.example.vucem_catalogos_service.model.dto.CatPlazoMaximoAutTramiteDTO(" +
            "e.id.idTipoTramite, e.id.fecIniVigencia, e.idTipoTramite.descServicio, " +
            "e.fecFinVigencia, e.plazoAnios, e.idePlazoMeses, e.blnIlimitado, e.plazo, e.blnAsignacionFechaFin, e.blnActivo) " +
            "FROM CatPlazoMaximoAutTramite e " +
            "WHERE e.id.idTipoTramite = :idTipoTramite AND e.id.fecIniVigencia = :fecIniVigencia")
    CatPlazoMaximoAutTramiteDTO findByPlazoMaximoAutTramiteDTO(@Param("idTipoTramite") Long idTipoTramite,
                                                                @Param("fecIniVigencia") LocalDate fecIniVigencia);
}
