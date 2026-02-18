package com.example.vucem_catalogos_service.core.constants;

public class CatalogPaths extends ConstPath{
    private CatalogPaths() {

    }
    public static final String CONTROLLER = BASE_API_PATH + "/catalogs";

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
    public static final String FIND_CATALOGO_ADUANA = "/cat_aduana/{cveAduana}";
    public static final String UPDATE_CATALOGO_ADUANA = "/cat_aduana/{cveAduana}";
    public static final String LIST_TIPOS_ADUANA = "/cat_aduana/tipos_aduana";
    public static final String LIST_ENTIDADES = "/cat_aduana/entidades";

    public static final String LIST_CATALOGO_ADUANA_CLASIF_PRODUCTO = "/cat_aduana_clasif/list";
    public static final String SAVE_CATALOGO_ADUANA_CLASIF_PRODUCTO = "/cat_aduana_clasif/save";
    public static final String FIND_CATALOGO_ADUANA_CLASIF_PRODUCTO = "/cat_aduana_clasif/{cveAduana}";
    public static final String UPDATE_CATALOGO_ADUANA_CLASIF_PRODUCTO = "/cat_aduana_clasif/{cveAduana}";
    public static final String LIST_TIPOS_ADUANA_CLASIF_PRODUCTO = "/cat_aduana_clasif/tipos_aduana";
    public static final String LIST_ENTIDADES_CLASIF_PRODUCTO = "/cat_aduana_clasif/entidades";
}
