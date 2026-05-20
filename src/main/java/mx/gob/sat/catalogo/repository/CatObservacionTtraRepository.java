package mx.gob.sat.catalogo.repository;

import mx.gob.sat.catalogo.model.entity.CatObservacionTtra;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CatObservacionTtraRepository extends JpaRepository<CatObservacionTtra, Long> {
}
