package mx.gob.sat.catalogo.repository;

import mx.gob.sat.catalogo.model.entity.CatPlazoMaximoAutTramite;
import mx.gob.sat.catalogo.model.entity.CatPlazoMaximoAutTramiteId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CatPlazoMaximoAutTramiteRepository extends JpaRepository<CatPlazoMaximoAutTramite, CatPlazoMaximoAutTramiteId> {
}
