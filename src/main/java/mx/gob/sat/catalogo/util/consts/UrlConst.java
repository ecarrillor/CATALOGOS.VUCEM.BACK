package mx.gob.sat.catalogo.util.consts;

/**
 * <b>Class:</b> UrlConst.java <br>
 * <b>Description:</b>
 * <p>Contiene las constantes de las urls de los endpoint
 * que se exponen para el API REST.
 * </p>
 *
 * @author Javier Chávez Barrios
 * @email jchavezb@ultrasist.com.mx
 * 
 * @created 17 de febrero del 2025
 * @version 1.0
 * @category Constantes
 */
public final class UrlConst {

    private UrlConst() {
        throw new IllegalStateException("Clase UrlConst no puede ser instanciada");
    }

    /**
     * Representa la url base del API.
     */
    public static final String URL_BASE = "api/catalogo-admin";

    /**
     * Representa la url para consultar la versión de la aplicación.
     */
    public static final String APLICACION_VERSION = "/aplicacion/version";

    /****************************************************************************************
     *                                      EJEMPLO
     ****************************************************************************************/
    /**
     * Representa la url de ejemplo.
     */
    public static final String EJEMPLO_CONSULTA_DATOS = "/ejemplo";

    /****************************************************************************************
     *                                    CAT. ADUANA
     ****************************************************************************************/

    /** Prefijo de las URLs del catalogo de aduanas. */
    public static final String ADUANA_BASE = "/aduana";

    /** URL para listar aduanas paginadas. */
    public static final String ADUANA_LISTAR = ADUANA_BASE;

    /** URL para crear una nueva aduana. */
    public static final String ADUANA_CREAR = ADUANA_BASE;

    /** URL para buscar una aduana por clave. */
    public static final String ADUANA_BUSCAR = ADUANA_BASE + "/{cveAduana}";

    /** URL para actualizar una aduana por clave. */
    public static final String ADUANA_ACTUALIZAR = ADUANA_BASE + "/{cveAduana}";

    /** URL para listar los tipos de aduana. */
    public static final String ADUANA_TIPOS = ADUANA_BASE + "/tipos";

    /** URL para listar las entidades federativas. */
    public static final String ADUANA_ENTIDADES = ADUANA_BASE + "/entidades";

    /** URL para obtener la ultima clave de aduana. */
    public static final String ADUANA_ULTIMO_ID = ADUANA_BASE + "/ultimo-id";

    /****************************************************************************************
     *                                  CAT. TIPO ADUANA
     ****************************************************************************************/

    /** Prefijo de las URLs del catalogo de tipos de aduana. */
    public static final String TIPO_ADUANA_BASE = "/tipo-aduana";

    /** URL para listar tipos de aduana paginados. */
    public static final String TIPO_ADUANA_LISTAR = TIPO_ADUANA_BASE;

    /** URL para crear un nuevo tipo de aduana. */
    public static final String TIPO_ADUANA_CREAR = TIPO_ADUANA_BASE;

    /** URL para buscar un tipo de aduana por clave. */
    public static final String TIPO_ADUANA_BUSCAR = TIPO_ADUANA_BASE + "/{cveTipoAduana}";

    /** URL para actualizar un tipo de aduana por clave. */
    public static final String TIPO_ADUANA_ACTUALIZAR = TIPO_ADUANA_BASE + "/{cveTipoAduana}";

    /****************************************************************************************
     *                                    CAT. MONEDA
     ****************************************************************************************/

    /** Prefijo de las URLs del catalogo de monedas. */
    public static final String MONEDA_BASE = "/moneda";

    /** URL para listar monedas paginadas. */
    public static final String MONEDA_LISTAR = MONEDA_BASE;

    /** URL para crear una nueva moneda. */
    public static final String MONEDA_CREAR = MONEDA_BASE;

    /** URL para buscar una moneda por clave. */
    public static final String MONEDA_BUSCAR = MONEDA_BASE + "/{cveMoneda}";

    /** URL para actualizar una moneda por clave. */
    public static final String MONEDA_ACTUALIZAR = MONEDA_BASE + "/{cveMoneda}";

    /****************************************************************************************
     *                                    CAT. ENTIDAD
     ****************************************************************************************/

    /** Prefijo de las URLs del catalogo de entidades federativas. */
    public static final String ENTIDAD_BASE = "/entidad";

    /** URL para listar entidades paginadas. */
    public static final String ENTIDAD_LISTAR = ENTIDAD_BASE;

    /** URL para crear una nueva entidad. */
    public static final String ENTIDAD_CREAR = ENTIDAD_BASE;

    /** URL para buscar una entidad por clave. */
    public static final String ENTIDAD_BUSCAR = ENTIDAD_BASE + "/{cveEntidad}";

    /** URL para actualizar una entidad por clave. */
    public static final String ENTIDAD_ACTUALIZAR = ENTIDAD_BASE + "/{cveEntidad}";

    /** URL para listar paises disponibles (usado en entidad). */
    public static final String ENTIDAD_PAISES = ENTIDAD_BASE + "/paises";

    /** URL para listar entidades en formato select. */
    public static final String ENTIDAD_NOMBRES = ENTIDAD_BASE + "/nombres";

    /****************************************************************************************
     *                                      CAT. PAIS
     ****************************************************************************************/

    /** Prefijo de las URLs del catalogo de paises. */
    public static final String PAIS_BASE = "/pais";

    /** URL para listar paises paginados. */
    public static final String PAIS_LISTAR = PAIS_BASE;

    /** URL para crear un nuevo pais. */
    public static final String PAIS_CREAR = PAIS_BASE;

    /** URL para buscar un pais por clave. */
    public static final String PAIS_BUSCAR = PAIS_BASE + "/{cvePais}";

    /** URL para actualizar un pais por clave. */
    public static final String PAIS_ACTUALIZAR = PAIS_BASE + "/{cvePais}";

    /** URL para listar monedas disponibles (usado en pais). */
    public static final String PAIS_MONEDAS = PAIS_BASE + "/monedas";

    /****************************************************************************************
     *                                  CAT. CALENDARIO
     ****************************************************************************************/

    /** Prefijo de las URLs del catalogo de calendarios. */
    public static final String CALENDARIO_BASE = "/calendario";

    /** URL para listar calendarios paginados. */
    public static final String CALENDARIO_LISTAR = CALENDARIO_BASE;

    /** URL para crear un nuevo calendario. */
    public static final String CALENDARIO_CREAR = CALENDARIO_BASE;

    /** URL para buscar un calendario por clave. */
    public static final String CALENDARIO_BUSCAR = CALENDARIO_BASE + "/{cveCalendario}";

    /** URL para actualizar un calendario por clave. */
    public static final String CALENDARIO_ACTUALIZAR = CALENDARIO_BASE + "/{cveCalendario}";

    /****************************************************************************************
     *                                    CAT. GENERO
     ****************************************************************************************/

    /** Prefijo de las URLs del catalogo de generos. */
    public static final String GENERO_BASE = "/genero";

    /** URL para listar generos paginados. */
    public static final String GENERO_LISTAR = GENERO_BASE;

    /** URL para crear un nuevo genero. */
    public static final String GENERO_CREAR = GENERO_BASE;

    /** URL para buscar un genero por identificador. */
    public static final String GENERO_BUSCAR = GENERO_BASE + "/{idGenero}";

    /** URL para actualizar un genero por identificador. */
    public static final String GENERO_ACTUALIZAR = GENERO_BASE + "/{idGenero}";

    /****************************************************************************************
     *                                   CAT. LENGUAJE
     ****************************************************************************************/

    /** Prefijo de las URLs del catalogo de lenguajes. */
    public static final String LENGUAJE_BASE = "/lenguaje";

    /** URL para listar lenguajes paginados. */
    public static final String LENGUAJE_LISTAR = LENGUAJE_BASE;

    /** URL para crear un nuevo lenguaje. */
    public static final String LENGUAJE_CREAR = LENGUAJE_BASE;

    /** URL para buscar un lenguaje por clave. */
    public static final String LENGUAJE_BUSCAR = LENGUAJE_BASE + "/{cveLenguaje}";

    /** URL para actualizar un lenguaje por clave. */
    public static final String LENGUAJE_ACTUALIZAR = LENGUAJE_BASE + "/{cveLenguaje}";

    /****************************************************************************************
     *                                     CAT. BANCO
     ****************************************************************************************/

    /** Prefijo de las URLs del catalogo de bancos. */
    public static final String BANCO_BASE = "/banco";

    /** URL para listar bancos activos paginados. */
    public static final String BANCO_LISTAR = BANCO_BASE;

    /** URL para crear un nuevo banco. */
    public static final String BANCO_CREAR = BANCO_BASE;

    /** URL para buscar un banco por clave. */
    public static final String BANCO_BUSCAR = BANCO_BASE + "/{cveBanco}";

    /** URL para actualizar un banco por clave. */
    public static final String BANCO_ACTUALIZAR = BANCO_BASE + "/{cveBanco}";

    /****************************************************************************************
     *                                  CAT. DEPENDENCIA
     ****************************************************************************************/

    /** Prefijo de las URLs del catalogo de dependencias. */
    public static final String DEPENDENCIA_BASE = "/dependencia";

    /** URL para listar dependencias paginadas. */
    public static final String DEPENDENCIA_LISTAR = DEPENDENCIA_BASE;

    /** URL para crear una nueva dependencia. */
    public static final String DEPENDENCIA_CREAR = DEPENDENCIA_BASE;

    /** URL para buscar una dependencia por identificador. */
    public static final String DEPENDENCIA_BUSCAR = DEPENDENCIA_BASE + "/{id}";

    /** URL para actualizar una dependencia por identificador. */
    public static final String DEPENDENCIA_ACTUALIZAR = DEPENDENCIA_BASE + "/{id}";

    /** URL para listar calendarios disponibles (usado en dependencia). */
    public static final String DEPENDENCIA_CALENDARIOS = DEPENDENCIA_BASE + "/calendarios";

    /****************************************************************************************
     *                                   CAT. PARAMETRO
     ****************************************************************************************/

    /** Prefijo de las URLs del catalogo de parametros. */
    public static final String PARAMETRO_BASE = "/parametro";

    /** URL para listar parametros paginados. */
    public static final String PARAMETRO_LISTAR = PARAMETRO_BASE;

    /** URL para crear un nuevo parametro. */
    public static final String PARAMETRO_CREAR = PARAMETRO_BASE;

    /** URL para buscar un parametro por clave. */
    public static final String PARAMETRO_BUSCAR = PARAMETRO_BASE + "/{cveParametro}";

    /** URL para actualizar un parametro por clave. */
    public static final String PARAMETRO_ACTUALIZAR = PARAMETRO_BASE + "/{cveParametro}";

    /** URL para listar dependencias disponibles (usado en parametro). */
    public static final String PARAMETRO_DEPENDENCIAS = PARAMETRO_BASE + "/dependencias";

    /****************************************************************************************
     *                                      CAT. AGA
     ****************************************************************************************/

    /** Prefijo de las URLs del catalogo de AGA. */
    public static final String AGA_BASE = "/aga";

    /** URL para listar registros AGA paginados. */
    public static final String AGA_LISTAR = AGA_BASE;

    /** URL para crear un nuevo registro AGA. */
    public static final String AGA_CREAR = AGA_BASE;

    /** URL para buscar un registro AGA por clave. */
    public static final String AGA_BUSCAR = AGA_BASE + "/{cveParametro}";

    /** URL para actualizar un registro AGA por clave. */
    public static final String AGA_ACTUALIZAR = AGA_BASE + "/{cveParametro}";

    /****************************************************************************************
     *                                    CAT. REGIMEN
     ****************************************************************************************/

    /** Prefijo de las URLs del catalogo de regimenes. */
    public static final String REGIMEN_BASE = "/regimen";

    /** URL para listar regimenes paginados. */
    public static final String REGIMEN_LISTAR = REGIMEN_BASE;

