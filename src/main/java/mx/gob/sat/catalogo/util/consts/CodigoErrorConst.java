package mx.gob.sat.catalogo.util.consts;

/**
 * <b>Class:</b> CodigoErrorConst.java <br>
 * <b>Description:</b>
 * <p>Contiene los codigos de error que maneja la aplicación.
 * </p>
 *
 * @author Javier Chávez Barrios
 * @email jchavezb@ultrasist.com.mx
 * 
 * @created 10 de marzo del 2025
 * @version 1.0
 * @category Constantes
 */
public final class CodigoErrorConst {

    private CodigoErrorConst() {
        throw new IllegalStateException("Clase CodigoErrorConst no puede ser instanciada");
    }

    /**
     * Representa el codigo base de la aplicación CATADMIN.
     */
    public static final String CODIGO_BASE = "CATADMIN-";
    
    /** Representa el código de error cuando la unidad administrativa federal no es soportada. */
    public static final String UNIDAD_ADMINISTRATIVA_NO_SOPORTADA = CODIGO_BASE + "U001";

    /****************************************************************************************
     *                                    CAT. ADUANA
     ****************************************************************************************/

    /** Error cuando ya existe una aduana con la clave indicada. */
    public static final String ADUANA_YA_EXISTE = CODIGO_BASE + "A001";

    /** Error cuando no se encuentra una aduana con la clave indicada. */
    public static final String ADUANA_NO_ENCONTRADA = CODIGO_BASE + "A002";

    /** Error cuando no se encuentra el tipo de aduana indicado. */
    public static final String TIPO_ADUANA_NO_ENCONTRADO = CODIGO_BASE + "A003";

    /** Error cuando no se encuentra la entidad federativa indicada. */
    public static final String ENTIDAD_NO_ENCONTRADA = CODIGO_BASE + "A004";

    /****************************************************************************************
     *                                    CAT. TIPO ADUANA
     ****************************************************************************************/

    /** Error cuando no se encuentra el tipo de aduana indicado (alias independiente). */
    public static final String TIPO_ADUANA_NO_ENCONTRADA = CODIGO_BASE + "T001";

    /****************************************************************************************
     *                                    CAT. MONEDA
     ****************************************************************************************/

    /** Error cuando no se encuentra una moneda con la clave indicada. */
    public static final String MONEDA_NO_ENCONTRADA = CODIGO_BASE + "M001";

    /****************************************************************************************
     *                                    CAT. PAIS
     ****************************************************************************************/

    /** Error cuando no se encuentra un pais con la clave indicada. */
    public static final String PAIS_NO_ENCONTRADO = CODIGO_BASE + "P001";

    /****************************************************************************************
     *                                  CAT. CALENDARIO
     ****************************************************************************************/

    /** Error cuando no se encuentra un calendario con la clave indicada. */
    public static final String CALENDARIO_NO_ENCONTRADO = CODIGO_BASE + "C001";

    /****************************************************************************************
     *                                    CAT. GENERO
     ****************************************************************************************/

    /** Error cuando no se encuentra un genero con el identificador indicado. */
    public static final String GENERO_NO_ENCONTRADO = CODIGO_BASE + "G001";

    /****************************************************************************************
     *                                   CAT. LENGUAJE
     ****************************************************************************************/

    /** Error cuando no se encuentra un lenguaje con la clave indicada. */
    public static final String LENGUAJE_NO_ENCONTRADO = CODIGO_BASE + "L001";

    /****************************************************************************************
     *                                     CAT. BANCO
     ****************************************************************************************/

    /** Error cuando no se encuentra un banco con la clave indicada. */
    public static final String BANCO_NO_ENCONTRADO = CODIGO_BASE + "B001";

    /****************************************************************************************
     *                                  CAT. DEPENDENCIA
     ****************************************************************************************/

    /** Error cuando no se encuentra una dependencia con el identificador indicado. */
    public static final String DEPENDENCIA_NO_ENCONTRADA = CODIGO_BASE + "D001";

