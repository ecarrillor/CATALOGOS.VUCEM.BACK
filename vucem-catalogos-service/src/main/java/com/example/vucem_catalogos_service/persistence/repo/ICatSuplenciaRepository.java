package com.example.vucem_catalogos_service.persistence.repo;

import com.example.vucem_catalogos_service.model.dto.CatSuplenciaDTO;
import com.example.vucem_catalogos_service.model.entity.CatSuplencia;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ICatSuplenciaRepository extends JpaRepository<CatSuplencia, Short> {

    @Query("SELECT new com.example.vucem_catalogos_service.model.dto.CatSuplenciaDTO(" +
            "e.id, e.idDependencia.id, e.idDependencia.nombre, e.texto, e.fecIniVigencia, e.fecFinVigencia, e.blnActivo) " +
            "FROM CatSuplencia e " +
            "WHERE (:activo IS NULL OR e.blnActivo = :activo) " +
            "AND (:search IS NULL OR :search = '' OR LOWER(e.texto) LIKE LOWER(CONCAT('%', :search, '%')))")
    Page<CatSuplenciaDTO> search(@Param("search") String search,
                                 @Param("activo") Boolean activo,
                                 Pageable pageable);

    @Query("SELECT new com.example.vucem_catalogos_service.model.dto.CatSuplenciaDTO(" +
            "e.id, e.idDependencia.id, e.idDependencia.nombre, e.texto, e.fecIniVigencia, e.fecFinVigencia, e.blnActivo) " +
            "FROM CatSuplencia e " +
            "WHERE e.id = :id")
    CatSuplenciaDTO findBySuplenciaDTO(@Param("id") Short id);
}