    /** URL para crear un nuevo regimen. */
    public static final String REGIMEN_CREAR = REGIMEN_BASE;

    /** URL para buscar un regimen por clave. */
    public static final String REGIMEN_BUSCAR = REGIMEN_BASE + "/{cveRegimen}";

    /** URL para actualizar un regimen por clave. */
    public static final String REGIMEN_ACTUALIZAR = REGIMEN_BASE + "/{cveRegimen}";

    /****************************************************************************************
     *                                 CAT. UNIDAD DE MEDIDA
     ****************************************************************************************/

    /** Prefijo de las URLs del catalogo de unidades de medida. */
    public static final String UNIDAD_MEDIDA_BASE = "/unidad-medida";

    /** URL para listar unidades de medida paginadas. */
    public static final String UNIDAD_MEDIDA_LISTAR = UNIDAD_MEDIDA_BASE;

    /** URL para crear una nueva unidad de medida. */
    public static final String UNIDAD_MEDIDA_CREAR = UNIDAD_MEDIDA_BASE;

    /** URL para buscar una unidad de medida por clave. */
    public static final String UNIDAD_MEDIDA_BUSCAR = UNIDAD_MEDIDA_BASE + "/{cveUnidadMedida}";

    /** URL para actualizar una unidad de medida por clave. */
    public static final String UNIDAD_MEDIDA_ACTUALIZAR = UNIDAD_MEDIDA_BASE + "/{cveUnidadMedida}";

    /****************************************************************************************
     *                                  CAT. SECTOR PROSEC
     ****************************************************************************************/

    /** Prefijo de las URLs del catalogo de sector prosec. */
    public static final String SECTOR_PROSEC_BASE = "/sector-prosec";

    /** URL para listar registros sector prosec paginados. */
    public static final String SECTOR_PROSEC_LISTAR = SECTOR_PROSEC_BASE;

    /** URL para crear un nuevo registro sector prosec. */
    public static final String SECTOR_PROSEC_CREAR = SECTOR_PROSEC_BASE;

    /** URL para buscar un registro sector prosec por clave. */
    public static final String SECTOR_PROSEC_BUSCAR = SECTOR_PROSEC_BASE + "/{cveSectorProsec}";

    /** URL para actualizar un registro sector prosec por clave. */
    public static final String SECTOR_PROSEC_ACTUALIZAR = SECTOR_PROSEC_BASE + "/{cveSectorProsec}";

    /****************************************************************************************
     *                                      CAT. SCIAN
     ****************************************************************************************/

    /** Prefijo de las URLs del catalogo SCIAN. */
    public static final String SCIAN_BASE = "/scian";

    /** URL para listar registros SCIAN paginados. */
    public static final String SCIAN_LISTAR = SCIAN_BASE;

    /** URL para crear un nuevo registro SCIAN. */
    public static final String SCIAN_CREAR = SCIAN_BASE;

    /** URL para buscar un registro SCIAN por clave. */
    public static final String SCIAN_BUSCAR = SCIAN_BASE + "/{cveScian}";

    /** URL para actualizar un registro SCIAN por clave. */
    public static final String SCIAN_ACTUALIZAR = SCIAN_BASE + "/{cveScian}";

    /****************************************************************************************
     *                                CAT. CAPITULO FRACCION
     ****************************************************************************************/

    /** Prefijo de las URLs del catalogo de capitulo fraccion. */
    public static final String CAPITULO_FRACCION_BASE = "/capitulo-fraccion";

    /** URL para listar registros capitulo fraccion paginados. */
    public static final String CAPITULO_FRACCION_LISTAR = CAPITULO_FRACCION_BASE;

    /** URL para crear un nuevo registro capitulo fraccion. */
    public static final String CAPITULO_FRACCION_CREAR = CAPITULO_FRACCION_BASE;

    /** URL para buscar un registro capitulo fraccion por clave. */
    public static final String CAPITULO_FRACCION_BUSCAR = CAPITULO_FRACCION_BASE + "/{cveCapituloFraccion}";

    /** URL para actualizar un registro capitulo fraccion por clave. */
    public static final String CAPITULO_FRACCION_ACTUALIZAR = CAPITULO_FRACCION_BASE + "/{cveCapituloFraccion}";

    /****************************************************************************************
     *                                CAT. CRITERIO ORIGEN
     ****************************************************************************************/

    /** Prefijo de las URLs del catalogo de criterio de origen. */
    public static final String CRITERIO_ORIGEN_BASE = "/criterio-origen";

    /** URL para listar registros criterio origen paginados. */
    public static final String CRITERIO_ORIGEN_LISTAR = CRITERIO_ORIGEN_BASE;

    /** URL para crear un nuevo registro criterio origen. */
    public static final String CRITERIO_ORIGEN_CREAR = CRITERIO_ORIGEN_BASE;

    /** URL para buscar un registro criterio origen por clave. */
    public static final String CRITERIO_ORIGEN_BUSCAR = CRITERIO_ORIGEN_BASE + "/{cveCriterioOrigen}";

    /** URL para actualizar un registro criterio origen por clave. */
    public static final String CRITERIO_ORIGEN_ACTUALIZAR = CRITERIO_ORIGEN_BASE + "/{cveCriterioOrigen}";

    /****************************************************************************************
     *                                   CAT. DECLARACION
     ****************************************************************************************/

    /** Prefijo de las URLs del catalogo de declaracion. */
    public static final String DECLARACION_BASE = "/declaracion";

    /** URL para listar declaraciones paginadas. */
    public static final String DECLARACION_LISTAR = DECLARACION_BASE;

    /** URL para crear una nueva declaracion. */
    public static final String DECLARACION_CREAR = DECLARACION_BASE;

    /** URL para buscar una declaracion por clave. */
    public static final String DECLARACION_BUSCAR = DECLARACION_BASE + "/{cveDeclaracion}";

    /** URL para actualizar una declaracion por clave. */
    public static final String DECLARACION_ACTUALIZAR = DECLARACION_BASE + "/{cveDeclaracion}";

    /****************************************************************************************
     *                                    CAT. PRODUCTO
     ****************************************************************************************/

    /** Prefijo de las URLs del catalogo de productos. */
    public static final String PRODUCTO_BASE = "/producto";

    /** URL para listar productos paginados. */
    public static final String PRODUCTO_LISTAR = PRODUCTO_BASE;

    /** URL para crear un nuevo producto. */
    public static final String PRODUCTO_CREAR = PRODUCTO_BASE;

    /** URL para buscar un producto por clave. */
    public static final String PRODUCTO_BUSCAR = PRODUCTO_BASE + "/{cveProducto}";

    /** URL para actualizar un producto por clave. */
    public static final String PRODUCTO_ACTUALIZAR = PRODUCTO_BASE + "/{cveProducto}";

    /****************************************************************************************
     *                                CAT. PATENTE ADUANAL
     ****************************************************************************************/

    /** Prefijo de las URLs del catalogo de patente aduanal. */
    public static final String PATENTE_ADUANAL_BASE = "/patente-aduanal";

    /** URL para listar patentes aduanales paginadas. */
    public static final String PATENTE_ADUANAL_LISTAR = PATENTE_ADUANAL_BASE;

    /** URL para crear una nueva patente aduanal. */
    public static final String PATENTE_ADUANAL_CREAR = PATENTE_ADUANAL_BASE;

    /** URL para buscar una patente aduanal por clave. */
    public static final String PATENTE_ADUANAL_BUSCAR = PATENTE_ADUANAL_BASE + "/{cvePatenteAduanal}";

    /** URL para actualizar una patente aduanal por clave. */
    public static final String PATENTE_ADUANAL_ACTUALIZAR = PATENTE_ADUANAL_BASE + "/{cvePatenteAduanal}";

    /****************************************************************************************
     *                                    CAT. ESPECIE
     ****************************************************************************************/

    /** Prefijo de las URLs del catalogo de especies. */
    public static final String ESPECIE_BASE = "/especie";

    /** URL para listar especies paginadas. */
    public static final String ESPECIE_LISTAR = ESPECIE_BASE;

    /** URL para crear una nueva especie. */
    public static final String ESPECIE_CREAR = ESPECIE_BASE;

    /** URL para buscar una especie por identificador. */
    public static final String ESPECIE_BUSCAR = ESPECIE_BASE + "/{idEspecie}";

    /** URL para actualizar una especie por identificador. */
    public static final String ESPECIE_ACTUALIZAR = ESPECIE_BASE + "/{idEspecie}";

    /****************************************************************************************
     *                                  CAT. SEMANA LABORAL
     ****************************************************************************************/

    /** Prefijo de las URLs del catalogo de semana laboral. */
    public static final String SEMANA_LABORAL_BASE = "/semana-laboral";

    /** URL para listar semanas laborales paginadas. */
    public static final String SEMANA_LABORAL_LISTAR = SEMANA_LABORAL_BASE;

    /** URL para crear una nueva semana laboral. */
    public static final String SEMANA_LABORAL_CREAR = SEMANA_LABORAL_BASE;

    /** URL para buscar una semana laboral por identificador. */
    public static final String SEMANA_LABORAL_BUSCAR = SEMANA_LABORAL_BASE + "/{idSemanaLaboral}";

    /** URL para actualizar una semana laboral por identificador. */
    public static final String SEMANA_LABORAL_ACTUALIZAR = SEMANA_LABORAL_BASE + "/{idSemanaLaboral}";

    /****************************************************************************************
     *                                CAT. DESCRIPCION PROD
     ****************************************************************************************/

    /** Prefijo de las URLs del catalogo de descripcion de producto. */
    public static final String DESCRIPCION_PROD_BASE = "/descripcion-prod";

    /** URL para listar descripciones de producto paginadas. */
    public static final String DESCRIPCION_PROD_LISTAR = DESCRIPCION_PROD_BASE;

    /** URL para crear una nueva descripcion de producto. */
    public static final String DESCRIPCION_PROD_CREAR = DESCRIPCION_PROD_BASE;

    /** URL para buscar una descripcion de producto por identificador. */
    public static final String DESCRIPCION_PROD_BUSCAR = DESCRIPCION_PROD_BASE + "/{idDescripcionProd}";

    /** URL para actualizar una descripcion de producto por identificador. */
    public static final String DESCRIPCION_PROD_ACTUALIZAR = DESCRIPCION_PROD_BASE + "/{idDescripcionProd}";

    /****************************************************************************************
     *                               CAT. PUNTO VERIFICACION
     ****************************************************************************************/

    /** Prefijo de las URLs del catalogo de punto de verificacion. */
    public static final String PUNTO_VERIFICACION_BASE = "/punto-verificacion";

    /** URL para listar puntos de verificacion paginados. */
    public static final String PUNTO_VERIFICACION_LISTAR = PUNTO_VERIFICACION_BASE;

    /** URL para crear un nuevo punto de verificacion. */
    public static final String PUNTO_VERIFICACION_CREAR = PUNTO_VERIFICACION_BASE;

    /** URL para buscar un punto de verificacion por identificador. */
    public static final String PUNTO_VERIFICACION_BUSCAR = PUNTO_VERIFICACION_BASE + "/{idPuntoVerificacion}";

    /** URL para actualizar un punto de verificacion por identificador. */
    public static final String PUNTO_VERIFICACION_ACTUALIZAR = PUNTO_VERIFICACION_BASE + "/{idPuntoVerificacion}";

    /****************************************************************************************
     *                            CAT. CRITERIO DICTAMINACION
     ****************************************************************************************/

    /** Prefijo de las URLs del catalogo de criterio de dictaminacion. */
    public static final String CRITERIO_DICTAMINACION_BASE = "/criterio-dictaminacion";

    /** URL para listar criterios de dictaminacion paginados. */
    public static final String CRITERIO_DICTAMINACION_LISTAR = CRITERIO_DICTAMINACION_BASE;

    /** URL para crear un nuevo criterio de dictaminacion. */
    public static final String CRITERIO_DICTAMINACION_CREAR = CRITERIO_DICTAMINACION_BASE;

