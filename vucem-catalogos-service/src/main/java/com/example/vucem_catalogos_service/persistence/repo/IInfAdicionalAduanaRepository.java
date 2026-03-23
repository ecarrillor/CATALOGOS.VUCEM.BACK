package com.example.vucem_catalogos_service.persistence.repo;

import com.example.vucem_catalogos_service.model.dto.InfAdicionalAduanaDTO;
import com.example.vucem_catalogos_service.model.entity.InfAdicionalAduana;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IInfAdicionalAduanaRepository extends JpaRepository<InfAdicionalAduana, String> {

    @Query(value = """
            SELECT new com.example.vucem_catalogos_service.model.dto.InfAdicionalAduanaDTO(
                e.cveAduana,
                e.catAduana.nombre,
                e.correoNotificacion,
                e.blnCuentaRni,
                e.tagAduana
            )
            FROM InfAdicionalAduana e
            WHERE (:texto IS NULL
                OR LOWER(e.catAduana.nombre) LIKE LOWER(CONCAT('%', CAST(:texto AS string), '%'))
                OR LOWER(e.correoNotificacion) LIKE LOWER(CONCAT('%', CAST(:texto AS string), '%'))
                OR LOWER(e.tagAduana) LIKE LOWER(CONCAT('%', CAST(:texto AS string), '%')))
            """,
            countQuery = """
            SELECT COUNT(e)
            FROM InfAdicionalAduana e
            WHERE (:texto IS NULL
                OR LOWER(e.catAduana.nombre) LIKE LOWER(CONCAT('%', CAST(:texto AS string), '%'))
                OR LOWER(e.correoNotificacion) LIKE LOWER(CONCAT('%', CAST(:texto AS string), '%'))
                OR LOWER(e.tagAduana) LIKE LOWER(CONCAT('%', CAST(:texto AS string), '%')))
            """)
    Page<InfAdicionalAduanaDTO> search(@Param("texto") String texto, Pageable pageable);

    @Query("""
            SELECT new com.example.vucem_catalogos_service.model.dto.InfAdicionalAduanaDTO(
                e.cveAduana,
                e.catAduana.nombre,
                e.correoNotificacion,
                e.blnCuentaRni,
                e.tagAduana
            )
            FROM InfAdicionalAduana e
            WHERE e.cveAduana = :cveAduana
            """)
    Optional<InfAdicionalAduanaDTO> findByInfAdicionalAduanaDTO(@Param("cveAduana") String cveAduana);
}
