package com.example.vucem_catalogos_service.business.Interface;

import com.example.vucem_catalogos_service.model.dto.SelectDTO;
import com.example.vucem_catalogos_service.model.entity.CatEntidad;
import com.example.vucem_catalogos_service.model.entity.CatPais;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ICatEntidadService {

    Page<CatEntidad> catEntidadListAll(int page, int size, String search);

    CatEntidad crearEntidad(CatEntidad entidad);

    CatEntidad findByCveEntidad(String cveEntidad);

    CatEntidad updateEntidad(String cveEntidad, CatEntidad catEntidad);

    List<CatPais> findAllPaises();

    List<SelectDTO> listNombres();

}
