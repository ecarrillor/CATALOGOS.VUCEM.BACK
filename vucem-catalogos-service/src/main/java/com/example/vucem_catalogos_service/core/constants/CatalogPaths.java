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
    public static final String FIND_CATALOGO_ADUANA = "/cat_aduana/{cveAduana}";
    public static final String UPDATE_CATALOGO_ADUANA = "/cat_aduana/{cveAduana}";
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
    public static final String FIND_ADUANA_TRAMITE = "/cat_aduana_tramite/{id}";
    public static final String UPDATE_ADUANA_TRAMITE = "/cat_aduana_tramite/{id}";
    public static final String LIST_TIPOS_TRAMITE = "/cat_aduana_tramite/tipos_tramite";


    /*entidad CAT_APROB_CERT_SE */
    public static final String LIST_CATALOGO_APROB_CERT = "/cat_aprobCert/list";
    public static final String SAVE_CATALOGO_APROB_CERT = "/cat_aprobCert/save";
    public static final String FIND_CATALOGO_APROB_CERT = "/cat_aprobCert/{id}";
    public static final String UPDATE_CATALOGO_APROB_CERT = "/cat_aprobCert/{id}";

    /*entidad CAT_UNIDAD_ADMINISTRATIVA */
    public static final String LIST_UNIDAD_ADMIN_LIST_LAB    = "/cat_unidad_adm/listLab";
    public static final String LIST_UNIDAD_ADMIN             = "/cat_unidad_admin/list";
    public static final String SAVE_UNIDAD_ADMIN             = "/cat_unidad_admin/save";
    public static final String FIND_UNIDAD_ADMIN             = "/cat_unidad_admin/{cveUnidadAdministrativa}";
    public static final String UPDATE_UNIDAD_ADMIN           = "/cat_unidad_admin/{cveUnidadAdministrativa}";
    public static final String LIST_ENTIDADES_UNIDAD_ADMIN   = "/cat_unidad_admin/entidades";
    public static final String LIST_DEPENDENCIAS_UNIDAD_ADMIN = "/cat_unidad_admin/dependencias";

    /*entidad CAT_COLONIA */
    public static final String LIST_CATALOGO_COLONIA   = "/cat_colonia/list";
    public static final String SAVE_CATALOGO_COLONIA   = "/cat_colonia/save";
    public static final String FIND_CATALOGO_COLONIA   = "/cat_colonia/{cveColonia}";
    public static final String UPDATE_CATALOGO_COLONIA = "/cat_colonia/{cveColonia}";
    public static final String FIND_COLONIA_BY_CP      = "/cat_colonia/cp/{cp}";
    public static final String FIND_MUNICIPIOS     = "/cat_colonia/{cvePais}/{cveEntidad}";
    public static final String FIND_LOCALIDADES     = "/cat_colonia/{cvePais}/{cveEntidad}/{cveMunicipio}";

    /*entidad CAT_PAIS */
    public static final String LIST_CATALOGO_PAIS   = "/cat_pais/list";
    public static final String SAVE_CATALOGO_PAIS   = "/cat_pais/save";
    public static final String FIND_CATALOGO_PAIS   = "/cat_pais/{cvePais}";
    public static final String UPDATE_CATALOGO_PAIS = "/cat_pais/{cvePais}";
    public static final String LIST_CAT_MONEDA = "/cat_pais/listMoneda";

    /*entidad CAT_DELEG_MUN */
    public static final String LIST_CATALOGO_DELEG_MUN   = "/cat_deleg_mun/list";
    public static final String SAVE_CATALOGO_DELEG_MUN   = "/cat_deleg_mun/save";
    public static final String FIND_CATALOGO_DELEG_MUN   = "/cat_deleg_mun/{cveDelegMun}";
    public static final String UPDATE_CATALOGO_DELEG_MUN = "/cat_deleg_mun/{cveDelegMun}";

    /*entidad CAT_ENTIDAD - listado simple */
    public static final String LIST_NOMBRES_ENTIDAD = "/cat_entidad/nombres";

    /*entidad CAT_ARANCEL_PROSEC */
    public static final String LIST_ARANCEL_PROSEC = "/cat_arancelPro/list";
    public static final String SAVE_ARANCEL_PROSEC = "/cat_arancelPro/save";
    public static final String FIND_ARANCEL_PROSEC = "/cat_arancelPro/{id}/{cveSectorProsec}";
    public static final String UPDATE_ARANCEL_PROSEC = "/cat_arancelPro/{cveFraccion}/{cveSectorProsec}";

    /*entidad CAT_CLASIFICACION_REGIMEN */
    public static final String LIST_CLASIFICACION_REGIMEN   = "/cat_clasificacion_regimen/list";
    public static final String SAVE_CLASIFICACION_REGIMEN   = "/cat_clasificacion_regimen/save";
    public static final String FIND_CLASIFICACION_REGIMEN   = "/cat_clasificacion_regimen/{cveClasificacionRegimen}/{cveRegimen}";
    public static final String UPDATE_CLASIFICACION_REGIMEN = "/cat_clasificacion_regimen/{cveClasificacionRegimen}/{cveRegimen}";
    public static final String LIST_REGIMENES_SELECT        = "/cat_clasificacion_regimen/regimenes";

    /*entidad CAT_DECLARACION_TRAMITE */
    public static final String LIST_DECLARACION_TRAMITE   = "/cat_declaracion_tramite/list";
    public static final String SAVE_DECLARACION_TRAMITE   = "/cat_declaracion_tramite/save";
    public static final String FIND_DECLARACION_TRAMITE   = "/cat_declaracion_tramite/{cveDeclaracion}/{idTipoTramite}";
    public static final String UPDATE_DECLARACION_TRAMITE = "/cat_declaracion_tramite/{cveDeclaracion}/{idTipoTramite}";
    public static final String LIST_DECLARACIONES_SELECT  = "/cat_declaracion_tramite/declaraciones";
    public static final String LIST_TIPOS_TRAMITE_DECL    = "/cat_declaracion_tramite/tipos_tramite";

    /*entidad CAT_EQUIV_MONEDA */
    public static final String LIST_EQUIV_MONEDA   = "/cat_equiv_moneda/list";
    public static final String SAVE_EQUIV_MONEDA   = "/cat_equiv_moneda/save";
    public static final String FIND_EQUIV_MONEDA   = "/cat_equiv_moneda/{id}";
    public static final String UPDATE_EQUIV_MONEDA = "/cat_equiv_moneda/{id}";
    public static final String LIST_MONEDAS_SELECT_MONORIGEN = "/cat_equiv_moneda/cveMonedaOrigen";
    public static final String LIST_MONEDAS_SELECT_MONDEST = "/cat_equiv_moneda/cveMonedaDestino";

    /*entidad CAT_EMPRESA_RECIF */
    public static final String LIST_EMPRESA_RECIF        = "/cat_empresa_recif/list";
    public static final String SAVE_EMPRESA_RECIF        = "/cat_empresa_recif/save";
    public static final String FIND_EMPRESA_RECIF        = "/cat_empresa_recif/{recif}";
    public static final String UPDATE_EMPRESA_RECIF      = "/cat_empresa_recif/{recif}";
    public static final String LIST_UNIDADES_ADMIN_SELECT = "/cat_empresa_recif/unidades_admin";

    /*entidad CAT_FRACCION_HTS_USA */
    public static final String LIST_FRACCION_HTS_USA        = "/cat_fraccion_hts_usa/list";
    public static final String SAVE_FRACCION_HTS_USA        = "/cat_fraccion_hts_usa/save";
    public static final String FIND_FRACCION_HTS_USA        = "/cat_fraccion_hts_usa/{id}";
    public static final String UPDATE_FRACCION_HTS_USA      = "/cat_fraccion_hts_usa/{id}";
    public static final String LIST_UNIDADES_MEDIDA_SELECT  = "/cat_fraccion_hts_usa/unidades_medida";


}