    /** URL para buscar un criterio de dictaminacion por identificador. */
    public static final String CRITERIO_DICTAMINACION_BUSCAR = CRITERIO_DICTAMINACION_BASE + "/{idCriterioDictaminacion}";

    /** URL para actualizar un criterio de dictaminacion por identificador. */
    public static final String CRITERIO_DICTAMINACION_ACTUALIZAR = CRITERIO_DICTAMINACION_BASE + "/{idCriterioDictaminacion}";

    /****************************************************************************************
     *                                  CAT. TIPO DICTAMEN
     ****************************************************************************************/

    /** Prefijo de las URLs del catalogo de tipo de dictamen. */
    public static final String TIPO_DICTAMEN_BASE = "/tipo-dictamen";

    /** URL para listar tipos de dictamen paginados. */
    public static final String TIPO_DICTAMEN_LISTAR = TIPO_DICTAMEN_BASE;

    /** URL para crear un nuevo tipo de dictamen. */
    public static final String TIPO_DICTAMEN_CREAR = TIPO_DICTAMEN_BASE;

    /** URL para buscar un tipo de dictamen por identificador. */
    public static final String TIPO_DICTAMEN_BUSCAR = TIPO_DICTAMEN_BASE + "/{idTipoDictamen}";

    /** URL para actualizar un tipo de dictamen por identificador. */
    public static final String TIPO_DICTAMEN_ACTUALIZAR = TIPO_DICTAMEN_BASE + "/{idTipoDictamen}";

    /****************************************************************************************
     *                                 CAT. TIPO DOCUMENTO
     ****************************************************************************************/

    /** Prefijo de las URLs del catalogo de tipo de documento. */
    public static final String TIPO_DOCUMENTO_BASE = "/tipo-documento";

    /** URL para listar tipos de documento paginados. */
    public static final String TIPO_DOCUMENTO_LISTAR = TIPO_DOCUMENTO_BASE;

    /** URL para crear un nuevo tipo de documento. */
    public static final String TIPO_DOCUMENTO_CREAR = TIPO_DOCUMENTO_BASE;

    /** URL para buscar un tipo de documento por identificador. */
    public static final String TIPO_DOCUMENTO_BUSCAR = TIPO_DOCUMENTO_BASE + "/{idTipoDocumento}";

    /** URL para actualizar un tipo de documento por identificador. */
    public static final String TIPO_DOCUMENTO_ACTUALIZAR = TIPO_DOCUMENTO_BASE + "/{idTipoDocumento}";

    /****************************************************************************************
     *                              CAT. FUNDAMENTO DICTAMEN
     ****************************************************************************************/

    /** Prefijo de las URLs del catalogo de fundamento de dictamen. */
    public static final String FUNDAMENTO_DICTAMEN_BASE = "/fundamento-dictamen";

    /** URL para listar fundamentos de dictamen paginados. */
    public static final String FUNDAMENTO_DICTAMEN_LISTAR = FUNDAMENTO_DICTAMEN_BASE;

    /** URL para crear un nuevo fundamento de dictamen. */
    public static final String FUNDAMENTO_DICTAMEN_CREAR = FUNDAMENTO_DICTAMEN_BASE;

    /** URL para buscar un fundamento de dictamen por identificador. */
    public static final String FUNDAMENTO_DICTAMEN_BUSCAR = FUNDAMENTO_DICTAMEN_BASE + "/{idFundamentoDictamen}";

    /** URL para actualizar un fundamento de dictamen por identificador. */
    public static final String FUNDAMENTO_DICTAMEN_ACTUALIZAR = FUNDAMENTO_DICTAMEN_BASE + "/{idFundamentoDictamen}";

    /****************************************************************************************
     *                                CAT. SERVICIO IMMEX
     ****************************************************************************************/

    /** Prefijo de las URLs del catalogo de servicio IMMEX. */
    public static final String SERVICIO_IMMEX_BASE = "/servicio-immex";

    /** URL para listar servicios IMMEX paginados. */
    public static final String SERVICIO_IMMEX_LISTAR = SERVICIO_IMMEX_BASE;

    /** URL para crear un nuevo servicio IMMEX. */
    public static final String SERVICIO_IMMEX_CREAR = SERVICIO_IMMEX_BASE;

    /** URL para buscar un servicio IMMEX por identificador. */
    public static final String SERVICIO_IMMEX_BUSCAR = SERVICIO_IMMEX_BASE + "/{idServicioImmex}";

    /** URL para actualizar un servicio IMMEX por identificador. */
    public static final String SERVICIO_IMMEX_ACTUALIZAR = SERVICIO_IMMEX_BASE + "/{idServicioImmex}";

    /****************************************************************************************
     *                                CAT. TRATADO ACUERDO
     ****************************************************************************************/

    /** Prefijo de las URLs del catalogo de tratado acuerdo. */
    public static final String TRATADO_ACUERDO_BASE = "/tratado-acuerdo";

    /** URL para listar tratados acuerdo paginados. */
    public static final String TRATADO_ACUERDO_LISTAR = TRATADO_ACUERDO_BASE;

    /** URL para crear un nuevo tratado acuerdo. */
    public static final String TRATADO_ACUERDO_CREAR = TRATADO_ACUERDO_BASE;

    /** URL para buscar un tratado acuerdo por identificador. */
    public static final String TRATADO_ACUERDO_BUSCAR = TRATADO_ACUERDO_BASE + "/{idTratadoAcuerdo}";

    /** URL para actualizar un tratado acuerdo por identificador. */
    public static final String TRATADO_ACUERDO_ACTUALIZAR = TRATADO_ACUERDO_BASE + "/{idTratadoAcuerdo}";

    /****************************************************************************************
     *                         CAT. IDENTIFICADOR PREVALIDADOR
     ****************************************************************************************/

    /** Prefijo de las URLs del catalogo de identificador prevalidador. */
    public static final String IDENTIFICADOR_PREVALIDADOR_BASE = "/identificador-prevalidador";

    /** URL para listar identificadores prevalidador paginados. */
    public static final String IDENTIFICADOR_PREVALIDADOR_LISTAR = IDENTIFICADOR_PREVALIDADOR_BASE;

    /** URL para crear un nuevo identificador prevalidador. */
    public static final String IDENTIFICADOR_PREVALIDADOR_CREAR = IDENTIFICADOR_PREVALIDADOR_BASE;

    /** URL para buscar un identificador prevalidador por identificador. */
    public static final String IDENTIFICADOR_PREVALIDADOR_BUSCAR = IDENTIFICADOR_PREVALIDADOR_BASE + "/{idIdentificadorPrevalidador}";

    /** URL para actualizar un identificador prevalidador por identificador. */
    public static final String IDENTIFICADOR_PREVALIDADOR_ACTUALIZAR = IDENTIFICADOR_PREVALIDADOR_BASE + "/{idIdentificadorPrevalidador}";

    /****************************************************************************************
     *                                   CAT. SIT DOM IDC
     ****************************************************************************************/

    /** Prefijo de las URLs del catalogo de situacion domicilio IDC. */
    public static final String SIT_DOM_IDC_BASE = "/sit-dom-idc";

    /** URL para listar registros sit dom idc paginados. */
    public static final String SIT_DOM_IDC_LISTAR = SIT_DOM_IDC_BASE;

    /** URL para crear un nuevo registro sit dom idc. */
    public static final String SIT_DOM_IDC_CREAR = SIT_DOM_IDC_BASE;

    /** URL para buscar un registro sit dom idc por identificador. */
    public static final String SIT_DOM_IDC_BUSCAR = SIT_DOM_IDC_BASE + "/{idSitDomIdc}";

    /** URL para actualizar un registro sit dom idc por identificador. */
    public static final String SIT_DOM_IDC_ACTUALIZAR = SIT_DOM_IDC_BASE + "/{idSitDomIdc}";

    /****************************************************************************************
     *                                  CAT. LEYENDA TEXTO
     ****************************************************************************************/

    /** Prefijo de las URLs del catalogo de leyenda texto. */
    public static final String LEYENDA_TEXTO_BASE = "/leyenda-texto";

    /** URL para listar leyendas texto paginadas. */
    public static final String LEYENDA_TEXTO_LISTAR = LEYENDA_TEXTO_BASE;

    /** URL para crear una nueva leyenda texto. */
    public static final String LEYENDA_TEXTO_CREAR = LEYENDA_TEXTO_BASE;

    /** URL para buscar una leyenda texto por identificador. */
    public static final String LEYENDA_TEXTO_BUSCAR = LEYENDA_TEXTO_BASE + "/{idLeyendaTexto}";

    /** URL para actualizar una leyenda texto por identificador. */
    public static final String LEYENDA_TEXTO_ACTUALIZAR = LEYENDA_TEXTO_BASE + "/{idLeyendaTexto}";

    /****************************************************************************************
     *                                CAT. ESQUEMA REGLA 8
     ****************************************************************************************/

    /** Prefijo de las URLs del catalogo de esquema regla 8. */
    public static final String ESQUEMA_REGLA8_BASE = "/esquema-regla8";

    /** URL para listar esquemas regla 8 paginados. */
    public static final String ESQUEMA_REGLA8_LISTAR = ESQUEMA_REGLA8_BASE;

    /** URL para crear un nuevo esquema regla 8. */
    public static final String ESQUEMA_REGLA8_CREAR = ESQUEMA_REGLA8_BASE;

    /** URL para buscar un esquema regla 8 por identificador. */
    public static final String ESQUEMA_REGLA8_BUSCAR = ESQUEMA_REGLA8_BASE + "/{idEsquemaRegla8}";

    /** URL para actualizar un esquema regla 8 por identificador. */
    public static final String ESQUEMA_REGLA8_ACTUALIZAR = ESQUEMA_REGLA8_BASE + "/{idEsquemaRegla8}";

    /****************************************************************************************
     *                                   CAT. CATALOGO H
     ****************************************************************************************/

    /** Prefijo de las URLs del catalogo H. */
    public static final String CATALOGO_H_BASE = "/catalogo-h";

    /** URL para listar registros catalogo H paginados. */
    public static final String CATALOGO_H_LISTAR = CATALOGO_H_BASE;

    /** URL para crear un nuevo registro catalogo H. */
    public static final String CATALOGO_H_CREAR = CATALOGO_H_BASE;

    /** URL para buscar un registro catalogo H por clave. */
    public static final String CATALOGO_H_BUSCAR = CATALOGO_H_BASE + "/{cveCatalogoH}";

    /** URL para actualizar un registro catalogo H por clave. */
    public static final String CATALOGO_H_ACTUALIZAR = CATALOGO_H_BASE + "/{cveCatalogoH}";

    /****************************************************************************************
     *                              CAT. TRATAMIENTO ESPECIAL
     ****************************************************************************************/

    /** Prefijo de las URLs del catalogo de tratamiento especial. */
    public static final String TRATAMIENTO_ESPECIAL_BASE = "/tratamiento-especial";

    /** URL para listar tratamientos especiales paginados. */
    public static final String TRATAMIENTO_ESPECIAL_LISTAR = TRATAMIENTO_ESPECIAL_BASE;

    /** URL para crear un nuevo tratamiento especial. */
    public static final String TRATAMIENTO_ESPECIAL_CREAR = TRATAMIENTO_ESPECIAL_BASE;

    /** URL para buscar un tratamiento especial por identificador. */
    public static final String TRATAMIENTO_ESPECIAL_BUSCAR = TRATAMIENTO_ESPECIAL_BASE + "/{idTratamientoEspecial}";

    /** URL para actualizar un tratamiento especial por identificador. */
    public static final String TRATAMIENTO_ESPECIAL_ACTUALIZAR = TRATAMIENTO_ESPECIAL_BASE + "/{idTratamientoEspecial}";

    /****************************************************************************************
     *                                  CAT. CONDICION USO
     ****************************************************************************************/

