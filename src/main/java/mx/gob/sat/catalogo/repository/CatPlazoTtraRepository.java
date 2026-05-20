package mx.gob.sat.catalogo.repository;

import mx.gob.sat.catalogo.model.entity.CatPlazoTtra;
import mx.gob.sat.catalogo.model.entity.CatPlazoTtraId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CatPlazoTtraRepository extends JpaRepository<CatPlazoTtra, CatPlazoTtraId> {
}
