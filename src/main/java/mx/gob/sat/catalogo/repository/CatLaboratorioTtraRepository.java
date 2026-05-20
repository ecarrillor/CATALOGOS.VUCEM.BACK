package mx.gob.sat.catalogo.repository;

import mx.gob.sat.catalogo.model.entity.CatLaboratorioTtra;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CatLaboratorioTtraRepository extends JpaRepository<CatLaboratorioTtra, Long> {
}