    /** Prefijo de las URLs del catalogo de condicion de uso. */
    public static final String CONDICION_USO_BASE = "/condicion-uso";

    /** URL para listar condiciones de uso paginadas. */
    public static final String CONDICION_USO_LISTAR = CONDICION_USO_BASE;

    /** URL para crear una nueva condicion de uso. */
    public static final String CONDICION_USO_CREAR = CONDICION_USO_BASE;

    /** URL para buscar una condicion de uso por identificador. */
    public static final String CONDICION_USO_BUSCAR = CONDICION_USO_BASE + "/{idCondicionUso}";

    /** URL para actualizar una condicion de uso por identificador. */
    public static final String CONDICION_USO_ACTUALIZAR = CONDICION_USO_BASE + "/{idCondicionUso}";

    /****************************************************************************************
     *                                      CAT. CAS
     ****************************************************************************************/

    /** Prefijo de las URLs del catalogo CAS. */
    public static final String CAS_BASE = "/cas";

    /** URL para listar registros CAS paginados. */
    public static final String CAS_LISTAR = CAS_BASE;

    /** URL para crear un nuevo registro CAS. */
    public static final String CAS_CREAR = CAS_BASE;

    /** URL para buscar un registro CAS por identificador. */
    public static final String CAS_BUSCAR = CAS_BASE + "/{idCas}";

    /** URL para actualizar un registro CAS por identificador. */
    public static final String CAS_ACTUALIZAR = CAS_BASE + "/{idCas}";

    /****************************************************************************************
     *                              CAT. TIPO EMPRESA RECIF
     ****************************************************************************************/

    /** Prefijo de las URLs del catalogo de tipo empresa RECIF. */
    public static final String TIPO_EMPRESA_RECIF_BASE = "/tipo-empresa-recif";

    /** URL para listar tipos empresa RECIF paginados. */
    public static final String TIPO_EMPRESA_RECIF_LISTAR = TIPO_EMPRESA_RECIF_BASE;

    /** URL para crear un nuevo tipo empresa RECIF. */
    public static final String TIPO_EMPRESA_RECIF_CREAR = TIPO_EMPRESA_RECIF_BASE;

    /** URL para buscar un tipo empresa RECIF por clave. */
    public static final String TIPO_EMPRESA_RECIF_BUSCAR = TIPO_EMPRESA_RECIF_BASE + "/{cveTipoEmpresaRecif}";

    /** URL para actualizar un tipo empresa RECIF por clave. */
    public static final String TIPO_EMPRESA_RECIF_ACTUALIZAR = TIPO_EMPRESA_RECIF_BASE + "/{cveTipoEmpresaRecif}";

    /****************************************************************************************
     *                                   CAT. DELEG MUN
     ****************************************************************************************/

    /** Prefijo de las URLs del catalogo de delegacion municipio. */
    public static final String DELEG_MUN_BASE = "/deleg-mun";

    /** URL para listar delegaciones municipio paginadas. */
    public static final String DELEG_MUN_LISTAR = DELEG_MUN_BASE;

    /** URL para crear una nueva delegacion municipio. */
    public static final String DELEG_MUN_CREAR = DELEG_MUN_BASE;

    /** URL para buscar una delegacion municipio por clave. */
    public static final String DELEG_MUN_BUSCAR = DELEG_MUN_BASE + "/{cveDelegMun}";

    /** URL para actualizar una delegacion municipio por clave. */
    public static final String DELEG_MUN_ACTUALIZAR = DELEG_MUN_BASE + "/{cveDelegMun}";

    /****************************************************************************************
     *                                   CAT. LOCALIDAD
     ****************************************************************************************/

    /** Prefijo de las URLs del catalogo de localidades. */
    public static final String LOCALIDAD_BASE = "/localidad";

    /** URL para listar localidades paginadas. */
    public static final String LOCALIDAD_LISTAR = LOCALIDAD_BASE;

    /** URL para crear una nueva localidad. */
    public static final String LOCALIDAD_CREAR = LOCALIDAD_BASE;

    /** URL para buscar una localidad por clave. */
    public static final String LOCALIDAD_BUSCAR = LOCALIDAD_BASE + "/{cveLocalidad}";

    /** URL para actualizar una localidad por clave. */
    public static final String LOCALIDAD_ACTUALIZAR = LOCALIDAD_BASE + "/{cveLocalidad}";

    /****************************************************************************************
     *                                    CAT. COLONIA
     ****************************************************************************************/

    /** Prefijo de las URLs del catalogo de colonias. */
    public static final String COLONIA_BASE = "/colonia";

    /** URL para listar colonias paginadas. */
    public static final String COLONIA_LISTAR = COLONIA_BASE;

    /** URL para crear una nueva colonia. */
    public static final String COLONIA_CREAR = COLONIA_BASE;

    /** URL para buscar una colonia por clave. */
    public static final String COLONIA_BUSCAR = COLONIA_BASE + "/{cveColonia}";

    /** URL para actualizar una colonia por clave. */
    public static final String COLONIA_ACTUALIZAR = COLONIA_BASE + "/{cveColonia}";

    /****************************************************************************************
     *                                   CAT. SUPLENCIA
     ****************************************************************************************/

    /** Prefijo de las URLs del catalogo de suplencias. */
    public static final String SUPLENCIA_BASE = "/suplencia";

    /** URL para listar suplencias paginadas. */
    public static final String SUPLENCIA_LISTAR = SUPLENCIA_BASE;

    /** URL para crear una nueva suplencia. */
    public static final String SUPLENCIA_CREAR = SUPLENCIA_BASE;

    /** URL para buscar una suplencia por identificador. */
    public static final String SUPLENCIA_BUSCAR = SUPLENCIA_BASE + "/{idSuplencia}";

    /** URL para actualizar una suplencia por identificador. */
    public static final String SUPLENCIA_ACTUALIZAR = SUPLENCIA_BASE + "/{idSuplencia}";

    /****************************************************************************************
     *                                  CAT. EQUIV MONEDA
     ****************************************************************************************/

    /** Prefijo de las URLs del catalogo de equivalencia de moneda. */
    public static final String EQUIV_MONEDA_BASE = "/equiv-moneda";

    /** URL para listar equivalencias de moneda paginadas. */
    public static final String EQUIV_MONEDA_LISTAR = EQUIV_MONEDA_BASE;

    /** URL para crear una nueva equivalencia de moneda. */
    public static final String EQUIV_MONEDA_CREAR = EQUIV_MONEDA_BASE;

    /** URL para buscar una equivalencia de moneda por identificador. */
    public static final String EQUIV_MONEDA_BUSCAR = EQUIV_MONEDA_BASE + "/{idEquivMoneda}";

    /** URL para actualizar una equivalencia de moneda por identificador. */
    public static final String EQUIV_MONEDA_ACTUALIZAR = EQUIV_MONEDA_BASE + "/{idEquivMoneda}";

    /****************************************************************************************
     *                                   CAT. CATALOGO D
     ****************************************************************************************/

    /** Prefijo de las URLs del catalogo D. */
    public static final String CATALOGO_D_BASE = "/catalogo-d";

    /** URL para listar registros catalogo D paginados. */
    public static final String CATALOGO_D_LISTAR = CATALOGO_D_BASE;

    /** URL para crear un nuevo registro catalogo D. */
    public static final String CATALOGO_D_CREAR = CATALOGO_D_BASE;

    /** URL para buscar un registro catalogo D por clave. */
    public static final String CATALOGO_D_BUSCAR = CATALOGO_D_BASE + "/{cveCatalogo}";

    /** URL para actualizar un registro catalogo D por clave. */
    public static final String CATALOGO_D_ACTUALIZAR = CATALOGO_D_BASE + "/{cveCatalogo}";

    /****************************************************************************************
     *                                CAT. NORMAL OFICIAL
     ****************************************************************************************/

    /** Prefijo de las URLs del catalogo de norma oficial. */
    public static final String NORMAL_OFICIAL_BASE = "/normal-oficial";

    /** URL para listar normas oficiales paginadas. */
    public static final String NORMAL_OFICIAL_LISTAR = NORMAL_OFICIAL_BASE;

    /** URL para crear una nueva norma oficial. */
    public static final String NORMAL_OFICIAL_CREAR = NORMAL_OFICIAL_BASE;

    /** URL para buscar una norma oficial por identificador. */
    public static final String NORMAL_OFICIAL_BUSCAR = NORMAL_OFICIAL_BASE + "/{idNormalOficial}";

    /** URL para actualizar una norma oficial por identificador. */
    public static final String NORMAL_OFICIAL_ACTUALIZAR = NORMAL_OFICIAL_BASE + "/{idNormalOficial}";

    /****************************************************************************************
     *                               CAT. CATEGORIA TEXTIL
     ****************************************************************************************/

    /** Prefijo de las URLs del catalogo de categoria textil. */
    public static final String CATEGORIA_TEXTIL_BASE = "/categoria-textil";

    /** URL para listar categorias textil paginadas. */
    public static final String CATEGORIA_TEXTIL_LISTAR = CATEGORIA_TEXTIL_BASE;

    /** URL para crear una nueva categoria textil. */
    public static final String CATEGORIA_TEXTIL_CREAR = CATEGORIA_TEXTIL_BASE;

    /** URL para buscar una categoria textil por identificador. */
    public static final String CATEGORIA_TEXTIL_BUSCAR = CATEGORIA_TEXTIL_BASE + "/{idCategoriaTextil}";

    /** URL para actualizar una categoria textil por identificador. */
    public static final String CATEGORIA_TEXTIL_ACTUALIZAR = CATEGORIA_TEXTIL_BASE + "/{idCategoriaTextil}";

    /****************************************************************************************
     *                              CAT. RECINTO FISCALIZADO
     ****************************************************************************************/

    /** Prefijo de las URLs del catalogo de recinto fiscalizado. */
    public static final String RECINTO_FISCALIZADO_BASE = "/recinto-fiscalizado";

    /** URL para listar recintos fiscalizados paginados. */
    public static final String RECINTO_FISCALIZADO_LISTAR = RECINTO_FISCALIZADO_BASE;

    /** URL para crear un nuevo recinto fiscalizado. */
    public static final String RECINTO_FISCALIZADO_CREAR = RECINTO_FISCALIZADO_BASE;

    /** URL para buscar un recinto fiscalizado por identificador. */
    public static final String RECINTO_FISCALIZADO_BUSCAR = RECINTO_FISCALIZADO_BASE + "/{idRecintoFiscalizado}";

    /** URL para actualizar un recinto fiscalizado por identificador. */
    public static final String RECINTO_FISCALIZADO_ACTUALIZAR = RECINTO_FISCALIZADO_BASE + "/{idRecintoFiscalizado}";

    /****************************************************************************************
     *                                CAT. VIDA SILVESTRE
     ****************************************************************************************/

    /** Prefijo de las URLs del catalogo de vida silvestre. */
    public static final String VIDA_SILVESTRE_BASE = "/vida-silvestre";

    /** URL para listar registros vida silvestre paginados. */
    public static final String VIDA_SILVESTRE_LISTAR = VIDA_SILVESTRE_BASE;

    /** URL para crear un nuevo registro vida silvestre. */
    public static final String VIDA_SILVESTRE_CREAR = VIDA_SILVESTRE_BASE;

    /** URL para buscar un registro vida silvestre por identificador. */
    public static final String VIDA_SILVESTRE_BUSCAR = VIDA_SILVESTRE_BASE + "/{idVidaSilvestre}";

    /** URL para actualizar un registro vida silvestre por identificador. */
    public static final String VIDA_SILVESTRE_ACTUALIZAR = VIDA_SILVESTRE_BASE + "/{idVidaSilvestre}";

    /****************************************************************************************
     *                            CAT. UNIDAD ADMINISTRATIVA
     ****************************************************************************************/

    /** Prefijo de las URLs del catalogo de unidad administrativa. */
    public static final String UNIDAD_ADMINISTRATIVA_BASE = "/unidad-administrativa";

