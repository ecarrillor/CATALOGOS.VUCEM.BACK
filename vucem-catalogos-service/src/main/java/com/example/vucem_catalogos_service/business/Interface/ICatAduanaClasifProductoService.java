package com.example.vucem_catalogos_service.business.Interface;

import com.example.vucem_catalogos_service.model.entity.CatAduanaClasifProd;
import org.springframework.data.domain.Page;

public interface ICatAduanaClasifProductoService {

    Page<CatAduanaClasifProd> catAduanaListAll(int page, int size, String search);

    CatAduanaClasifProd crearAduanaClasifProducto(CatAduanaClasifProd aduanaClasifProd);
}
