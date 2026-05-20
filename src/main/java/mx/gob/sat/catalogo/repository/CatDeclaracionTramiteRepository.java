package mx.gob.sat.catalogo.repository;

import mx.gob.sat.catalogo.model.entity.CatDeclaracionTramite;
import mx.gob.sat.catalogo.model.entity.CatDeclaracionTramiteId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CatDeclaracionTramiteRepository extends JpaRepository<CatDeclaracionTramite, CatDeclaracionTramiteId> {
}
