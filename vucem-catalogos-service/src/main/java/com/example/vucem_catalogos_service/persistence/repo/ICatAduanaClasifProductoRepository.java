package com.example.vucem_catalogos_service.persistence.repo;

import com.example.vucem_catalogos_service.model.entity.CatAduanaClasifProd;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface ICatAduanaClasifProductoRepository extends JpaRepository<CatAduanaClasifProd, Long>, JpaSpecificationExecutor<CatAduanaClasifProd> {
}