    /****************************************************************************************
     *                                   CAT. PARAMETRO
     ****************************************************************************************/

    /** Error cuando no se encuentra un parametro con la clave indicada. */
    public static final String PARAMETRO_NO_ENCONTRADO = CODIGO_BASE + "PR001";

    /****************************************************************************************
     *                                      CAT. AGA
     ****************************************************************************************/

    /** Error cuando no se encuentra un registro AGA con la clave indicada. */
    public static final String AGA_NO_ENCONTRADO = CODIGO_BASE + "AG001";

    /****************************************************************************************
     *                                    CAT. REGIMEN
     ****************************************************************************************/

    /** Error cuando no se encuentra un regimen con la clave indicada. */
    public static final String REGIMEN_NO_ENCONTRADO = CODIGO_BASE + "RE001";

    /****************************************************************************************
     *                                 CAT. UNIDAD DE MEDIDA
     ****************************************************************************************/

    /** Error cuando no se encuentra una unidad de medida con la clave indicada. */
    public static final String UNIDAD_MEDIDA_NO_ENCONTRADA = CODIGO_BASE + "UM001";

    /****************************************************************************************
     *                                  CAT. SECTOR PROSEC
     ****************************************************************************************/

    /** Error cuando no se encuentra un registro sector prosec con la clave indicada. */
    public static final String SECTOR_PROSEC_NO_ENCONTRADO = CODIGO_BASE + "SP001";

    /****************************************************************************************
     *                                      CAT. SCIAN
     ****************************************************************************************/

    /** Error cuando no se encuentra un registro SCIAN con la clave indicada. */
    public static final String SCIAN_NO_ENCONTRADO = CODIGO_BASE + "SC001";

    /****************************************************************************************
     *                                CAT. CAPITULO FRACCION
     ****************************************************************************************/

    /** Error cuando no se encuentra un registro capitulo fraccion con la clave indicada. */
    public static final String CAPITULO_FRACCION_NO_ENCONTRADO = CODIGO_BASE + "CF001";

    /****************************************************************************************
     *                                CAT. CRITERIO ORIGEN
     ****************************************************************************************/

    /** Error cuando no se encuentra un registro criterio origen con la clave indicada. */
    public static final String CRITERIO_ORIGEN_NO_ENCONTRADO = CODIGO_BASE + "CO001";

    /****************************************************************************************
     *                                   CAT. DECLARACION
     ****************************************************************************************/

    /** Error cuando no se encuentra una declaracion con la clave indicada. */
    public static final String DECLARACION_NO_ENCONTRADA = CODIGO_BASE + "DE001";

    /****************************************************************************************
     *                                    CAT. PRODUCTO
     ****************************************************************************************/

    /** Error cuando no se encuentra un producto con la clave indicada. */
    public static final String PRODUCTO_NO_ENCONTRADO = CODIGO_BASE + "PD001";

    /****************************************************************************************
     *                                CAT. PATENTE ADUANAL
     ****************************************************************************************/

    /** Error cuando no se encuentra una patente aduanal con la clave indicada. */
    public static final String PATENTE_ADUANAL_NO_ENCONTRADA = CODIGO_BASE + "PA001";

    /****************************************************************************************
     *                                    CAT. ESPECIE
     ****************************************************************************************/

    /** Error cuando no se encuentra una especie con el identificador indicado. */
    public static final String ESPECIE_NO_ENCONTRADA = CODIGO_BASE + "ES001";

    /****************************************************************************************
     *                                  CAT. SEMANA LABORAL
     ****************************************************************************************/

    /** Error cuando no se encuentra una semana laboral con el identificador indicado. */
    public static final String SEMANA_LABORAL_NO_ENCONTRADA = CODIGO_BASE + "SL001";

    /****************************************************************************************
     *                                CAT. DESCRIPCION PROD
     ****************************************************************************************/

