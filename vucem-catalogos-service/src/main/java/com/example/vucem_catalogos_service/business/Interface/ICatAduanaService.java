package com.example.vucem_catalogos_service.business.Interface;

import com.example.vucem_catalogos_service.model.entity.CatAduana;
import org.springframework.data.domain.Page;

public interface ICatAduanaService {

    Page<CatAduana> catAduanaListAll(int page, int size, String search);
}