    /** URL para listar unidades administrativas paginadas. */
    public static final String UNIDAD_ADMINISTRATIVA_LISTAR = UNIDAD_ADMINISTRATIVA_BASE;

    /** URL para crear una nueva unidad administrativa. */
    public static final String UNIDAD_ADMINISTRATIVA_CREAR = UNIDAD_ADMINISTRATIVA_BASE;

    /** URL para buscar una unidad administrativa por clave. */
    public static final String UNIDAD_ADMINISTRATIVA_BUSCAR = UNIDAD_ADMINISTRATIVA_BASE + "/{cveUnidadAdministrativa}";

    /** URL para actualizar una unidad administrativa por clave. */
    public static final String UNIDAD_ADMINISTRATIVA_ACTUALIZAR = UNIDAD_ADMINISTRATIVA_BASE + "/{cveUnidadAdministrativa}";

    /****************************************************************************************
     *                                  CAT. TIPO TRAMITE
     ****************************************************************************************/

    /** Prefijo de las URLs del catalogo de tipo de tramite. */
    public static final String TIPO_TRAMITE_BASE = "/tipo-tramite";

    /** URL para listar tipos de tramite paginados. */
    public static final String TIPO_TRAMITE_LISTAR = TIPO_TRAMITE_BASE;

    /** URL para crear un nuevo tipo de tramite. */
    public static final String TIPO_TRAMITE_CREAR = TIPO_TRAMITE_BASE;

    /** URL para buscar un tipo de tramite por identificador. */
    public static final String TIPO_TRAMITE_BUSCAR = TIPO_TRAMITE_BASE + "/{idTipoTramite}";

    /** URL para actualizar un tipo de tramite por identificador. */
    public static final String TIPO_TRAMITE_ACTUALIZAR = TIPO_TRAMITE_BASE + "/{idTipoTramite}";

    /****************************************************************************************
     *                                CAT. FRACCION ALADI
     ****************************************************************************************/

    /** Prefijo de las URLs del catalogo de fraccion ALADI. */
    public static final String FRACCION_ALADI_BASE = "/fraccion-aladi";

    /** URL para listar fracciones ALADI paginadas. */
    public static final String FRACCION_ALADI_LISTAR = FRACCION_ALADI_BASE;

    /** URL para crear una nueva fraccion ALADI. */
    public static final String FRACCION_ALADI_CREAR = FRACCION_ALADI_BASE;

    /** URL para buscar una fraccion ALADI por identificador. */
    public static final String FRACCION_ALADI_BUSCAR = FRACCION_ALADI_BASE + "/{idFraccionAladi}";

    /** URL para actualizar una fraccion ALADI por identificador. */
    public static final String FRACCION_ALADI_ACTUALIZAR = FRACCION_ALADI_BASE + "/{idFraccionAladi}";

    /****************************************************************************************
     *                                CAT. FUNDAMENTO TTRA
     ****************************************************************************************/

    /** Prefijo de las URLs del catalogo de fundamento ttra. */
    public static final String FUNDAMENTO_TTRA_BASE = "/fundamento-ttra";

    /** URL para listar fundamentos ttra paginados. */
    public static final String FUNDAMENTO_TTRA_LISTAR = FUNDAMENTO_TTRA_BASE;

    /** URL para crear un nuevo fundamento ttra. */
    public static final String FUNDAMENTO_TTRA_CREAR = FUNDAMENTO_TTRA_BASE;

    /** URL para buscar un fundamento ttra por identificador. */
    public static final String FUNDAMENTO_TTRA_BUSCAR = FUNDAMENTO_TTRA_BASE + "/{idFundamentoTtra}";

    /** URL para actualizar un fundamento ttra por identificador. */
    public static final String FUNDAMENTO_TTRA_ACTUALIZAR = FUNDAMENTO_TTRA_BASE + "/{idFundamentoTtra}";

    /****************************************************************************************
     *                          CAT. CLASIF TOXICOLOGICA TTRA
     ****************************************************************************************/

    /** Prefijo de las URLs del catalogo de clasificacion toxicologica ttra. */
    public static final String CLASIF_TOXICOLOGICA_TTRA_BASE = "/clasif-toxicologica-ttra";

    /** URL para listar clasificaciones toxicologicas ttra paginadas. */
    public static final String CLASIF_TOXICOLOGICA_TTRA_LISTAR = CLASIF_TOXICOLOGICA_TTRA_BASE;

    /** URL para crear una nueva clasificacion toxicologica ttra. */
    public static final String CLASIF_TOXICOLOGICA_TTRA_CREAR = CLASIF_TOXICOLOGICA_TTRA_BASE;

    /** URL para buscar una clasificacion toxicologica ttra por identificador. */
    public static final String CLASIF_TOXICOLOGICA_TTRA_BUSCAR = CLASIF_TOXICOLOGICA_TTRA_BASE + "/{idClasifToxicologicaTtra}";

    /** URL para actualizar una clasificacion toxicologica ttra por identificador. */
    public static final String CLASIF_TOXICOLOGICA_TTRA_ACTUALIZAR = CLASIF_TOXICOLOGICA_TTRA_BASE + "/{idClasifToxicologicaTtra}";

    /****************************************************************************************
     *                                  CAT. SECCION TTRA
     ****************************************************************************************/

    /** Prefijo de las URLs del catalogo de seccion ttra. */
    public static final String SECCION_TTRA_BASE = "/seccion-ttra";

    /** URL para listar secciones ttra paginadas. */
    public static final String SECCION_TTRA_LISTAR = SECCION_TTRA_BASE;

    /** URL para crear una nueva seccion ttra. */
    public static final String SECCION_TTRA_CREAR = SECCION_TTRA_BASE;

    /** URL para buscar una seccion ttra por identificador. */
    public static final String SECCION_TTRA_BUSCAR = SECCION_TTRA_BASE + "/{idSeccionTtra}";

    /** URL para actualizar una seccion ttra por identificador. */
    public static final String SECCION_TTRA_ACTUALIZAR = SECCION_TTRA_BASE + "/{idSeccionTtra}";

    /****************************************************************************************
     *                             CAT. MEDIO TRANSPORTE TTRA
     ****************************************************************************************/

    /** Prefijo de las URLs del catalogo de medio transporte ttra. */
    public static final String MEDIO_TRANSPORTE_TTRA_BASE = "/medio-transporte-ttra";

    /** URL para listar medios de transporte ttra paginados. */
    public static final String MEDIO_TRANSPORTE_TTRA_LISTAR = MEDIO_TRANSPORTE_TTRA_BASE;

    /** URL para crear un nuevo medio de transporte ttra. */
    public static final String MEDIO_TRANSPORTE_TTRA_CREAR = MEDIO_TRANSPORTE_TTRA_BASE;

    /** URL para buscar un medio de transporte ttra por identificador. */
    public static final String MEDIO_TRANSPORTE_TTRA_BUSCAR = MEDIO_TRANSPORTE_TTRA_BASE + "/{idMedioTransporteTtra}";

    /** URL para actualizar un medio de transporte ttra por identificador. */
    public static final String MEDIO_TRANSPORTE_TTRA_ACTUALIZAR = MEDIO_TRANSPORTE_TTRA_BASE + "/{idMedioTransporteTtra}";

    /****************************************************************************************
     *                              CAT. TIPO PRODUCTO TTRA
     ****************************************************************************************/

    /** Prefijo de las URLs del catalogo de tipo producto ttra. */
    public static final String TIPO_PRODUCTO_TTRA_BASE = "/tipo-producto-ttra";

    /** URL para listar tipos de producto ttra paginados. */
    public static final String TIPO_PRODUCTO_TTRA_LISTAR = TIPO_PRODUCTO_TTRA_BASE;

    /** URL para crear un nuevo tipo de producto ttra. */
    public static final String TIPO_PRODUCTO_TTRA_CREAR = TIPO_PRODUCTO_TTRA_BASE;

    /** URL para buscar un tipo de producto ttra por identificador. */
    public static final String TIPO_PRODUCTO_TTRA_BUSCAR = TIPO_PRODUCTO_TTRA_BASE + "/{idTipoProductoTtra}";

    /** URL para actualizar un tipo de producto ttra por identificador. */
    public static final String TIPO_PRODUCTO_TTRA_ACTUALIZAR = TIPO_PRODUCTO_TTRA_BASE + "/{idTipoProductoTtra}";

    /****************************************************************************************
     *                                 CAT. REGIMEN TTRA
     ****************************************************************************************/

    /** Prefijo de las URLs del catalogo de regimen ttra. */
    public static final String REGIMEN_TTRA_BASE = "/regimen-ttra";

    /** URL para listar regimenes ttra paginados. */
    public static final String REGIMEN_TTRA_LISTAR = REGIMEN_TTRA_BASE;

    /** URL para crear un nuevo regimen ttra. */
    public static final String REGIMEN_TTRA_CREAR = REGIMEN_TTRA_BASE;

    /** URL para buscar un regimen ttra por identificador. */
    public static final String REGIMEN_TTRA_BUSCAR = REGIMEN_TTRA_BASE + "/{idRegimenTtra}";

    /** URL para actualizar un regimen ttra por identificador. */
    public static final String REGIMEN_TTRA_ACTUALIZAR = REGIMEN_TTRA_BASE + "/{idRegimenTtra}";

    /****************************************************************************************
     *                                  CAT. ADUANA TTRA
     ****************************************************************************************/

    /** Prefijo de las URLs del catalogo de aduana ttra. */
    public static final String ADUANA_TTRA_BASE = "/aduana-ttra";

    /** URL para listar aduanas ttra paginadas. */
    public static final String ADUANA_TTRA_LISTAR = ADUANA_TTRA_BASE;

    /** URL para crear una nueva aduana ttra. */
    public static final String ADUANA_TTRA_CREAR = ADUANA_TTRA_BASE;

    /** URL para buscar una aduana ttra por identificador. */
    public static final String ADUANA_TTRA_BUSCAR = ADUANA_TTRA_BASE + "/{idAduanaTtra}";

    /** URL para actualizar una aduana ttra por identificador. */
    public static final String ADUANA_TTRA_ACTUALIZAR = ADUANA_TTRA_BASE + "/{idAduanaTtra}";

    /****************************************************************************************
     *                                 CAT. EMPRESA RECIF
     ****************************************************************************************/

    /** Prefijo de las URLs del catalogo de empresa RECIF. */
    public static final String EMPRESA_RECIF_BASE = "/empresa-recif";

    /** URL para listar empresas RECIF paginadas. */
    public static final String EMPRESA_RECIF_LISTAR = EMPRESA_RECIF_BASE;

    /** URL para crear una nueva empresa RECIF. */
    public static final String EMPRESA_RECIF_CREAR = EMPRESA_RECIF_BASE;

    /** URL para buscar una empresa RECIF por clave. */
    public static final String EMPRESA_RECIF_BUSCAR = EMPRESA_RECIF_BASE + "/{recif}";

    /** URL para actualizar una empresa RECIF por clave. */
    public static final String EMPRESA_RECIF_ACTUALIZAR = EMPRESA_RECIF_BASE + "/{recif}";

    /****************************************************************************************
     *                               CAT. FRACCION HTS USA
     ****************************************************************************************/

    /** Prefijo de las URLs del catalogo de fraccion HTS USA. */
    public static final String FRACCION_HTS_USA_BASE = "/fraccion-hts-usa";

    /** URL para listar fracciones HTS USA paginadas. */
    public static final String FRACCION_HTS_USA_LISTAR = FRACCION_HTS_USA_BASE;

    /** URL para crear una nueva fraccion HTS USA. */
    public static final String FRACCION_HTS_USA_CREAR = FRACCION_HTS_USA_BASE;

    /** URL para buscar una fraccion HTS USA por identificador. */
    public static final String FRACCION_HTS_USA_BUSCAR = FRACCION_HTS_USA_BASE + "/{idFraccionHtsUsa}";

