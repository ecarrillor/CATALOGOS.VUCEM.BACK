package mx.gob.sat.catalogo.repository;

import mx.gob.sat.catalogo.model.entity.CatTipoCertificado;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CatTipoCertificadoRepository extends JpaRepository<CatTipoCertificado, Long> {
}
