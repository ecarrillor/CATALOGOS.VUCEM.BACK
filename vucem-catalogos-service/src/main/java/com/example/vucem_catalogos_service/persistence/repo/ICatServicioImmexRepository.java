package com.example.vucem_catalogos_service.persistence.repo;

import com.example.vucem_catalogos_service.model.dto.CatServicioImmexDTO;
import com.example.vucem_catalogos_service.model.entity.CatServicioImmex;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ICatServicioImmexRepository extends JpaRepository<CatServicioImmex, Short> {

    @Query(value = "SELECT new com.example.vucem_catalogos_service.model.dto.CatServicioImmexDTO(" +
            "e.id, e.nombre, e.fecIniVigencia, e.ideTipoServicioImmex, e.fecFinVigencia, e.blnActivo) " +
            "FROM CatServicioImmex e " +
            "WHERE (:activo IS NULL OR e.blnActivo = :activo) " +
            "AND (:search IS NULL OR :search = '' " +
            "OR LOWER(CAST(e.id AS string)) LIKE :search " +
            "OR LOWER(e.nombre) LIKE :search " +
            "OR LOWER(CAST(e.fecIniVigencia AS string)) LIKE :search " +
            "OR LOWER(e.ideTipoServicioImmex) LIKE :search " +
            "OR LOWER(CAST(e.fecFinVigencia AS string)) LIKE :search)",
            countQuery = "SELECT COUNT(e) FROM CatServicioImmex e  WHERE (:activo IS NULL OR e.blnActivo = :activo)  AND (:search IS NULL OR :search = ''  OR LOWER(CAST(e.id AS string)) LIKE :search  OR LOWER(e.nombre) LIKE :search  OR LOWER(CAST(e.fecIniVigencia AS string)) LIKE :search  OR LOWER(e.ideTipoServicioImmex) LIKE :search  OR LOWER(CAST(e.fecFinVigencia AS string)) LIKE :search)")
    Page<CatServicioImmexDTO> search(@Param("search") String search,
                                     @Param("activo") Boolean activo,
                                     Pageable pageable);

    @Query("SELECT new com.example.vucem_catalogos_service.model.dto.CatServicioImmexDTO(" +
            "e.id, e.nombre, e.fecIniVigencia, e.ideTipoServicioImmex, e.fecFinVigencia, e.blnActivo) " +
            "FROM CatServicioImmex e " +
            "WHERE e.id = :id")
    CatServicioImmexDTO findByServicioImmexDTO(@Param("id") Short id);
}