    /** Error cuando no se encuentra una descripcion de producto con el identificador indicado. */
    public static final String DESCRIPCION_PROD_NO_ENCONTRADA = CODIGO_BASE + "DP001";

    /****************************************************************************************
     *                               CAT. PUNTO VERIFICACION
     ****************************************************************************************/

    /** Error cuando no se encuentra un punto de verificacion con el identificador indicado. */
    public static final String PUNTO_VERIFICACION_NO_ENCONTRADO = CODIGO_BASE + "PV001";

    /****************************************************************************************
     *                            CAT. CRITERIO DICTAMINACION
     ****************************************************************************************/

    /** Error cuando no se encuentra un criterio de dictaminacion con el identificador indicado. */
    public static final String CRITERIO_DICTAMINACION_NO_ENCONTRADO = CODIGO_BASE + "CD001";

    /****************************************************************************************
     *                                  CAT. TIPO DICTAMEN
     ****************************************************************************************/

    /** Error cuando no se encuentra un tipo de dictamen con el identificador indicado. */
    public static final String TIPO_DICTAMEN_NO_ENCONTRADO = CODIGO_BASE + "TD001";

    /****************************************************************************************
     *                                 CAT. TIPO DOCUMENTO
     ****************************************************************************************/

    /** Error cuando no se encuentra un tipo de documento con el identificador indicado. */
    public static final String TIPO_DOCUMENTO_NO_ENCONTRADO = CODIGO_BASE + "TDO001";

    /****************************************************************************************
     *                              CAT. FUNDAMENTO DICTAMEN
     ****************************************************************************************/

    /** Error cuando no se encuentra un fundamento de dictamen con el identificador indicado. */
    public static final String FUNDAMENTO_DICTAMEN_NO_ENCONTRADO = CODIGO_BASE + "FD001";

    /****************************************************************************************
     *                                CAT. SERVICIO IMMEX
     ****************************************************************************************/

    /** Error cuando no se encuentra un servicio IMMEX con el identificador indicado. */
    public static final String SERVICIO_IMMEX_NO_ENCONTRADO = CODIGO_BASE + "SI001";

    /****************************************************************************************
     *                                CAT. TRATADO ACUERDO
     ****************************************************************************************/

    /** Error cuando no se encuentra un tratado acuerdo con el identificador indicado. */
    public static final String TRATADO_ACUERDO_NO_ENCONTRADO = CODIGO_BASE + "TA001";

    /****************************************************************************************
     *                         CAT. IDENTIFICADOR PREVALIDADOR
     ****************************************************************************************/

    /** Error cuando no se encuentra un identificador prevalidador con el identificador indicado. */
    public static final String IDENTIFICADOR_PREVALIDADOR_NO_ENCONTRADO = CODIGO_BASE + "IP001";

    /****************************************************************************************
     *                                   CAT. SIT DOM IDC
     ****************************************************************************************/

    /** Error cuando no se encuentra un registro sit dom idc con el identificador indicado. */
    public static final String SIT_DOM_IDC_NO_ENCONTRADO = CODIGO_BASE + "SDI001";

    /****************************************************************************************
     *                                  CAT. LEYENDA TEXTO
     ****************************************************************************************/

    /** Error cuando no se encuentra una leyenda texto con el identificador indicado. */
    public static final String LEYENDA_TEXTO_NO_ENCONTRADA = CODIGO_BASE + "LT001";

    /****************************************************************************************
     *                                CAT. ESQUEMA REGLA 8
     ****************************************************************************************/

    /** Error cuando no se encuentra un esquema regla 8 con el identificador indicado. */
    public static final String ESQUEMA_REGLA8_NO_ENCONTRADO = CODIGO_BASE + "ER001";

    /****************************************************************************************
     *                                   CAT. CATALOGO H
     ****************************************************************************************/

    /** Error cuando no se encuentra un registro catalogo H con la clave indicada. */
    public static final String CATALOGO_H_NO_ENCONTRADO = CODIGO_BASE + "CH001";

