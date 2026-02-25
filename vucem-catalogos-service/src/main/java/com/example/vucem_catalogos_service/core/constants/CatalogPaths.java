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

    /*entidad CAT_PAIS_TRATADO_ACUERDO */
    public static final String LIST_CATALOGO_PAIS_TRATADO_ACUERDO = "/cat_pais_tratado_ac/list";
    public static final String SAVE_CATALOGO_PAIS_TRATADO_ACUERDO = "/cat_pais_tratado_ac/save";
    public static final String FIND_CATALOGO_PAIS_TRATADO_ACUERDO = "/cat_pais_tratado_ac/{idPais}/{idTratado}";
    public static final String UPDATE_CATALOGO_PAIS_TRATADO_ACUERDO = "/cat_pais_tratado_ac/{idPais}/{idTratado}";
    public static final String LIST_SELECT_TRATADO_ACUERDO = "/cat_pais_tratado_ac/tratados_acuerdo";

    /*entidad CAT_APROB_CERT_SE */
    public static final String LIST_CATALOGO_APROB_CERT = "/cat_aprobCert/list";
    public static final String SAVE_CATALOGO_APROB_CERT = "/cat_aprobCert/save";
    public static final String FIND_CATALOGO_APROB_CERT = "/cat_aprobCert/{id}";
    public static final String UPDATE_CATALOGO_APROB_CERT = "/cat_aprobCert/{id}";
    public static final String LIST_LABORATORIOS = "/cat_aprobCert/laboratorios";

    /*entidad CAT_UNIDAD_ADMINISTRATIVA */
    public static final String LIST_UNIDAD_ADMIN_LIST_LAB    = "/cat_unidad_adm/listUnidadAdm";
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

    /*entidad CAT_PLAZO_MAXIMO_AUT_TRAMITE */
    public static final String LIST_PLAZO_MAX_TTRA   = "/cat_plazo_max_ttra/list";
    public static final String SAVE_PLAZO_MAX_TTRA   = "/cat_plazo_max_ttra/save";
    public static final String FIND_PLAZO_MAX_TTRA   = "/cat_plazo_max_ttra/{idTipoTramite}/{fecIniVigencia}";
    public static final String UPDATE_PLAZO_MAX_TTRA = "/cat_plazo_max_ttra/{idTipoTramite}/{fecIniVigencia}";

    /*entidad CAT_SECCION_TTRA */
    public static final String LIST_SECCION_TTRA   = "/cat_seccion_ttra/list";
    public static final String SAVE_SECCION_TTRA   = "/cat_seccion_ttra/save";
    public static final String FIND_SECCION_TTRA   = "/cat_seccion_ttra/{id}";
    public static final String UPDATE_SECCION_TTRA = "/cat_seccion_ttra/{id}";

    /*entidad CAT_SERVICIO_IMMEX */
    public static final String LIST_SERVICIO_IMMEX   = "/cat_servicio_immex/list";
    public static final String SAVE_SERVICIO_IMMEX   = "/cat_servicio_immex/save";
    public static final String FIND_SERVICIO_IMMEX   = "/cat_servicio_immex/{id}";
    public static final String UPDATE_SERVICIO_IMMEX = "/cat_servicio_immex/{id}";

    /*entidad CAT_SUPLENCIA */
    public static final String LIST_SUPLENCIA              = "/cat_suplencia/list";
    public static final String SAVE_SUPLENCIA              = "/cat_suplencia/save";
    public static final String FIND_SUPLENCIA              = "/cat_suplencia/{id}";
    public static final String UPDATE_SUPLENCIA            = "/cat_suplencia/{id}";
    public static final String LIST_DEPENDENCIAS_SUPLENCIA = "/cat_suplencia/dependencias";

    /*entidad CAT_USO_ESPEC_MERCANCIA_TTRA */
    public static final String LIST_USO_ESPEC_MERCANCIA_TTRA   = "/cat_uso_espec_mercancia_ttra/list";
    public static final String SAVE_USO_ESPEC_MERCANCIA_TTRA   = "/cat_uso_espec_mercancia_ttra/save";
    public static final String FIND_USO_ESPEC_MERCANCIA_TTRA   = "/cat_uso_espec_mercancia_ttra/{id}";
    public static final String UPDATE_USO_ESPEC_MERCANCIA_TTRA = "/cat_uso_espec_mercancia_ttra/{id}";

    /*entidad CAT_TIPO_RFC */
    public static final String LIST_TIPO_RFC   = "/cat_tipo_rfc/list";
    public static final String SAVE_TIPO_RFC   = "/cat_tipo_rfc/save";
    public static final String FIND_TIPO_RFC   = "/cat_tipo_rfc/{rfc}/{ideTipoRfc}";
    public static final String UPDATE_TIPO_RFC = "/cat_tipo_rfc/{rfc}/{ideTipoRfc}";

    /*entidad CAT_TRATADO_ACUERDO */
    public static final String LIST_TRATADO_ACUERDO   = "/cat_tratado_acuerdo/list";
    public static final String SAVE_TRATADO_ACUERDO   = "/cat_tratado_acuerdo/save";
    public static final String FIND_TRATADO_ACUERDO   = "/cat_tratado_acuerdo/{id}";
    public static final String UPDATE_TRATADO_ACUERDO = "/cat_tratado_acuerdo/{id}";

    /*entidad CAT_PLAZO_TTRA */
    public static final String LIST_PLAZO_TTRA   = "/cat_plazo_ttra/list";
    public static final String SAVE_PLAZO_TTRA   = "/cat_plazo_ttra/save";
    public static final String FIND_PLAZO_TTRA   = "/cat_plazo_ttra/{idTipoTramite}/{idePlazoVigencia}";
    public static final String UPDATE_PLAZO_TTRA = "/cat_plazo_ttra/{idTipoTramite}/{idePlazoVigencia}";

    /*entidad CAT_RECINTO_FISCALIZADO */
    public static final String LIST_RECINTO_FISCALIZADO   = "/cat_recinto_fis/list";
    public static final String SAVE_RECINTO_FISCALIZADO   = "/cat_recinto_fis/save";
    public static final String FIND_RECINTO_FISCALIZADO   = "/cat_recinto_fis/{id}";
    public static final String UPDATE_RECINTO_FISCALIZADO = "/cat_recinto_fis/{id}";

    /*entidad CAT_DOCUMENTO_TRAMITE */
    public static final String LIST_DOCUMENTO_TRAMITE   = "/cat_documento_tramite/list";
    public static final String SAVE_DOCUMENTO_TRAMITE   = "/cat_documento_tramite/save";
    public static final String FIND_DOCUMENTO_TRAMITE   = "/cat_documento_tramite/{idTipoDoc}/{idTipoTramite}";
    public static final String UPDATE_DOCUMENTO_TRAMITE = "/cat_documento_tramite/{idTipoDoc}/{idTipoTramite}";
    public static final String LISTADO_TIPO_DOCUMENTO = "/cat_documento_tramite/listDocumento";

    /*entidad CAT_VIDA_SILVESTRE */
    public static final String LIST_VIDA_SILVESTRE   = "/cat_vida_silvestre/list";
    public static final String SAVE_VIDA_SILVESTRE   = "/cat_vida_silvestre/save";
    public static final String FIND_VIDA_SILVESTRE   = "/cat_vida_silvestre/{id}";
    public static final String UPDATE_VIDA_SILVESTRE = "/cat_vida_silvestre/{id}";
    public static final String LIST_ESPECIES_ACTIVAS = "/cat_vida_silvestre/especies";

    /*entidad CAT_ACTIVIDAD_ECONOMICA_SAT */
    public static final String LIST_ACTIVIDAD_ECONOMICA_SAT   = "/cat_activ_eco_sat/list";
    public static final String LIST_ACTIVIDAD_ECONOMICA_SAT_DESC   = "/cat_activ_eco_sat/Lisdesc";
    public static final String LIST_ACTIVIDAD_ECONOMICA_SAT_REL   = "/cat_activ_eco_sat/listActRe";
    public static final String SAVE_ACTIVIDAD_ECONOMICA_SAT   = "/cat_activ_eco_sat/save";
    public static final String FIND_ACTIVIDAD_ECONOMICA_SAT   = "/cat_activ_eco_sat/{id}";
    public static final String UPDATE_ACTIVIDAD_ECONOMICA_SAT = "/cat_activ_eco_sat/{id}";

    /*entidad CAT_DIA_NO_LABORABLE */
    public static final String LIST_DIA_NO_LABORABLE   = "/cat_dia_no_lab/list";
    public static final String SAVE_DIA_NO_LABORABLE   = "/cat_dia_no_lab/save";
    public static final String FIND_DIA_NO_LABORABLE   = "/cat_dia_no_lab/{fecNoLaborable}/{cveCalendario}";
    public static final String UPDATE_DIA_NO_LABORABLE = "/cat_dia_no_lab/{fecNoLaborable}/{cveCalendario}";
    public static final String LIST_SELECT_CALENDARIO = "/cat_dia_no_lab/calendarios";

    /*entidad CAT_EQUIVALENCIA_AELC */
    public static final String LIST_EQUIVALENCIA_AELC   = "/cat_equiv_aelc/list";
    public static final String SAVE_EQUIVALENCIA_AELC   = "/cat_equiv_aelc/save";
    public static final String FIND_EQUIVALENCIA_AELC   = "/cat_equiv_aelc/{fecIniVigencia}/{cveMoneda}";
    public static final String UPDATE_EQUIVALENCIA_AELC = "/cat_equiv_aelc/{fecIniVigencia}/{cveMoneda}";

    /*entidad CAT_SECTOR_PROSEC */
    public static final String LIST_SECTOR_PROSEC   = "/cat_sector_prosec/list";
    public static final String SAVE_SECTOR_PROSEC   = "/cat_sector_prosec/save";
    public static final String FIND_SECTOR_PROSEC   = "/cat_sector_prosec/{cveSectorProsec}";
    public static final String UPDATE_SECTOR_PROSEC = "/cat_sector_prosec/{cveSectorProsec}";

    /*entidad CAT_SCIAN */
    public static final String LIST_SCIAN   = "/cat_scian/list";
    public static final String SAVE_SCIAN   = "/cat_scian/save";
    public static final String FIND_SCIAN   = "/cat_scian/{cveScian}";
    public static final String UPDATE_SCIAN = "/cat_scian/{cveScian}";

    /*entidad CAT_MEDIO_TRANSPORTE_TTRA */
    public static final String LIST_MEDIO_TRANSPORTE_TTRA   = "/cat_medio_tporte_ttra/list";
    public static final String SAVE_MEDIO_TRANSPORTE_TTRA   = "/cat_medio_tporte_ttra/save";
    public static final String FIND_MEDIO_TRANSPORTE_TTRA   = "/cat_medio_tporte_ttra/{id}";
    public static final String UPDATE_MEDIO_TRANSPORTE_TTRA = "/cat_medio_tporte_ttra/{id}";

    /*entidad CAT_CLASIF_TOXICOLOGICA_TTRA */
    public static final String LIST_CLASIF_TOXICOLOGICA_TTRA   = "/cat_clasif_toxicol_ttra/list";
    public static final String SAVE_CLASIF_TOXICOLOGICA_TTRA   = "/cat_clasif_toxicol_ttra/save";
    public static final String FIND_CLASIF_TOXICOLOGICA_TTRA   = "/cat_clasif_toxicol_ttra/{id}";
    public static final String UPDATE_CLASIF_TOXICOLOGICA_TTRA = "/cat_clasif_toxicol_ttra/{id}";

    /*entidad CAT_UNIDAD_ADMIN_ADUANA */
    public static final String LIST_UNIDAD_ADMIN_ADUANA   = "/cat_unidad_admin_aduana/list";
    public static final String SAVE_UNIDAD_ADMIN_ADUANA   = "/cat_unidad_admin_aduana/save";
    public static final String FIND_UNIDAD_ADMIN_ADUANA   = "/cat_unidad_admin_aduana/{cveUnidadAdministrativa}/{cveAduana}";
    public static final String UPDATE_UNIDAD_ADMIN_ADUANA = "/cat_unidad_admin_aduana/{cveUnidadAdministrativa}/{cveAduana}";

    /*entidad CAT_TARIFA */
    public static final String LIST_TARIFA   = "/cat_tarifa/list";
    public static final String SAVE_TARIFA   = "/cat_tarifa/save";
    public static final String FIND_TARIFA   = "/cat_tarifa/{idTipoTramite}/{fecIniVigencia}/{ideTipoTarifa}";
    public static final String UPDATE_TARIFA = "/cat_tarifa/{idTipoTramite}/{fecIniVigencia}/{ideTipoTarifa}";

    /*entidad CAT_SUBPARTIDA_FRACCION */
    public static final String LIST_SUBPARTIDA_FRACCION   = "/cat_subpartida_fraccion/list";
    public static final String SAVE_SUBPARTIDA_FRACCION   = "/cat_subpartida_fraccion/save";
    public static final String FIND_SUBPARTIDA_FRACCION   = "/cat_subpartida_fraccion/{cveSubpartidaFraccion}/{cveCapituloFraccion}/{cvePartidaFraccion}";
    public static final String UPDATE_SUBPARTIDA_FRACCION = "/cat_subpartida_fraccion/{cveSubpartidaFraccion}/{cveCapituloFraccion}/{cvePartidaFraccion}";

    /*entidad CAT_TIPO_PRODUCTO_TTRA */
    public static final String LIST_TIPO_PRODUCTO_TTRA   = "/cat_tipo_producto_ttra/list";
    public static final String SAVE_TIPO_PRODUCTO_TTRA   = "/cat_tipo_producto_ttra/save";
    public static final String FIND_TIPO_PRODUCTO_TTRA   = "/cat_tipo_producto_ttra/{id}";
    public static final String UPDATE_TIPO_PRODUCTO_TTRA = "/cat_tipo_producto_ttra/{id}";
    public static final String SELECT_TIPO_CERTIFICADO_MERCANCIA = "/cat_tipo_producto_ttra/tipo_certificado";

    /*entidad CAT_TIPO_TRAMITE */
    public static final String LIST_TIPO_TRAMITE   = "/cat_tipo_tramite/list";
    public static final String SAVE_TIPO_TRAMITE   = "/cat_tipo_tramite/save";
    public static final String FIND_TIPO_TRAMITE   = "/cat_tipo_tramite/{id}";
    public static final String UPDATE_TIPO_TRAMITE = "/cat_tipo_tramite/{id}";

    /*entidad CAT_SUBDIVISION_FRACCION */
    public static final String LIST_FRACCION_ARANCELARIA_BY_ID   = "/cat_subdivision_fraccion/cvefraccion";
    public static final String LIST_SUBDIVISION_FRACCION   = "/cat_subdivision_fraccion/list";
    public static final String SAVE_SUBDIVISION_FRACCION   = "/cat_subdivision_fraccion/save";
    public static final String FIND_SUBDIVISION_FRACCION   = "/cat_subdivision_fraccion/{cveSubdivision}";
    public static final String UPDATE_SUBDIVISION_FRACCION = "/cat_subdivision_fraccion/{cveSubdivision}";

    /*entidad CAT_USO_MERCANCIA_TTRA */
    public static final String LIST_USO_MERCANCIA_TTRA   = "/cat_uso_mercancia_ttra/list";
    public static final String SAVE_USO_MERCANCIA_TTRA   = "/cat_uso_mercancia_ttra/save";
    public static final String FIND_USO_MERCANCIA_TTRA   = "/cat_uso_mercancia_ttra/{id}";
    public static final String UPDATE_USO_MERCANCIA_TTRA = "/cat_uso_mercancia_ttra/{id}";

    /*entidad CAT_UNIDAD_MEDIDA_TTRA */
    public static final String LIST_UNIDAD_MEDIDA_TTRA   = "/cat_unidad_medida_ttra/list";
    public static final String SAVE_UNIDAD_MEDIDA_TTRA   = "/cat_unidad_medida_ttra/save";
    public static final String FIND_UNIDAD_MEDIDA_TTRA   = "/cat_unidad_medida_ttra/{id}";
    public static final String UPDATE_UNIDAD_MEDIDA_TTRA = "/cat_unidad_medida_ttra/{id}";

    /*entidad CAT_TIPO_EMPRESA_RECIF */
    public static final String LIST_TIPO_EMPRESA_RECIF   = "/cat_tipo_empresa_recif/list";
    public static final String SAVE_TIPO_EMPRESA_RECIF   = "/cat_tipo_empresa_recif/save";
    public static final String FIND_TIPO_EMPRESA_RECIF   = "/cat_tipo_empresa_recif/{cveTipoEmpresaRecif}";
    public static final String UPDATE_TIPO_EMPRESA_RECIF = "/cat_tipo_empresa_recif/{cveTipoEmpresaRecif}";
    public static final String SELECT_TIPO_EMPRESA_RECIF = "/cat_tipo_empresa_recif/select_tipo_empresa";


    /*entidad CAT_TRATADO_BLOQUE */
    public static final String LIST_TRATADO_BLOQUE   = "/cat_tratado_bloque/list";
    public static final String SAVE_TRATADO_BLOQUE   = "/cat_tratado_bloque/save";
    public static final String FIND_TRATADO_BLOQUE   = "/cat_tratado_bloque/{idTratadoAcuerdo}/{idBloque}";
    public static final String UPDATE_TRATADO_BLOQUE = "/cat_tratado_bloque/{idTratadoAcuerdo}/{idBloque}";

    /*entidad CAT_TRATAMIENTO_ESPECIAL */
    public static final String LIST_TRATAMIENTO_ESPECIAL   = "/cat_tratamiento_especial/list";
    public static final String SAVE_TRATAMIENTO_ESPECIAL   = "/cat_tratamiento_especial/save";
    public static final String FIND_TRATAMIENTO_ESPECIAL   = "/cat_tratamiento_especial/{id}";
    public static final String UPDATE_TRATAMIENTO_ESPECIAL = "/cat_tratamiento_especial/{id}";

    /*entidad CAT_VIGENCIA_SERVICIO */
    public static final String LIST_VIGENCIA_SERVICIO   = "/cat_vigencia_servicio/list";
    public static final String SAVE_VIGENCIA_SERVICIO   = "/cat_vigencia_servicio/save";
    public static final String FIND_VIGENCIA_SERVICIO   = "/cat_vigencia_servicio/{id}";
    public static final String UPDATE_VIGENCIA_SERVICIO = "/cat_vigencia_servicio/{id}";

    /*entidad CAT_UNIDAD_ADMIN_VECINA */
    public static final String LIST_UNIDAD_ADMIN_VECINA   = "/cat_unidad_admin_vecina/list";
    public static final String SAVE_UNIDAD_ADMIN_VECINA   = "/cat_unidad_admin_vecina/save";
    public static final String FIND_UNIDAD_ADMIN_VECINA   = "/cat_unidad_admin_vecina/{cveUnidadAdministrativa}/{cveEntidad}";
    public static final String UPDATE_UNIDAD_ADMIN_VECINA = "/cat_unidad_admin_vecina/{cveUnidadAdministrativa}/{cveEntidad}";

    /*entidad CAT_COMBINACION_SG */
    public static final String LIST_COMBINACION_SG   = "/cat_combinacion_sg/list";
    public static final String SAVE_COMBINACION_SG   = "/cat_combinacion_sg/save";
    public static final String FIND_COMBINACION_SG   = "/cat_combinacion_sg/{id}";
    public static final String UPDATE_COMBINACION_SG = "/cat_combinacion_sg/{id}";

    /*entidad CAT_REGIMEN_TTRA */
    public static final String LIST_REGIMEN_TTRA   = "/cat_regimen_ttra/list";
    public static final String SAVE_REGIMEN_TTRA   = "/cat_regimen_ttra/save";
    public static final String FIND_REGIMEN_TTRA   = "/cat_regimen_ttra/{id}";
    public static final String UPDATE_REGIMEN_TTRA = "/cat_regimen_ttra/{id}";
    public static final String SELECT_TIPO_TRAMITE = "/cat_regimen_ttra/tipo_tramite";
    public static final String SELECT_REGIMEN = "/cat_regimen_ttra/list_regimen";

}
