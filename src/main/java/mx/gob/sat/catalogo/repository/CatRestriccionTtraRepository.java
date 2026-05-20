package mx.gob.sat.catalogo.repository;

import mx.gob.sat.catalogo.model.entity.CatRestriccionTtra;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CatRestriccionTtraRepository extends JpaRepository<CatRestriccionTtra, Short> {
}