    /****************************************************************************************
     *                              CAT. TRATAMIENTO ESPECIAL
     ****************************************************************************************/

    /** Error cuando no se encuentra un tratamiento especial con el identificador indicado. */
    public static final String TRATAMIENTO_ESPECIAL_NO_ENCONTRADO = CODIGO_BASE + "TE001";

    /****************************************************************************************
     *                                  CAT. CONDICION USO
     ****************************************************************************************/

    /** Error cuando no se encuentra una condicion de uso con el identificador indicado. */
    public static final String CONDICION_USO_NO_ENCONTRADA = CODIGO_BASE + "CU001";

    /****************************************************************************************
     *                                      CAT. CAS
     ****************************************************************************************/

    /** Error cuando no se encuentra un registro CAS con el identificador indicado. */
    public static final String CAS_NO_ENCONTRADO = CODIGO_BASE + "CAS001";

    /****************************************************************************************
     *                              CAT. TIPO EMPRESA RECIF
     ****************************************************************************************/

    /** Error cuando no se encuentra un tipo empresa RECIF con la clave indicada. */
    public static final String TIPO_EMPRESA_RECIF_NO_ENCONTRADO = CODIGO_BASE + "TER001";

    /****************************************************************************************
     *                                   CAT. DELEG MUN
     ****************************************************************************************/

    /** Error cuando no se encuentra una delegacion municipio con la clave indicada. */
    public static final String DELEG_MUN_NO_ENCONTRADA = CODIGO_BASE + "DM001";

    /****************************************************************************************
     *                                   CAT. LOCALIDAD
     ****************************************************************************************/

    /** Error cuando no se encuentra una localidad con la clave indicada. */
    public static final String LOCALIDAD_NO_ENCONTRADA = CODIGO_BASE + "LO001";

    /****************************************************************************************
     *                                    CAT. COLONIA
     ****************************************************************************************/

    /** Error cuando no se encuentra una colonia con la clave indicada. */
    public static final String COLONIA_NO_ENCONTRADA = CODIGO_BASE + "COL001";

    /****************************************************************************************
     *                                   CAT. SUPLENCIA
     ****************************************************************************************/

    /** Error cuando no se encuentra una suplencia con el identificador indicado. */
    public static final String SUPLENCIA_NO_ENCONTRADA = CODIGO_BASE + "SU001";

    /****************************************************************************************
     *                                  CAT. EQUIV MONEDA
     ****************************************************************************************/

    /** Error cuando no se encuentra una equivalencia de moneda con el identificador indicado. */
    public static final String EQUIV_MONEDA_NO_ENCONTRADA = CODIGO_BASE + "EM001";

    /****************************************************************************************
     *                                   CAT. CATALOGO D
     ****************************************************************************************/

    /** Error cuando no se encuentra un registro catalogo D con la clave indicada. */
    public static final String CATALOGO_D_NO_ENCONTRADO = CODIGO_BASE + "CATD001";

    /****************************************************************************************
     *                                CAT. NORMAL OFICIAL
     ****************************************************************************************/

    /** Error cuando no se encuentra una norma oficial con el identificador indicado. */
    public static final String NORMAL_OFICIAL_NO_ENCONTRADO = CODIGO_BASE + "NO001";

    /****************************************************************************************
     *                               CAT. CATEGORIA TEXTIL
     ****************************************************************************************/

    /** Error cuando no se encuentra una categoria textil con el identificador indicado. */
    public static final String CATEGORIA_TEXTIL_NO_ENCONTRADO = CODIGO_BASE + "CT001";

    /****************************************************************************************
     *                              CAT. RECINTO FISCALIZADO
     ****************************************************************************************/

    /** Error cuando no se encuentra un recinto fiscalizado con el identificador indicado. */
    public static final String RECINTO_FISCALIZADO_NO_ENCONTRADO = CODIGO_BASE + "RF001";

