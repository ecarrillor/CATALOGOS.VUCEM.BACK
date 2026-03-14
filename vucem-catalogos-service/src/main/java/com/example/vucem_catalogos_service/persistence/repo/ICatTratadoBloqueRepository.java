package com.example.vucem_catalogos_service.persistence.repo;

import com.example.vucem_catalogos_service.model.dto.CatTratadoBloqueDTO;
import com.example.vucem_catalogos_service.model.entity.CatTratadoBloque;
import com.example.vucem_catalogos_service.model.entity.CatTratadoBloqueId;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface ICatTratadoBloqueRepository extends JpaRepository<CatTratadoBloque, CatTratadoBloqueId> {

    @Query("""
            SELECT new com.example.vucem_catalogos_service.model.dto.CatTratadoBloqueDTO(
                e.id.idTratadoAcuerdo,
                e.id.idBloque,
                e.idTratadoAcuerdo.nombre,
                e.idBloque.nombre,
                e.fecIniVigencia,
                e.fecFinVigencia,
                e.blnActivo
            )
            FROM CatTratadoBloque e
            WHERE (:search IS NULL OR
                LOWER(CAST(e.id.idTratadoAcuerdo AS string)) LIKE LOWER(CONCAT('%', CAST(:search AS string), '%')) OR
                LOWER(CAST(e.id.idBloque AS string)) LIKE LOWER(CONCAT('%', CAST(:search AS string), '%')) OR
                LOWER(e.idTratadoAcuerdo.nombre) LIKE LOWER(CONCAT('%', CAST(:search AS string), '%')) OR
                LOWER(e.idBloque.nombre) LIKE LOWER(CONCAT('%', CAST(:search AS string), '%')) OR
                LOWER(CAST(e.fecIniVigencia AS string)) LIKE LOWER(CONCAT('%', CAST(:search AS string), '%')) OR
                LOWER(CAST(e.fecFinVigencia AS string)) LIKE LOWER(CONCAT('%', CAST(:search AS string), '%')))
              AND (:activo IS NULL OR e.blnActivo = :activo)
            """)
    Page<CatTratadoBloqueDTO> search(
            @Param("search") String search,
            @Param("activo") Boolean activo,
            Pageable pageable
    );

    @Query("""
            SELECT new com.example.vucem_catalogos_service.model.dto.CatTratadoBloqueDTO(
                e.id.idTratadoAcuerdo,
                e.id.idBloque,
                e.idTratadoAcuerdo.nombre,
                e.idBloque.nombre,
                e.fecIniVigencia,
                e.fecFinVigencia,
                e.blnActivo
            )
            FROM CatTratadoBloque e
            WHERE e.id.idTratadoAcuerdo = :idTratadoAcuerdo
              AND e.id.idBloque          = :idBloque
            """)
    Optional<CatTratadoBloqueDTO> findByTratadoBloqueDTO(
            @Param("idTratadoAcuerdo") Short idTratadoAcuerdo,
            @Param("idBloque") Short idBloque
    );
}