    /** URL para actualizar una fraccion HTS USA por identificador. */
    public static final String FRACCION_HTS_USA_ACTUALIZAR = FRACCION_HTS_USA_BASE + "/{idFraccionHtsUsa}";

    /****************************************************************************************
     *                                CAT. APROB CERT SE
     ****************************************************************************************/

    /** Prefijo de las URLs del catalogo de aprobacion certificado SE. */
    public static final String APROB_CERT_SE_BASE = "/aprob-cert-se";

    /** URL para listar aprobaciones certificado SE paginadas. */
    public static final String APROB_CERT_SE_LISTAR = APROB_CERT_SE_BASE;

    /** URL para crear una nueva aprobacion certificado SE. */
    public static final String APROB_CERT_SE_CREAR = APROB_CERT_SE_BASE;

    /** URL para buscar una aprobacion certificado SE por identificador. */
    public static final String APROB_CERT_SE_BUSCAR = APROB_CERT_SE_BASE + "/{idAprobCertSe}";

    /** URL para actualizar una aprobacion certificado SE por identificador. */
    public static final String APROB_CERT_SE_ACTUALIZAR = APROB_CERT_SE_BASE + "/{idAprobCertSe}";

    /****************************************************************************************
     *                           CAT. PAIS RESTRICCION TTRA
     ****************************************************************************************/

    /** Prefijo de las URLs del catalogo de pais restriccion ttra. */
    public static final String PAIS_RESTRICCION_TTRA_BASE = "/pais-restriccion-ttra";

    /** URL para listar paises restriccion ttra paginados. */
    public static final String PAIS_RESTRICCION_TTRA_LISTAR = PAIS_RESTRICCION_TTRA_BASE;

    /** URL para crear un nuevo pais restriccion ttra. */
    public static final String PAIS_RESTRICCION_TTRA_CREAR = PAIS_RESTRICCION_TTRA_BASE;

    /** URL para buscar un pais restriccion ttra por identificador. */
    public static final String PAIS_RESTRICCION_TTRA_BUSCAR = PAIS_RESTRICCION_TTRA_BASE + "/{idPaisRestriccionTtra}";

    /** URL para actualizar un pais restriccion ttra por identificador. */
    public static final String PAIS_RESTRICCION_TTRA_ACTUALIZAR = PAIS_RESTRICCION_TTRA_BASE + "/{idPaisRestriccionTtra}";

    /****************************************************************************************
     *                             CAT. USO MERCANCIA TTRA
     ****************************************************************************************/

    /** Prefijo de las URLs del catalogo de uso mercancia ttra. */
    public static final String USO_MERCANCIA_TTRA_BASE = "/uso-mercancia-ttra";

    /** URL para listar usos de mercancia ttra paginados. */
    public static final String USO_MERCANCIA_TTRA_LISTAR = USO_MERCANCIA_TTRA_BASE;

    /** URL para crear un nuevo uso de mercancia ttra. */
    public static final String USO_MERCANCIA_TTRA_CREAR = USO_MERCANCIA_TTRA_BASE;

    /** URL para buscar un uso de mercancia ttra por identificador. */
    public static final String USO_MERCANCIA_TTRA_BUSCAR = USO_MERCANCIA_TTRA_BASE + "/{idUsoMercanciaTtra}";

    /** URL para actualizar un uso de mercancia ttra por identificador. */
    public static final String USO_MERCANCIA_TTRA_ACTUALIZAR = USO_MERCANCIA_TTRA_BASE + "/{idUsoMercanciaTtra}";

    /****************************************************************************************
     *                          CAT. USO ESPEC MERCANCIA TTRA
     ****************************************************************************************/

    /** Prefijo de las URLs del catalogo de uso especial de mercancia ttra. */
    public static final String USO_ESPEC_MERCANCIA_TTRA_BASE = "/uso-espec-mercancia-ttra";

    /** URL para listar usos especiales de mercancia ttra paginados. */
    public static final String USO_ESPEC_MERCANCIA_TTRA_LISTAR = USO_ESPEC_MERCANCIA_TTRA_BASE;

    /** URL para crear un nuevo uso especial de mercancia ttra. */
    public static final String USO_ESPEC_MERCANCIA_TTRA_CREAR = USO_ESPEC_MERCANCIA_TTRA_BASE;

    /** URL para buscar un uso especial de mercancia ttra por identificador. */
    public static final String USO_ESPEC_MERCANCIA_TTRA_BUSCAR = USO_ESPEC_MERCANCIA_TTRA_BASE + "/{idUsoEspecMercanciaTtra}";

    /** URL para actualizar un uso especial de mercancia ttra por identificador. */
    public static final String USO_ESPEC_MERCANCIA_TTRA_ACTUALIZAR = USO_ESPEC_MERCANCIA_TTRA_BASE + "/{idUsoEspecMercanciaTtra}";

    /****************************************************************************************
     *                             CAT. UNIDAD MEDIDA TTRA
     ****************************************************************************************/

    /** Prefijo de las URLs del catalogo de unidad de medida ttra. */
    public static final String UNIDAD_MEDIDA_TTRA_BASE = "/unidad-medida-ttra";

    /** URL para listar unidades de medida ttra paginadas. */
    public static final String UNIDAD_MEDIDA_TTRA_LISTAR = UNIDAD_MEDIDA_TTRA_BASE;

    /** URL para crear una nueva unidad de medida ttra. */
    public static final String UNIDAD_MEDIDA_TTRA_CREAR = UNIDAD_MEDIDA_TTRA_BASE;

    /** URL para buscar una unidad de medida ttra por identificador. */
    public static final String UNIDAD_MEDIDA_TTRA_BUSCAR = UNIDAD_MEDIDA_TTRA_BASE + "/{idUnidadMedidaTtra}";

    /** URL para actualizar una unidad de medida ttra por identificador. */
    public static final String UNIDAD_MEDIDA_TTRA_ACTUALIZAR = UNIDAD_MEDIDA_TTRA_BASE + "/{idUnidadMedidaTtra}";

    /****************************************************************************************
     *                           CAT. CONTROL REFERENCIA
     ****************************************************************************************/

    /** Prefijo de las URLs del catalogo de control referencia. */
    public static final String CONTROL_REFERENCIA_BASE = "/control-referencia";

    /** URL para listar controles de referencia paginados. */
    public static final String CONTROL_REFERENCIA_LISTAR = CONTROL_REFERENCIA_BASE;

    /** URL para crear un nuevo control de referencia. */
    public static final String CONTROL_REFERENCIA_CREAR = CONTROL_REFERENCIA_BASE;

    /** URL para buscar un control de referencia por identificador. */
    public static final String CONTROL_REFERENCIA_BUSCAR = CONTROL_REFERENCIA_BASE + "/{idControlReferencia}";

    /** URL para actualizar un control de referencia por identificador. */
    public static final String CONTROL_REFERENCIA_ACTUALIZAR = CONTROL_REFERENCIA_BASE + "/{idControlReferencia}";

    /****************************************************************************************
     *                             CAT. TIPO CERTIFICADO
     ****************************************************************************************/

    /** Prefijo de las URLs del catalogo de tipo certificado. */
    public static final String TIPO_CERTIFICADO_BASE = "/tipo-certificado";

    /** URL para listar tipos de certificado paginados. */
    public static final String TIPO_CERTIFICADO_LISTAR = TIPO_CERTIFICADO_BASE;

    /** URL para crear un nuevo tipo de certificado. */
    public static final String TIPO_CERTIFICADO_CREAR = TIPO_CERTIFICADO_BASE;

    /** URL para buscar un tipo de certificado por identificador. */
    public static final String TIPO_CERTIFICADO_BUSCAR = TIPO_CERTIFICADO_BASE + "/{idTipoCertificado}";

    /** URL para actualizar un tipo de certificado por identificador. */
    public static final String TIPO_CERTIFICADO_ACTUALIZAR = TIPO_CERTIFICADO_BASE + "/{idTipoCertificado}";

    /****************************************************************************************
     *                           CAT. JUSTIFICACION TTRA
     ****************************************************************************************/

    /** Prefijo de las URLs del catalogo de justificacion ttra. */
    public static final String JUSTIFICACION_TTRA_BASE = "/justificacion-ttra";

    /** URL para listar justificaciones ttra paginadas. */
    public static final String JUSTIFICACION_TTRA_LISTAR = JUSTIFICACION_TTRA_BASE;

    /** URL para crear una nueva justificacion ttra. */
    public static final String JUSTIFICACION_TTRA_CREAR = JUSTIFICACION_TTRA_BASE;

    /** URL para buscar una justificacion ttra por identificador. */
    public static final String JUSTIFICACION_TTRA_BUSCAR = JUSTIFICACION_TTRA_BASE + "/{idJustificacionTtra}";

    /** URL para actualizar una justificacion ttra por identificador. */
    public static final String JUSTIFICACION_TTRA_ACTUALIZAR = JUSTIFICACION_TTRA_BASE + "/{idJustificacionTtra}";

    /****************************************************************************************
     *                            CAT. LABORATORIO TTRA
     ****************************************************************************************/

    /** Prefijo de las URLs del catalogo de laboratorio ttra. */
    public static final String LABORATORIO_TTRA_BASE = "/laboratorio-ttra";

    /** URL para listar laboratorios ttra paginados. */
    public static final String LABORATORIO_TTRA_LISTAR = LABORATORIO_TTRA_BASE;

    /** URL para crear un nuevo laboratorio ttra. */
    public static final String LABORATORIO_TTRA_CREAR = LABORATORIO_TTRA_BASE;

    /** URL para buscar un laboratorio ttra por identificador. */
    public static final String LABORATORIO_TTRA_BUSCAR = LABORATORIO_TTRA_BASE + "/{idLaboratorioTtra}";

    /** URL para actualizar un laboratorio ttra por identificador. */
    public static final String LABORATORIO_TTRA_ACTUALIZAR = LABORATORIO_TTRA_BASE + "/{idLaboratorioTtra}";

    /****************************************************************************************
     *                              CAT. MOTIVO TTRA
     ****************************************************************************************/

    /** Prefijo de las URLs del catalogo de motivo ttra. */
    public static final String MOTIVO_TTRA_BASE = "/motivo-ttra";

    /** URL para listar motivos ttra paginados. */
    public static final String MOTIVO_TTRA_LISTAR = MOTIVO_TTRA_BASE;

    /** URL para crear un nuevo motivo ttra. */
    public static final String MOTIVO_TTRA_CREAR = MOTIVO_TTRA_BASE;

    /** URL para buscar un motivo ttra por identificador. */
    public static final String MOTIVO_TTRA_BUSCAR = MOTIVO_TTRA_BASE + "/{idMotivoTtra}";

    /** URL para actualizar un motivo ttra por identificador. */
    public static final String MOTIVO_TTRA_ACTUALIZAR = MOTIVO_TTRA_BASE + "/{idMotivoTtra}";

    /****************************************************************************************
     *                            CAT. OBSERVACION TTRA
     ****************************************************************************************/

    /** Prefijo de las URLs del catalogo de observacion ttra. */
    public static final String OBSERVACION_TTRA_BASE = "/observacion-ttra";

    /** URL para listar observaciones ttra paginadas. */
    public static final String OBSERVACION_TTRA_LISTAR = OBSERVACION_TTRA_BASE;

    /** URL para crear una nueva observacion ttra. */
    public static final String OBSERVACION_TTRA_CREAR = OBSERVACION_TTRA_BASE;

    /** URL para buscar una observacion ttra por identificador. */
    public static final String OBSERVACION_TTRA_BUSCAR = OBSERVACION_TTRA_BASE + "/{idObservacionTtra}";

    /** URL para actualizar una observacion ttra por identificador. */
    public static final String OBSERVACION_TTRA_ACTUALIZAR = OBSERVACION_TTRA_BASE + "/{idObservacionTtra}";