    /****************************************************************************************
     *                                CAT. VIDA SILVESTRE
     ****************************************************************************************/

    /** Error cuando no se encuentra un registro vida silvestre con el identificador indicado. */
    public static final String VIDA_SILVESTRE_NO_ENCONTRADO = CODIGO_BASE + "VS001";

    /****************************************************************************************
     *                            CAT. UNIDAD ADMINISTRATIVA
     ****************************************************************************************/

    /** Error cuando no se encuentra una unidad administrativa con la clave indicada. */
    public static final String UNIDAD_ADMINISTRATIVA_NO_ENCONTRADA = CODIGO_BASE + "UA001";

    /****************************************************************************************
     *                                  CAT. TIPO TRAMITE
     ****************************************************************************************/

    /** Error cuando no se encuentra un tipo de tramite con el identificador indicado. */
    public static final String TIPO_TRAMITE_NO_ENCONTRADO = CODIGO_BASE + "TT001";

    /****************************************************************************************
     *                                CAT. FRACCION ALADI
     ****************************************************************************************/

    /** Error cuando no se encuentra una fraccion ALADI con el identificador indicado. */
    public static final String FRACCION_ALADI_NO_ENCONTRADA = CODIGO_BASE + "FA001";

    /****************************************************************************************
     *                                CAT. FUNDAMENTO TTRA
     ****************************************************************************************/

    /** Error cuando no se encuentra un fundamento ttra con el identificador indicado. */
    public static final String FUNDAMENTO_TTRA_NO_ENCONTRADO = CODIGO_BASE + "FT001";

    /****************************************************************************************
     *                          CAT. CLASIF TOXICOLOGICA TTRA
     ****************************************************************************************/

    /** Error cuando no se encuentra una clasificacion toxicologica ttra con el identificador indicado. */
    public static final String CLASIF_TOXICOLOGICA_TTRA_NO_ENCONTRADA = CODIGO_BASE + "CTT001";

    /****************************************************************************************
     *                                  CAT. SECCION TTRA
     ****************************************************************************************/

    /** Error cuando no se encuentra una seccion ttra con el identificador indicado. */
    public static final String SECCION_TTRA_NO_ENCONTRADA = CODIGO_BASE + "ST001";

    /****************************************************************************************
     *                             CAT. MEDIO TRANSPORTE TTRA
     ****************************************************************************************/

    /** Error cuando no se encuentra un medio de transporte ttra con el identificador indicado. */
    public static final String MEDIO_TRANSPORTE_TTRA_NO_ENCONTRADO = CODIGO_BASE + "MT001";

    /****************************************************************************************
     *                              CAT. TIPO PRODUCTO TTRA
     ****************************************************************************************/

    /** Error cuando no se encuentra un tipo de producto ttra con el identificador indicado. */
    public static final String TIPO_PRODUCTO_TTRA_NO_ENCONTRADO = CODIGO_BASE + "TPT001";

    /****************************************************************************************
     *                                 CAT. REGIMEN TTRA
     ****************************************************************************************/

    /** Error cuando no se encuentra un regimen ttra con el identificador indicado. */
    public static final String REGIMEN_TTRA_NO_ENCONTRADO = CODIGO_BASE + "RT001";

    /****************************************************************************************
     *                                  CAT. ADUANA TTRA
     ****************************************************************************************/

    /** Error cuando no se encuentra una aduana ttra con el identificador indicado. */
    public static final String ADUANA_TTRA_NO_ENCONTRADA = CODIGO_BASE + "ADT001";

    /****************************************************************************************
     *                                 CAT. EMPRESA RECIF
     ****************************************************************************************/

    /** Error cuando no se encuentra una empresa RECIF con la clave indicada. */
    public static final String EMPRESA_RECIF_NO_ENCONTRADA = CODIGO_BASE + "ERC001";

    /****************************************************************************************
     *                               CAT. FRACCION HTS USA
     ****************************************************************************************/

