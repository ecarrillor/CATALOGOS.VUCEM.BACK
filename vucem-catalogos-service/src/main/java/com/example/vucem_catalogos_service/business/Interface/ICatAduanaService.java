package com.example.vucem_catalogos_service.business.Interface;

import com.example.vucem_catalogos_service.model.entity.CatAduana;
import com.example.vucem_catalogos_service.model.entity.CatEntidad;
import com.example.vucem_catalogos_service.model.entity.CatTipoAduana;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ICatAduanaService {

    Page<CatAduana> catAduanaListAll(int page, int size, String search);
    CatAduana crearAduana (CatAduana catAduana);

    CatAduana findByCveAduana(String cveAduana);

    CatAduana updateAduana(String cveAduana, CatAduana request);

    List<CatTipoAduana> getAllTiposAduana();

    List<CatEntidad> getAllEntidades();
}
