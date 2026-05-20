package mx.gob.sat.catalogo.repository;

import mx.gob.sat.catalogo.model.entity.CatSubdivisionFraccion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CatSubdivisionFraccionRepository extends JpaRepository<CatSubdivisionFraccion, String> {
}