    /** Error cuando no se encuentra una fraccion HTS USA con el identificador indicado. */
    public static final String FRACCION_HTS_USA_NO_ENCONTRADA = CODIGO_BASE + "FHU001";

    /****************************************************************************************
     *                                CAT. APROB CERT SE
     ****************************************************************************************/

    /** Error cuando no se encuentra una aprobacion certificado SE con el identificador indicado. */
    public static final String APROB_CERT_SE_NO_ENCONTRADA = CODIGO_BASE + "ACS001";

    /****************************************************************************************
     *                           CAT. PAIS RESTRICCION TTRA
     ****************************************************************************************/

    /** Error cuando no se encuentra un pais restriccion ttra con el identificador indicado. */
    public static final String PAIS_RESTRICCION_TTRA_NO_ENCONTRADO = CODIGO_BASE + "PRT001";

    /****************************************************************************************
     *                             CAT. USO MERCANCIA TTRA
     ****************************************************************************************/

    /** Error cuando no se encuentra un uso de mercancia ttra con el identificador indicado. */
    public static final String USO_MERCANCIA_TTRA_NO_ENCONTRADO = CODIGO_BASE + "UMT001";

    /****************************************************************************************
     *                          CAT. USO ESPEC MERCANCIA TTRA
     ****************************************************************************************/

    /** Error cuando no se encuentra un uso especial de mercancia ttra con el identificador indicado. */
    public static final String USO_ESPEC_MERCANCIA_TTRA_NO_ENCONTRADO = CODIGO_BASE + "UEMT001";

    /****************************************************************************************
     *                             CAT. UNIDAD MEDIDA TTRA
     ****************************************************************************************/

    /** Error cuando no se encuentra una unidad de medida ttra con el identificador indicado. */
    public static final String UNIDAD_MEDIDA_TTRA_NO_ENCONTRADA = CODIGO_BASE + "UMDT001";

    /****************************************************************************************
     *                           CAT. CONTROL REFERENCIA
     ****************************************************************************************/

    /** Error cuando no se encuentra un control de referencia con el identificador indicado. */
    public static final String CONTROL_REFERENCIA_NO_ENCONTRADO = CODIGO_BASE + "CR001";

    /****************************************************************************************
     *                             CAT. TIPO CERTIFICADO
     ****************************************************************************************/

    /** Error cuando no se encuentra un tipo de certificado con el identificador indicado. */
    public static final String TIPO_CERTIFICADO_NO_ENCONTRADO = CODIGO_BASE + "TC001";

    /****************************************************************************************
     *                           CAT. JUSTIFICACION TTRA
     ****************************************************************************************/

    /** Error cuando no se encuentra una justificacion ttra con el identificador indicado. */
    public static final String JUSTIFICACION_TTRA_NO_ENCONTRADA = CODIGO_BASE + "JT001";

    /****************************************************************************************
     *                            CAT. LABORATORIO TTRA
     ****************************************************************************************/

    /** Error cuando no se encuentra un laboratorio ttra con el identificador indicado. */
    public static final String LABORATORIO_TTRA_NO_ENCONTRADO = CODIGO_BASE + "LT001";

    /****************************************************************************************
     *                              CAT. MOTIVO TTRA
     ****************************************************************************************/

    /** Error cuando no se encuentra un motivo ttra con el identificador indicado. */
    public static final String MOTIVO_TTRA_NO_ENCONTRADO = CODIGO_BASE + "MT001";

    /****************************************************************************************
     *                            CAT. OBSERVACION TTRA
     ****************************************************************************************/

    /** Error cuando no se encuentra una observacion ttra con el identificador indicado. */
    public static final String OBSERVACION_TTRA_NO_ENCONTRADA = CODIGO_BASE + "OT001";

    /****************************************************************************************
     *                            CAT. RESTRICCION TTRA
     ****************************************************************************************/

    /** Error cuando no se encuentra una restriccion ttra con el identificador indicado. */
    public static final String RESTRICCION_TTRA_NO_ENCONTRADA = CODIGO_BASE + "RT001";

