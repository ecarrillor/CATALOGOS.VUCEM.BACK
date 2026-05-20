package mx.gob.sat.catalogo.repository;

import mx.gob.sat.catalogo.model.entity.CatIsotopoFraccion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CatIsotopoFraccionRepository extends JpaRepository<CatIsotopoFraccion, Short> {
}
