package com.example.vucem_catalogos_service.core.constants;

public class CatalogPaths extends ConstPath{
    private CatalogPaths() {

    }
    public static final String CONTROLLER = BASE_API_PATH + "/catalogs";

    public static final String CREATE = "/ctc01/{catalog}";
    public static final String UPDATE = "/ctu01/{catalog}/{id}";
    public static final String FIND_BY_ID = "/ctd01/{catalog}/{id}";
    public static final String LIST   = "/ctl01/{catalog}";

    /*entidad CAT_ENTIDAD */
    public static final String LIST_CATALOGO_ENTIDAD = "/cat_entidad/list";
    public static final String SAVE_CATALOGO_ENTIDAD = "/cat_entidad/save";
    public static final String FIND_CATALOGO_ENTIDAD = "/cat_entidad/{cveEntidad}";
    public static final String UPDATE_CATALOGO_ENTIDAD = "/cat_entidad/{cveEntidad}";
    public static final String PAISES_CATALOGO_ENTIDAD = "/cat_entidad/paises";

    /*entidad CAT_ADUANA */
    public static final String LIST_CATALOGO_ADUANA = "/cat_aduana/list";
    public static final String SAVE_CATALOGO_ADUANA = "/cat_aduana/save";
    public static final String FIND_CATALOGO_ADUANA = "/cat_aduana/{cveEntidad}";
    public static final String UPDATE_CATALOGO_ADUANA = "/cat_aduana/{cveEntidad}";
    public static final String LIST_TIPOS_ADUANA = "/cat_aduana/tipos_aduana";
    public static final String LIST_ENTIDADES = "/cat_aduana/entidades";

    /*entidad CAT_ADUANA_CLASIF_PRODUCTO */
    public static final String LIST_CATALOGO_ADUANA_CLASIF_PRODUCTO = "/cat_aduana_clasif/list";
    public static final String SAVE_CATALOGO_ADUANA_CLASIF_PRODUCTO = "/cat_aduana_clasif/save";
    public static final String FIND_CATALOGO_ADUANA_CLASIF_PRODUCTO = "/cat_aduana_clasif/{id}";
    public static final String UPDATE_CATALOGO_ADUANA_CLASIF_PRODUCTO = "/cat_aduana_clasif/{id}";
    public static final String LIST_ADUANA = "/cat_aduana_clasif/aduanas";
    public static final String LIST_CLASIFICACION_PRODUCTO = "/cat_aduana_clasif/clasificaciones_producto";

    /*entidad CAT_ADUANA_TRAMITE */
    public static final String LIST_ADUANA_TRAMITE = "/cat_aduana_tramite/list";
    public static final String SAVE_ADUANA_TRAMITE = "/cat_aduana_tramite/save";
    public static final String FIND_ADUANA_TRAMITE = "/cat_aduana_tramite/{cveEntidad}";
    public static final String UPDATE_ADUANA_TRAMITE = "/cat_aduana_tramite/{cveEntidad}";


    /*entidad CAT_APROB_CERT_SE */
    public static final String LIST_CATALOGO_APROB_CERT = "/cat_aprobCert/list";
    public static final String SAVE_CATALOGO_APROB_CERT = "/cat_aprobCert/save";
    public static final String FIND_CATALOGO_APROB_CERT = "/cat_aprobCert/{id}";
    public static final String UPDATE_CATALOGO_APROB_CERT = "/cat_aprobCert/{id}";

    /*entidad CAT_UNIDAD_ADMINISTRATIVA */
    public static final String LIST_UNIDAD_ADMIN_LIST_LAB = "/cat_unidad_adm/listLab";

}
