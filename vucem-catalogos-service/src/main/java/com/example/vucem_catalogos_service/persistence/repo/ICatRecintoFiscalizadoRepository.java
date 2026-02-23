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
            WHERE (:search IS NULL OR LOWER(e.nombre) LIKE LOWER(CONCAT('%', :search, '%'))
                OR LOWER(e.rfc) LIKE LOWER(CONCAT('%', :search, '%'))
                OR LOWER(e.ideTipoRecintoFiscalizado) LIKE LOWER(CONCAT('%', :search, '%')))
            AND (:activo IS NULL OR e.blnActivo = :activo)
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