    /****************************************************************************************
     *                            CAT. RESTRICCION TTRA
     ****************************************************************************************/

    /** Prefijo de las URLs del catalogo de restriccion ttra. */
    public static final String RESTRICCION_TTRA_BASE = "/restriccion-ttra";

    /** URL para listar restricciones ttra paginadas. */
    public static final String RESTRICCION_TTRA_LISTAR = RESTRICCION_TTRA_BASE;

    /** URL para crear una nueva restriccion ttra. */
    public static final String RESTRICCION_TTRA_CREAR = RESTRICCION_TTRA_BASE;

    /** URL para buscar una restriccion ttra por identificador. */
    public static final String RESTRICCION_TTRA_BUSCAR = RESTRICCION_TTRA_BASE + "/{idRestriccionTtra}";

    /** URL para actualizar una restriccion ttra por identificador. */
    public static final String RESTRICCION_TTRA_ACTUALIZAR = RESTRICCION_TTRA_BASE + "/{idRestriccionTtra}";

    /****************************************************************************************
     *                         CAT. ACTIVIDAD ECONOMICA SAT
     ****************************************************************************************/

    /** Prefijo de las URLs del catalogo de actividad economica SAT. */
    public static final String ACTIVIDAD_ECONOMICA_SAT_BASE = "/actividad-economica-sat";

    /** URL para listar actividades economicas SAT paginadas. */
    public static final String ACTIVIDAD_ECONOMICA_SAT_LISTAR = ACTIVIDAD_ECONOMICA_SAT_BASE;

    /** URL para crear una nueva actividad economica SAT. */
    public static final String ACTIVIDAD_ECONOMICA_SAT_CREAR = ACTIVIDAD_ECONOMICA_SAT_BASE;

    /** URL para buscar una actividad economica SAT por identificador. */
    public static final String ACTIVIDAD_ECONOMICA_SAT_BUSCAR = ACTIVIDAD_ECONOMICA_SAT_BASE + "/{idActividadEconomicaSat}";

    /** URL para actualizar una actividad economica SAT por identificador. */
    public static final String ACTIVIDAD_ECONOMICA_SAT_ACTUALIZAR = ACTIVIDAD_ECONOMICA_SAT_BASE + "/{idActividadEconomicaSat}";

    /****************************************************************************************
     *                            CAT. MONTO EXPORTACION
     ****************************************************************************************/

    public static final String MONTO_EXPORTACION_BASE = "/monto-exportacion";
    public static final String MONTO_EXPORTACION_LISTAR = MONTO_EXPORTACION_BASE;
    public static final String MONTO_EXPORTACION_CREAR = MONTO_EXPORTACION_BASE;
    public static final String MONTO_EXPORTACION_BUSCAR = MONTO_EXPORTACION_BASE + "/{rfcExportador}/{fecMontoExportacion}";
    public static final String MONTO_EXPORTACION_ACTUALIZAR = MONTO_EXPORTACION_BASE + "/{rfcExportador}/{fecMontoExportacion}";

    /****************************************************************************************
     *                            CAT. EQUIVALENCIA AELC
     ****************************************************************************************/

    public static final String EQUIVALENCIA_AELC_BASE = "/equivalencia-aelc";
    public static final String EQUIVALENCIA_AELC_LISTAR = EQUIVALENCIA_AELC_BASE;
    public static final String EQUIVALENCIA_AELC_CREAR = EQUIVALENCIA_AELC_BASE;
    public static final String EQUIVALENCIA_AELC_BUSCAR = EQUIVALENCIA_AELC_BASE + "/{fecIniVigencia}/{cveMoneda}";
    public static final String EQUIVALENCIA_AELC_ACTUALIZAR = EQUIVALENCIA_AELC_BASE + "/{fecIniVigencia}/{cveMoneda}";

    /****************************************************************************************
     *                            CAT. DIA NO LABORABLE
     ****************************************************************************************/

    public static final String DIA_NO_LABORABLE_BASE = "/dia-no-laborable";
    public static final String DIA_NO_LABORABLE_LISTAR = DIA_NO_LABORABLE_BASE;
    public static final String DIA_NO_LABORABLE_CREAR = DIA_NO_LABORABLE_BASE;
    public static final String DIA_NO_LABORABLE_BUSCAR = DIA_NO_LABORABLE_BASE + "/{fecNoLaborable}/{cveCalendario}";
    public static final String DIA_NO_LABORABLE_ACTUALIZAR = DIA_NO_LABORABLE_BASE + "/{fecNoLaborable}/{cveCalendario}";

    /****************************************************************************************
     *                              CAT. CATALOGO D TR
     ****************************************************************************************/

    public static final String CATALOGO_D_TR_BASE = "/catalogo-d-tr";
    public static final String CATALOGO_D_TR_LISTAR = CATALOGO_D_TR_BASE;
    public static final String CATALOGO_D_TR_CREAR = CATALOGO_D_TR_BASE;
    public static final String CATALOGO_D_TR_BUSCAR = CATALOGO_D_TR_BASE + "/{cveCatalogo}/{cveLenguaje}";
    public static final String CATALOGO_D_TR_ACTUALIZAR = CATALOGO_D_TR_BASE + "/{cveCatalogo}/{cveLenguaje}";

    /****************************************************************************************
     *                              CAT. TIPO RFC
     ****************************************************************************************/

    public static final String TIPO_RFC_BASE = "/tipo-rfc";
    public static final String TIPO_RFC_LISTAR = TIPO_RFC_BASE;
    public static final String TIPO_RFC_CREAR = TIPO_RFC_BASE;
    public static final String TIPO_RFC_BUSCAR = TIPO_RFC_BASE + "/{rfc}/{ideTipoRfc}";
    public static final String TIPO_RFC_ACTUALIZAR = TIPO_RFC_BASE + "/{rfc}/{ideTipoRfc}";

    /****************************************************************************************
     *                         CAT. DECLARACION TRAMITE
     ****************************************************************************************/

    public static final String DECLARACION_TRAMITE_BASE = "/declaracion-tramite";
    public static final String DECLARACION_TRAMITE_LISTAR = DECLARACION_TRAMITE_BASE;
    public static final String DECLARACION_TRAMITE_CREAR = DECLARACION_TRAMITE_BASE;
    public static final String DECLARACION_TRAMITE_BUSCAR = DECLARACION_TRAMITE_BASE + "/{cveDeclaracion}/{idTipoTramite}";
    public static final String DECLARACION_TRAMITE_ACTUALIZAR = DECLARACION_TRAMITE_BASE + "/{cveDeclaracion}/{idTipoTramite}";

    /****************************************************************************************
     *                           CAT. DICTAMEN TRAMITE
     ****************************************************************************************/

    public static final String DICTAMEN_TRAMITE_BASE = "/dictamen-tramite";
    public static final String DICTAMEN_TRAMITE_LISTAR = DICTAMEN_TRAMITE_BASE;
    public static final String DICTAMEN_TRAMITE_CREAR = DICTAMEN_TRAMITE_BASE;
    public static final String DICTAMEN_TRAMITE_BUSCAR = DICTAMEN_TRAMITE_BASE + "/{idTipoTramite}/{idTipoDictamen}";
    public static final String DICTAMEN_TRAMITE_ACTUALIZAR = DICTAMEN_TRAMITE_BASE + "/{idTipoTramite}/{idTipoDictamen}";

    /****************************************************************************************
     *                          CAT. DOCUMENTO TRAMITE
     ****************************************************************************************/

    public static final String DOCUMENTO_TRAMITE_BASE = "/documento-tramite";
    public static final String DOCUMENTO_TRAMITE_LISTAR = DOCUMENTO_TRAMITE_BASE;
    public static final String DOCUMENTO_TRAMITE_CREAR = DOCUMENTO_TRAMITE_BASE;
    public static final String DOCUMENTO_TRAMITE_BUSCAR = DOCUMENTO_TRAMITE_BASE + "/{idTipoDoc}/{idTipoTramite}";
    public static final String DOCUMENTO_TRAMITE_ACTUALIZAR = DOCUMENTO_TRAMITE_BASE + "/{idTipoDoc}/{idTipoTramite}";

    /****************************************************************************************
     *                      CAT. PLAZO MAXIMO AUT TRAMITE
     ****************************************************************************************/

    public static final String PLAZO_MAXIMO_AUT_TRAMITE_BASE = "/plazo-maximo-aut-tramite";
    public static final String PLAZO_MAXIMO_AUT_TRAMITE_LISTAR = PLAZO_MAXIMO_AUT_TRAMITE_BASE;
    public static final String PLAZO_MAXIMO_AUT_TRAMITE_CREAR = PLAZO_MAXIMO_AUT_TRAMITE_BASE;
    public static final String PLAZO_MAXIMO_AUT_TRAMITE_BUSCAR = PLAZO_MAXIMO_AUT_TRAMITE_BASE + "/{idTipoTramite}/{fecIniVigencia}";
    public static final String PLAZO_MAXIMO_AUT_TRAMITE_ACTUALIZAR = PLAZO_MAXIMO_AUT_TRAMITE_BASE + "/{idTipoTramite}/{fecIniVigencia}";

    /****************************************************************************************
     *                               CAT. PLAZO TTRA
     ****************************************************************************************/

    public static final String PLAZO_TTRA_BASE = "/plazo-ttra";
    public static final String PLAZO_TTRA_LISTAR = PLAZO_TTRA_BASE;
    public static final String PLAZO_TTRA_CREAR = PLAZO_TTRA_BASE;
    public static final String PLAZO_TTRA_BUSCAR = PLAZO_TTRA_BASE + "/{idTipoTramite}/{idePlazoVigencia}";
    public static final String PLAZO_TTRA_ACTUALIZAR = PLAZO_TTRA_BASE + "/{idTipoTramite}/{idePlazoVigencia}";

    /****************************************************************************************
     *                                 CAT. TARIFA
     ****************************************************************************************/

    public static final String TARIFA_BASE = "/tarifa";
    public static final String TARIFA_LISTAR = TARIFA_BASE;
    public static final String TARIFA_CREAR = TARIFA_BASE;
    public static final String TARIFA_BUSCAR = TARIFA_BASE + "/{idTipoTramite}/{fecIniVigencia}/{ideTipoTarifa}";
    public static final String TARIFA_ACTUALIZAR = TARIFA_BASE + "/{idTipoTramite}/{fecIniVigencia}/{ideTipoTarifa}";

    /****************************************************************************************
     *                        CAT. CLASIFICACION REGIMEN
     ****************************************************************************************/

    public static final String CLASIFICACION_REGIMEN_BASE = "/clasificacion-regimen";
    public static final String CLASIFICACION_REGIMEN_LISTAR = CLASIFICACION_REGIMEN_BASE;
    public static final String CLASIFICACION_REGIMEN_CREAR = CLASIFICACION_REGIMEN_BASE;
    public static final String CLASIFICACION_REGIMEN_BUSCAR = CLASIFICACION_REGIMEN_BASE + "/{cveClasificacionRegimen}/{cveRegimen}";
    public static final String CLASIFICACION_REGIMEN_ACTUALIZAR = CLASIFICACION_REGIMEN_BASE + "/{cveClasificacionRegimen}/{cveRegimen}";

    /****************************************************************************************
     *                           CAT. PARTIDA FRACCION
     ****************************************************************************************/

    public static final String PARTIDA_FRACCION_BASE = "/partida-fraccion";
    public static final String PARTIDA_FRACCION_LISTAR = PARTIDA_FRACCION_BASE;
    public static final String PARTIDA_FRACCION_CREAR = PARTIDA_FRACCION_BASE;
    public static final String PARTIDA_FRACCION_BUSCAR = PARTIDA_FRACCION_BASE + "/{cveCapituloFraccion}/{cvePartidaFraccion}";
    public static final String PARTIDA_FRACCION_ACTUALIZAR = PARTIDA_FRACCION_BASE + "/{cveCapituloFraccion}/{cvePartidaFraccion}";

