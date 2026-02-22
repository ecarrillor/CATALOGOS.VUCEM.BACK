package com.example.vucem_catalogos_service.persistence.repo;

import com.example.vucem_catalogos_service.model.dto.CatUsoEspecMercanciaTtraDTO;
import com.example.vucem_catalogos_service.model.entity.CatUsoEspecMercanciaTtra;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ICatUsoEspecMercanciaTtraRepository extends JpaRepository<CatUsoEspecMercanciaTtra, Short> {

    @Query("SELECT new com.example.vucem_catalogos_service.model.dto.CatUsoEspecMercanciaTtraDTO(" +
            "e.id, e.idTipoTramite.id, e.idTipoTramite.descServicio, " +
            "e.descUsoEspMercancia, e.fecIniVigencia, e.fecFinVigencia, e.blnActivo, e.blnReqRegistroSanitario) " +
            "FROM CatUsoEspecMercanciaTtra e " +
            "WHERE (:activo IS NULL OR e.blnActivo = :activo) " +
            "AND (:search IS NULL OR :search = '' OR LOWER(e.descUsoEspMercancia) LIKE LOWER(CONCAT('%', :search, '%')))")
    Page<CatUsoEspecMercanciaTtraDTO> search(@Param("search") String search,
                                              @Param("activo") Boolean activo,
                                              Pageable pageable);

    @Query("SELECT new com.example.vucem_catalogos_service.model.dto.CatUsoEspecMercanciaTtraDTO(" +
            "e.id, e.idTipoTramite.id, e.idTipoTramite.descServicio, " +
            "e.descUsoEspMercancia, e.fecIniVigencia, e.fecFinVigencia, e.blnActivo, e.blnReqRegistroSanitario) " +
            "FROM CatUsoEspecMercanciaTtra e " +
            "WHERE e.id = :id")
    CatUsoEspecMercanciaTtraDTO findByUsoEspecMercanciaTtraDTO(@Param("id") Short id);
}
