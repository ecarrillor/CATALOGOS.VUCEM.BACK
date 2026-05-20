package mx.gob.sat.catalogo.repository;

import mx.gob.sat.catalogo.model.entity.CatVigenciaServicio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CatVigenciaServicioRepository extends JpaRepository<CatVigenciaServicio, Short> {
}
