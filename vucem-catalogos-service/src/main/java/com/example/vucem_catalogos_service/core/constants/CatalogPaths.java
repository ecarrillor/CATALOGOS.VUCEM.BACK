package com.example.vucem_catalogos_service.core.constants;

public class CatalogPaths extends ConstPath{
    private CatalogPaths() {

    }
    public static final String CONTROLLER = BASE_API_PATH + "/catalogs";
    public static final String CONTROLLER_CATALOG_APROB_CERT = BASE_API_PATH + "/catAprCerSe";

    public static final String CREATE = "/ctc01/{catalog}";
    public static final String UPDATE = "/ctu01/{catalog}/{id}";
    public static final String FIND_BY_ID = "/ctd01/{catalog}/{id}";
    public static final String LIST   = "/ctl01/{catalog}";

    public static final String LIST_CATALOGO_ENTIDAD = "/cat_entidad/list";
    public static final String SAVE_CATALOGO_ENTIDAD = "/cat_entidad/save";
    public static final String FIND_CATALOGO_ENTIDAD = "/cat_entidad/{cveEntidad}";
    public static final String UPDATE_CATALOGO_ENTIDAD = "/cat_entidad/{cveEntidad}";
    public static final String PAISES_CATALOGO_ENTIDAD = "/cat_entidad/paises";

    public static final String LIST_CATALOGO_ADUANA = "/cat_aduana/list";
    public static final String SAVE_CATALOGO_ADUANA = "/cat_aduana/save";
    public static final String FIND_CATALOGO_ADUANA = "/cat_aduana/{cveEntidad}";
    public static final String UPDATE_CATALOGO_ADUANA = "/cat_aduana/{cveEntidad}";


    /*entidad CAT_APROB_CERT_SE */
    public static final String LIST_CATALOGO_APROB_CERT = "/cat_aprobCert/list";
    public static final String SAVE_CATALOGO_APROB_CERT = "/cat_aprobCert/save";
    public static final String FIND_CATALOGO_APROB_CERT = "/cat_aprobCert/{id}";
    public static final String UPDATE_CATALOGO_APROB_CERT = "/cat_aprobCert/{id}";

    /*entidad CAT_UNIDAD_ADMINISTRATIVA */
    public static final String LIST_UNIDAD_ADMIN_LIST_LAB = "/cat_unidad_adm/listLab";


}