    /****************************************************************************************
     *                        CAT. PAIS TRATADO ACUERDO
     ****************************************************************************************/

    public static final String PAIS_TRATADO_ACUERDO_BASE = "/pais-tratado-acuerdo";
    public static final String PAIS_TRATADO_ACUERDO_LISTAR = PAIS_TRATADO_ACUERDO_BASE;
    public static final String PAIS_TRATADO_ACUERDO_CREAR = PAIS_TRATADO_ACUERDO_BASE;
    public static final String PAIS_TRATADO_ACUERDO_BUSCAR = PAIS_TRATADO_ACUERDO_BASE + "/{cvePais}/{idTratadoAcuerdo}";
    public static final String PAIS_TRATADO_ACUERDO_ACTUALIZAR = PAIS_TRATADO_ACUERDO_BASE + "/{cvePais}/{idTratadoAcuerdo}";

    /****************************************************************************************
     *                            CAT. TRATADO BLOQUE
     ****************************************************************************************/

    public static final String TRATADO_BLOQUE_BASE = "/tratado-bloque";
    public static final String TRATADO_BLOQUE_LISTAR = TRATADO_BLOQUE_BASE;
    public static final String TRATADO_BLOQUE_CREAR = TRATADO_BLOQUE_BASE;
    public static final String TRATADO_BLOQUE_BUSCAR = TRATADO_BLOQUE_BASE + "/{idTratadoAcuerdo}/{idBloque}";
    public static final String TRATADO_BLOQUE_ACTUALIZAR = TRATADO_BLOQUE_BASE + "/{idTratadoAcuerdo}/{idBloque}";

    /****************************************************************************************
     *                          CAT. TRATADO BLOQUE PAI
     ****************************************************************************************/

    public static final String TRATADO_BLOQUE_PAI_BASE = "/tratado-bloque-pai";
    public static final String TRATADO_BLOQUE_PAI_LISTAR = TRATADO_BLOQUE_PAI_BASE;
    public static final String TRATADO_BLOQUE_PAI_CREAR = TRATADO_BLOQUE_PAI_BASE;
    public static final String TRATADO_BLOQUE_PAI_BUSCAR = TRATADO_BLOQUE_PAI_BASE + "/{cvePais}/{idTratadoAcuerdo}";
    public static final String TRATADO_BLOQUE_PAI_ACTUALIZAR = TRATADO_BLOQUE_PAI_BASE + "/{cvePais}/{idTratadoAcuerdo}";

    /****************************************************************************************
     *                        CAT. UNIDAD ADMIN ADUANA
     ****************************************************************************************/

    public static final String UNIDAD_ADMIN_ADUANA_BASE = "/unidad-admin-aduana";
    public static final String UNIDAD_ADMIN_ADUANA_LISTAR = UNIDAD_ADMIN_ADUANA_BASE;
    public static final String UNIDAD_ADMIN_ADUANA_CREAR = UNIDAD_ADMIN_ADUANA_BASE;
    public static final String UNIDAD_ADMIN_ADUANA_BUSCAR = UNIDAD_ADMIN_ADUANA_BASE + "/{cveUnidadAdministrativa}/{cveAduana}";
    public static final String UNIDAD_ADMIN_ADUANA_ACTUALIZAR = UNIDAD_ADMIN_ADUANA_BASE + "/{cveUnidadAdministrativa}/{cveAduana}";

    /****************************************************************************************
     *                        CAT. UNIDAD ADMIN VECINA
     ****************************************************************************************/

    public static final String UNIDAD_ADMIN_VECINA_BASE = "/unidad-admin-vecina";
    public static final String UNIDAD_ADMIN_VECINA_LISTAR = UNIDAD_ADMIN_VECINA_BASE;
    public static final String UNIDAD_ADMIN_VECINA_CREAR = UNIDAD_ADMIN_VECINA_BASE;
    public static final String UNIDAD_ADMIN_VECINA_BUSCAR = UNIDAD_ADMIN_VECINA_BASE + "/{cveUnidadAdministrativa}/{cveEntidad}";
    public static final String UNIDAD_ADMIN_VECINA_ACTUALIZAR = UNIDAD_ADMIN_VECINA_BASE + "/{cveUnidadAdministrativa}/{cveEntidad}";

    /****************************************************************************************
     *                         CAT. SUBPARTIDA FRACCION
     ****************************************************************************************/

    public static final String SUBPARTIDA_FRACCION_BASE = "/subpartida-fraccion";
    public static final String SUBPARTIDA_FRACCION_LISTAR = SUBPARTIDA_FRACCION_BASE;
    public static final String SUBPARTIDA_FRACCION_CREAR = SUBPARTIDA_FRACCION_BASE;
    public static final String SUBPARTIDA_FRACCION_BUSCAR = SUBPARTIDA_FRACCION_BASE + "/{cveSubpartidaFraccion}/{cveCapituloFraccion}/{cvePartidaFraccion}";
    public static final String SUBPARTIDA_FRACCION_ACTUALIZAR = SUBPARTIDA_FRACCION_BASE + "/{cveSubpartidaFraccion}/{cveCapituloFraccion}/{cvePartidaFraccion}";

    /****************************************************************************************
     *                           CAT. COMBINACION SG
     ****************************************************************************************/

    public static final String COMBINACION_SG_BASE = "/combinacion-sg";
    public static final String COMBINACION_SG_LISTAR = COMBINACION_SG_BASE;
    public static final String COMBINACION_SG_CREAR = COMBINACION_SG_BASE;
    public static final String COMBINACION_SG_BUSCAR = COMBINACION_SG_BASE + "/{idCombinacionSg}";
    public static final String COMBINACION_SG_ACTUALIZAR = COMBINACION_SG_BASE + "/{idCombinacionSg}";

    /****************************************************************************************
     *                          CAT. VIGENCIA SERVICIO
     ****************************************************************************************/

    public static final String VIGENCIA_SERVICIO_BASE = "/vigencia-servicio";
    public static final String VIGENCIA_SERVICIO_LISTAR = VIGENCIA_SERVICIO_BASE;
    public static final String VIGENCIA_SERVICIO_CREAR = VIGENCIA_SERVICIO_BASE;
    public static final String VIGENCIA_SERVICIO_BUSCAR = VIGENCIA_SERVICIO_BASE + "/{idVigenciaServicio}";
    public static final String VIGENCIA_SERVICIO_ACTUALIZAR = VIGENCIA_SERVICIO_BASE + "/{idVigenciaServicio}";

    /****************************************************************************************
     *                          CAT. RESTRIC DESC PROD
     ****************************************************************************************/

    public static final String RESTRIC_DESC_PROD_BASE = "/restric-desc-prod";
    public static final String RESTRIC_DESC_PROD_LISTAR = RESTRIC_DESC_PROD_BASE;
    public static final String RESTRIC_DESC_PROD_CREAR = RESTRIC_DESC_PROD_BASE;
    public static final String RESTRIC_DESC_PROD_BUSCAR = RESTRIC_DESC_PROD_BASE + "/{idRestricDescProd}";
    public static final String RESTRIC_DESC_PROD_ACTUALIZAR = RESTRIC_DESC_PROD_BASE + "/{idRestricDescProd}";

    /****************************************************************************************
     *                        CAT. FRACCION ARANCELARIA
     ****************************************************************************************/

    public static final String FRACCION_ARANCELARIA_BASE = "/fraccion-arancelaria";
    public static final String FRACCION_ARANCELARIA_LISTAR = FRACCION_ARANCELARIA_BASE;
    public static final String FRACCION_ARANCELARIA_CREAR = FRACCION_ARANCELARIA_BASE;
    public static final String FRACCION_ARANCELARIA_BUSCAR = FRACCION_ARANCELARIA_BASE + "/{cveFraccion}";
    public static final String FRACCION_ARANCELARIA_ACTUALIZAR = FRACCION_ARANCELARIA_BASE + "/{cveFraccion}";

    /****************************************************************************************
     *                            CAT. FRACCION TTRA
     ****************************************************************************************/

    public static final String FRACCION_TTRA_BASE = "/fraccion-ttra";
    public static final String FRACCION_TTRA_LISTAR = FRACCION_TTRA_BASE;
    public static final String FRACCION_TTRA_CREAR = FRACCION_TTRA_BASE;
    public static final String FRACCION_TTRA_BUSCAR = FRACCION_TTRA_BASE + "/{idFraccionGob}";
    public static final String FRACCION_TTRA_ACTUALIZAR = FRACCION_TTRA_BASE + "/{idFraccionGob}";

    /****************************************************************************************
     *                        CAT. SUBDIVISION FRACCION
     ****************************************************************************************/

    public static final String SUBDIVISION_FRACCION_BASE = "/subdivision-fraccion";
    public static final String SUBDIVISION_FRACCION_LISTAR = SUBDIVISION_FRACCION_BASE;
    public static final String SUBDIVISION_FRACCION_CREAR = SUBDIVISION_FRACCION_BASE;
    public static final String SUBDIVISION_FRACCION_BUSCAR = SUBDIVISION_FRACCION_BASE + "/{cveSubdivision}";
    public static final String SUBDIVISION_FRACCION_ACTUALIZAR = SUBDIVISION_FRACCION_BASE + "/{cveSubdivision}";

    /****************************************************************************************
     *                      CAT. FRACCION TTRA DESC PROD
     ****************************************************************************************/

    public static final String FRACCION_TTRA_DESC_PROD_BASE = "/fraccion-ttra-desc-prod";
    public static final String FRACCION_TTRA_DESC_PROD_LISTAR = FRACCION_TTRA_DESC_PROD_BASE;
    public static final String FRACCION_TTRA_DESC_PROD_CREAR = FRACCION_TTRA_DESC_PROD_BASE;
    public static final String FRACCION_TTRA_DESC_PROD_BUSCAR = FRACCION_TTRA_DESC_PROD_BASE + "/{idFraccionTtraDescProd}";
    public static final String FRACCION_TTRA_DESC_PROD_ACTUALIZAR = FRACCION_TTRA_DESC_PROD_BASE + "/{idFraccionTtraDescProd}";

    /****************************************************************************************
     *                      CAT. ISOTOPO FRACCION
     ****************************************************************************************/

    public static final String ISOTOPO_FRACCION_BASE = "/isotopo-fraccion";
    public static final String ISOTOPO_FRACCION_LISTAR = ISOTOPO_FRACCION_BASE;
    public static final String ISOTOPO_FRACCION_CREAR = ISOTOPO_FRACCION_BASE;
    public static final String ISOTOPO_FRACCION_BUSCAR = ISOTOPO_FRACCION_BASE + "/{idIsotopo}";
    public static final String ISOTOPO_FRACCION_ACTUALIZAR = ISOTOPO_FRACCION_BASE + "/{idIsotopo}";

    /****************************************************************************************
     *                      CAT. CAS FRACCION TTRA
     ****************************************************************************************/

    public static final String CAS_FRACCION_TTRA_BASE = "/cas-fraccion-ttra";
    public static final String CAS_FRACCION_TTRA_LISTAR = CAS_FRACCION_TTRA_BASE;
    public static final String CAS_FRACCION_TTRA_CREAR = CAS_FRACCION_TTRA_BASE;
    public static final String CAS_FRACCION_TTRA_BUSCAR = CAS_FRACCION_TTRA_BASE + "/{idCasFraccionTtra}";
    public static final String CAS_FRACCION_TTRA_ACTUALIZAR = CAS_FRACCION_TTRA_BASE + "/{idCasFraccionTtra}";

    /****************************************************************************************
     *                      CAT. ARANCEL PROSEC
     ****************************************************************************************/

    public static final String ARANCEL_PROSEC_BASE = "/arancel-prosec";
    public static final String ARANCEL_PROSEC_LISTAR = ARANCEL_PROSEC_BASE;
    public static final String ARANCEL_PROSEC_CREAR = ARANCEL_PROSEC_BASE;

}