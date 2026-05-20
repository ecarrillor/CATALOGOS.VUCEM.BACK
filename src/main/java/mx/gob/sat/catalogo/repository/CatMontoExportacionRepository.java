package mx.gob.sat.catalogo.repository;

import mx.gob.sat.catalogo.model.entity.CatMontoExportacion;
import mx.gob.sat.catalogo.model.entity.CatMontoExportacionId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CatMontoExportacionRepository extends JpaRepository<CatMontoExportacion, CatMontoExportacionId> {
}
