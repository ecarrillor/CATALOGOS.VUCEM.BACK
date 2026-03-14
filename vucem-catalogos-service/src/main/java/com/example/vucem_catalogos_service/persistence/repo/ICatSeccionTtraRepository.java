package com.example.vucem_catalogos_service.persistence.repo;

import com.example.vucem_catalogos_service.model.dto.CatSeccionTtraDTO;
import com.example.vucem_catalogos_service.model.entity.CatSeccionTtra;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ICatSeccionTtraRepository extends JpaRepository<CatSeccionTtra, Integer> {

    @Query("SELECT new com.example.vucem_catalogos_service.model.dto.CatSeccionTtraDTO(" +
            "e.id, e.idTipoTramite.id, e.idTipoTramite.descServicio, " +
            "e.descSeccion, e.fecIniVigencia, e.fecFinVigencia, e.blnActivo) " +
            "FROM CatSeccionTtra e " +
            "WHERE (:activo IS NULL OR e.blnActivo = :activo) " +
            "AND (:search IS NULL OR :search = '' OR LOWER(e.descSeccion) LIKE LOWER(CONCAT('%', :search, '%')))")
    Page<CatSeccionTtraDTO> search(@Param("search") String search,
                                   @Param("activo") Boolean activo,
                                   Pageable pageable);

    @Query("SELECT new com.example.vucem_catalogos_service.model.dto.CatSeccionTtraDTO(" +
            "e.id, e.idTipoTramite.id, e.idTipoTramite.descServicio, " +
            "e.descSeccion, e.fecIniVigencia, e.fecFinVigencia, e.blnActivo) " +
            "FROM CatSeccionTtra e " +
            "WHERE e.id = :id")
    CatSeccionTtraDTO findBySeccionTtraDTO(@Param("id") Integer id);
}
