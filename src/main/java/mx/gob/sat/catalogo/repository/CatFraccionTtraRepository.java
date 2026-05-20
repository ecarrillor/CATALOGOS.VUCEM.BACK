package mx.gob.sat.catalogo.repository;

import mx.gob.sat.catalogo.model.entity.CatFraccionTtra;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CatFraccionTtraRepository extends JpaRepository<CatFraccionTtra, Long> {
}