    /****************************************************************************************
     *                         CAT. ACTIVIDAD ECONOMICA SAT
     ****************************************************************************************/

    /** Error cuando no se encuentra una actividad economica SAT con el identificador indicado. */
    public static final String ACTIVIDAD_ECONOMICA_SAT_NO_ENCONTRADA = CODIGO_BASE + "AES001";

    public static final String MONTO_EXPORTACION_NO_ENCONTRADO = CODIGO_BASE + "ME001";
    public static final String EQUIVALENCIA_AELC_NO_ENCONTRADA = CODIGO_BASE + "EA001";
    public static final String DIA_NO_LABORABLE_NO_ENCONTRADO = CODIGO_BASE + "DNL001";
    public static final String CATALOGO_D_TR_NO_ENCONTRADO = CODIGO_BASE + "CDT001";
    public static final String TIPO_RFC_NO_ENCONTRADO = CODIGO_BASE + "TRF001";
    public static final String DECLARACION_TRAMITE_NO_ENCONTRADO = CODIGO_BASE + "DTR001";
    public static final String DICTAMEN_TRAMITE_NO_ENCONTRADO = CODIGO_BASE + "DIT001";
    public static final String DOCUMENTO_TRAMITE_NO_ENCONTRADO = CODIGO_BASE + "DOT001";
    public static final String PLAZO_MAXIMO_AUT_TRAMITE_NO_ENCONTRADO = CODIGO_BASE + "PMAT001";
    public static final String PLAZO_TTRA_NO_ENCONTRADO = CODIGO_BASE + "PT001";
    public static final String TARIFA_NO_ENCONTRADA = CODIGO_BASE + "TAR001";
    public static final String CLASIFICACION_REGIMEN_NO_ENCONTRADO = CODIGO_BASE + "CLR001";
    public static final String PARTIDA_FRACCION_NO_ENCONTRADA = CODIGO_BASE + "PF001";
    public static final String PAIS_TRATADO_ACUERDO_NO_ENCONTRADO = CODIGO_BASE + "PTA001";
    public static final String TRATADO_BLOQUE_NO_ENCONTRADO = CODIGO_BASE + "TB001";
    public static final String TRATADO_BLOQUE_PAI_NO_ENCONTRADO = CODIGO_BASE + "TBP001";
    public static final String UNIDAD_ADMIN_ADUANA_NO_ENCONTRADO = CODIGO_BASE + "UAA001";
    public static final String UNIDAD_ADMIN_VECINA_NO_ENCONTRADO = CODIGO_BASE + "UAV001";
    public static final String SUBPARTIDA_FRACCION_NO_ENCONTRADA = CODIGO_BASE + "SPF001";
    public static final String COMBINACION_SG_NO_ENCONTRADA = CODIGO_BASE + "CSG001";
    public static final String VIGENCIA_SERVICIO_NO_ENCONTRADA = CODIGO_BASE + "VS001";
    public static final String RESTRIC_DESC_PROD_NO_ENCONTRADO = CODIGO_BASE + "RDP001";
    public static final String FRACCION_ARANCELARIA_NO_ENCONTRADA = CODIGO_BASE + "FA001";
    public static final String FRACCION_TTRA_NO_ENCONTRADA = CODIGO_BASE + "FT001";
    public static final String SUBDIVISION_FRACCION_NO_ENCONTRADA = CODIGO_BASE + "SF001";
    public static final String FRACCION_TTRA_DESC_PROD_NO_ENCONTRADA = CODIGO_BASE + "FTDP001";
    public static final String ISOTOPO_FRACCION_NO_ENCONTRADO = CODIGO_BASE + "IF001";
    public static final String CAS_FRACCION_TTRA_NO_ENCONTRADO = CODIGO_BASE + "CFT001";
    public static final String ARANCEL_PROSEC_NO_ENCONTRADO = CODIGO_BASE + "AP001";

}
