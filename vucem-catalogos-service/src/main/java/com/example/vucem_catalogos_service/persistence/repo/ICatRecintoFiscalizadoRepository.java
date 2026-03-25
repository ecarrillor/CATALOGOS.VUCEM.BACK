package com.example.vucem_catalogos_service.persistence.repo;

import com.example.vucem_catalogos_service.model.dto.CatRecintoFiscalizadoDTO;
import com.example.vucem_catalogos_service.model.entity.CatRecintoFiscalizado;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ICatRecintoFiscalizadoRepository extends JpaRepository<CatRecintoFiscalizado, Long> {

    @Query(value = """
            SELECT new com.example.vucem_catalogos_service.model.dto.CatRecintoFiscalizadoDTO(
                e.id,
                e.cveAduana.cveAduana,
                e.cveAduana.nombre,
                e.ideTipoRecintoFiscalizado,
                e.nombre,
                e.rfc,
                e.numAutorizacion,
                e.fecIniVigencia,
                e.fecFinVigencia,
                e.blnActivo,
                e.codCamir,
                e.blnComRfMf,
                e.correoElectronico,
                e.descUrl,
                e.tipo
            )
            FROM CatRecintoFiscalizado e
            WHERE
                                       (
                                                   :search IS NULL OR
                                                   LOWER(CAST(e.id AS string)) LIKE :search OR
                                                   LOWER(e.cveAduana.cveAduana) LIKE :search OR
                                                   LOWER(e.cveAduana.nombre) LIKE :search OR
                                                   LOWER(e.ideTipoRecintoFiscalizado) LIKE :search OR
                                                   LOWER(e.nombre) LIKE :search OR
                                                   LOWER(e.rfc) LIKE :search OR
                                                   LOWER(e.numAutorizacion) LIKE :search OR
                                                   LOWER(CAST(e.fecIniVigencia AS string)) LIKE :search OR
                                                   LOWER(CAST(e.fecFinVigencia AS string)) LIKE :search OR
                                                   LOWER(e.codCamir) LIKE :search OR
                                                   LOWER(e.correoElectronico) LIKE :search OR
                                                   LOWER(e.descUrl) LIKE :search OR
                                                   LOWER(e.tipo) LIKE :search
                                      )
            """,
            countQuery = """
            SELECT COUNT(e)
            FROM CatRecintoFiscalizado e
            WHERE
                                       (
                                                   :search IS NULL OR
                                                   LOWER(CAST(e.id AS string)) LIKE :search OR
                                                   LOWER(e.cveAduana.cveAduana) LIKE :search OR
                                                   LOWER(e.cveAduana.nombre) LIKE :search OR
                                                   LOWER(e.ideTipoRecintoFiscalizado) LIKE :search OR
                                                   LOWER(e.nombre) LIKE :search OR
                                                   LOWER(e.rfc) LIKE :search OR
                                                   LOWER(e.numAutorizacion) LIKE :search OR
                                                   LOWER(CAST(e.fecIniVigencia AS string)) LIKE :search OR
                                                   LOWER(CAST(e.fecFinVigencia AS string)) LIKE :search OR
                                                   LOWER(e.codCamir) LIKE :search OR
                                                   LOWER(e.correoElectronico) LIKE :search OR
                                                   LOWER(e.descUrl) LIKE :search OR
                                                   LOWER(e.tipo) LIKE :search
                                      )
            """)
    Page<CatRecintoFiscalizadoDTO> search(@Param("search") String search,
                                           @Param("activo") Boolean activo,
                                           Pageable pageable);

    @Query("""
            SELECT new com.example.vucem_catalogos_service.model.dto.CatRecintoFiscalizadoDTO(
                e.id,
                e.cveAduana.cveAduana,
                e.cveAduana.nombre,
                e.ideTipoRecintoFiscalizado,
                e.nombre,
                e.rfc,
                e.numAutorizacion,
                e.fecIniVigencia,
                e.fecFinVigencia,
                e.blnActivo,
                e.codCamir,
                e.blnComRfMf,
                e.correoElectronico,
                e.descUrl,
                e.tipo
            )
            FROM CatRecintoFiscalizado e
            WHERE e.id = :id
            """)
    Optional<CatRecintoFiscalizadoDTO> findByRecintoFiscalizadoDTO(@Param("id") Long id);
}
