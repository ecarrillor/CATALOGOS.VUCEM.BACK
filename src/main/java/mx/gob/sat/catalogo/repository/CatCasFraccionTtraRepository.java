package mx.gob.sat.catalogo.repository;

import mx.gob.sat.catalogo.model.entity.CatCasFraccionTtra;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CatCasFraccionTtraRepository extends JpaRepository<CatCasFraccionTtra, Short> {
}
