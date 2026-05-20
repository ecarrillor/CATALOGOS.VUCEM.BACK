package mx.gob.sat.catalogo.repository;

import mx.gob.sat.catalogo.model.entity.CatJustificacionTtra;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CatJustificacionTtraRepository extends JpaRepository<CatJustificacionTtra, Long> {
}
