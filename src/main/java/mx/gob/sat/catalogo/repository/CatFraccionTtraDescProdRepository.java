package mx.gob.sat.catalogo.repository;

import mx.gob.sat.catalogo.model.entity.CatFraccionTtraDescProd;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CatFraccionTtraDescProdRepository extends JpaRepository<CatFraccionTtraDescProd, Long> {
}
