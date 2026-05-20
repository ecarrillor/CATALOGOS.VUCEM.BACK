package mx.gob.sat.catalogo.repository;

import mx.gob.sat.catalogo.model.entity.CatDocumentoTramite;
import mx.gob.sat.catalogo.model.entity.CatDocumentoTramiteId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CatDocumentoTramiteRepository extends JpaRepository<CatDocumentoTramite, CatDocumentoTramiteId> {
}
