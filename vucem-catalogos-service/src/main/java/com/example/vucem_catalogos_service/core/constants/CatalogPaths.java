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

    /*entidad CAT_CATEGORIA_TEXTIL */
    public static final String LIST_CATALOGO_CATEGORIA_TEXTIL= "/cat_categ_textil/list";
    public static final String SAVE_CATALOGO_CATEGORIA_TEXTIL = "/cat_categ_textil/save";
    public static final String FIND_CATALOGO_CATEGORIA_TEXTIL = "/cat_categ_textil/{id}";
    public static final String UPDATE_CATALOGO_CATEGORIA_TEXTIL = "/cat_categ_textil/{id}";
    public static final String UNIDAD_MEDIDA_CATALOGO_CATEGORIA_TEXTIL = "/cat_categ_textil/unidad_medidas";

    /*entidad CAT_DEPENDENCIA */
    public static final String LIST_CATALOGO_DEPENDENCIA= "/cat_dependencia/list";
    public static final String SAVE_CATALOGO_DEPENDENCIA = "/cat_dependencia/save";
    public static final String FIND_CATALOGO_DEPENDENCIA = "/cat_dependencia/{id}";
    public static final String UPDATE_CATALOGO_DEPENDENCIA = "/cat_dependencia/{id}";
    public static final String CALENDARIO_CATALOGO_DEPENDENCIA = "/cat_dependencia/calendarios";

    /*entidad CAT_DICTAMEN_TRAMITE */
    public static final String LIST_CATALOGO_DICTAMEN_TRAMITE = "/cat_dictamen_tramite/list";
    public static final String SAVE_CATALOGO_DICTAMEN_TRAMITE = "/cat_dictamen_tramite/save";
    public static final String FIND_CATALOGO_DICTAMEN_TRAMITE = "/cat_dictamen_tramite/{tt}/{dc}";
    public static final String UPDATE_CATALOGO_DICTAMEN_TRAMITE = "/cat_dictamen_tramite/{tt}/{dc}";
    public static final String TIPO_DICTAMEN_CATALOGO_DICTAMEN_TRAMITE = "/cat_dictamen_tramite/tipos_dictamen";

    /*entidad CAT_FRACCION_ALADI */
    public static final String LIST_CATALOGO_FRACCION_ALADI   = "/cat_fraccion_aladi/list";
    public static final String SAVE_CATALOGO_FRACCION_ALADI   = "/cat_fraccion_aladi/save";
    public static final String FIND_CATALOGO_FRACCION_ALADI   = "/cat_fraccion_aladi/{id}";
    public static final String UPDATE_CATALOGO_FRACCION_ALADI = "/cat_fraccion_aladi/{id}";

    /*entidad CAT_FUNDAMENTO_DICTAMEN */
    public static final String LIST_CATALOGO_FUNDAMENTO_DICTAMEN   = "/cat_fundamento_dictamen/list";
    public static final String SAVE_CATALOGO_FUNDAMENTO_DICTAMEN   = "/cat_fundamento_dictamen/save";
    public static final String FIND_CATALOGO_FUNDAMENTO_DICTAMEN   = "/cat_fundamento_dictamen/{id}";
    public static final String UPDATE_CATALOGO_FUNDAMENTO_DICTAMEN = "/cat_fundamento_dictamen/{id}";

    /*entidad CAT_JUSTIFICACION_TRAMITE */
    public static final String LIST_CATALOGO_JUSTIFICACION_TRAMITE   = "/cat_justificacion_tramite/list";
    public static final String SAVE_CATALOGO_JUSTIFICACION_TRAMITE   = "/cat_justificacion_tramite/save";
    public static final String FIND_CATALOGO_JUSTIFICACION_TRAMITE   = "/cat_justificacion_tramite/{id}";
    public static final String UPDATE_CATALOGO_JUSTIFICACION_TRAMITE = "/cat_justificacion_tramite/{id}";

    /*entidad CAT_LEYENDA_TEXTO */
    public static final String LIST_CATALOGO_LEYENDA_TEXTO = "/cat_leyenda_texto/list";
    public static final String SAVE_CATALOGO_LEYENDA_TEXTO = "/cat_leyenda_texto/save";
    public static final String FIND_CATALOGO_LEYENDA_TEXTO = "/cat_leyenda_texto/{id}";
    public static final String UPDATE_CATALOGO_LEYENDA_TEXTO = "/cat_leyenda_texto/{id}";

    /*entidad CAT_MOTIVO_TRAMITE */
    public static final String LIST_CATALOGO_MOTIVO_TRAMITE = "/cat_motivo_tramite/list";
    public static final String SAVE_CATALOGO_MOTIVO_TRAMITE = "/cat_motivo_tramite/save";
    public static final String FIND_CATALOGO_MOTIVO_TRAMITE = "/cat_motivo_tramite/{id}";
    public static final String UPDATE_CATALOGO_MOTIVO_TRAMITE = "/cat_motivo_tramite/{id}";

    /*entidad CAT_PARAMETRO */
    public static final String LIST_CATALOGO_PARAMETRO = "/cat_parametro/list";
    public static final String SAVE_CATALOGO_PARAMETRO = "/cat_parametro/save";
    public static final String FIND_CATALOGO_PARAMETRO = "/cat_parametro/{id}";
    public static final String UPDATE_CATALOGO_PARAMETRO = "/cat_parametro/{id}";
    public static final String LIST_DEPENDENCIAS = "/cat_parametro/dependencias";

    /*entidad CAT_OBSERVACION_TRAMITE */
    public static final String LIST_CATALOGO_OBSERVACION_TRAMITE = "/cat_observacion_ttra/list";
    public static final String SAVE_CATALOGO_OBSERVACION_TRAMITE = "/cat_observacion_ttra/save";
    public static final String FIND_CATALOGO_OBSERVACION_TRAMITE = "/cat_observacion_ttra/{id}";
    public static final String UPDATE_CATALOGO_OBSERVACION_TRAMITE = "/cat_observacion_ttra/{id}";

    /*entidad CAT_APROB_CERT_SE */
    public static final String LIST_CATALOGO_APROB_CERT = "/cat_aprobCert/list";
    public static final String SAVE_CATALOGO_APROB_CERT = "/cat_aprobCert/save";
    public static final String FIND_CATALOGO_APROB_CERT = "/cat_aprobCert/{id}";
    public static final String UPDATE_CATALOGO_APROB_CERT = "/cat_aprobCert/{id}";
    public static final String LIST_LABORATORIOS = "/cat_aprobCert/laboratorios";

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
    public static final String LIST_FRACCION_ARANCELARIA = "/cat_arancelPro/fracc_arancelaria";

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

    /*entidad CAT_LOCALIDAD */
    public static final String LIST_LOCALIDAD = "/cat_localidad/list";
    public static final String SAVE_LOCALIDAD = "/cat_localidad/save";
    public static final String FIND_LOCALIDAD = "/cat_localidad/{cveLocalidad}";
    public static final String UPDATE_LOCALIDAD = "/cat_localidad/{cveLocalidad}";

    /*entidad CAT_NORMAL_OFICIAL */
    public static final String LIST_NORMAL_OFICIAL         = "/cat_normal_oficial/list";
    public static final String SAVE_NORMAL_OFICIAL         = "/cat_normal_oficial/save";
    public static final String FIND_NORMAL_OFICIAL         = "/cat_normal_oficial/{id}";
    public static final String UPDATE_NORMAL_OFICIAL       = "/cat_normal_oficial/{id}";
    public static final String LIST_NORMAL_OFICIAL_ACTIVOS = "/cat_normal_oficial/activos";

    /*entidad CAT_PARTIDA_FRACCION */
    public static final String LIST_PARTIDA_FRACCION            = "/cat_partida_fraccion/list";
    public static final String SAVE_PARTIDA_FRACCION            = "/cat_partida_fraccion/save";
    public static final String FIND_PARTIDA_FRACCION            = "/cat_partida_fraccion/{cveCapituloFraccion}/{cvePartidaFraccion}";
    public static final String UPDATE_PARTIDA_FRACCION          = "/cat_partida_fraccion/{cveCapituloFraccion}/{cvePartidaFraccion}";
    public static final String LIST_CAPITULOS_FRACCION_ACTIVOS  = "/cat_partida_fraccion/capitulos_activos";

    /*entidad CAT_PATENTE_ADUANAL */
    public static final String LIST_PATENTE_ADUANAL   = "/cat_patente_aduanal/list";
    public static final String SAVE_PATENTE_ADUANAL   = "/cat_patente_aduanal/save";
    public static final String FIND_PATENTE_ADUANAL   = "/cat_patente_aduanal/{cvePatenteAduanal}";
    public static final String UPDATE_PATENTE_ADUANAL = "/cat_patente_aduanal/{cvePatenteAduanal}";

    /*entidad CAT_PAIS_RESTRICCION_TTRA */
    public static final String LIST_PAIS_RESTRICCION_TTRA   = "/cat_pais_restriccion_ttra/list";
    public static final String SAVE_PAIS_RESTRICCION_TTRA   = "/cat_pais_restriccion_ttra/save";
    public static final String FIND_PAIS_RESTRICCION_TTRA   = "/cat_pais_restriccion_ttra/{id}";
    public static final String UPDATE_PAIS_RESTRICCION_TTRA = "/cat_pais_restriccion_ttra/{id}";



}
