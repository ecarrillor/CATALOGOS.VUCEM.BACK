package com.example.vucem_catalogos_service.core.constants;

public class CatalogPaths extends ConstPath{
    private CatalogPaths() {

    }
    public static final String CONTROLLER = BASE_API_PATH + "/catalogs";

    public static final String CREATE = "/ctc01/{catalog}";
    public static final String UPDATE = "/ctu01/{catalog}";
    public static final String DELETE = "/ctd01/{catalog}/{id}";
    public static final String LIST   = "/ctl01/{catalog}";
}
