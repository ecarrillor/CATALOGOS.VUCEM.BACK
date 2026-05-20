package mx.gob.sat.catalogo.controller.response.tipotramite;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;

/**
 * <b>Class:</b> CatTipoTramiteResponse.java <br>
 * <b>Description:</b>
 * <p>DTO de salida para el catalogo de tipos de tramite.</p>
 *
 * @author Javier Chávez Barrios
 * @email jchavezb@ultrasist.com.mx
 *
 * @created 18 de mayo del 2026
 * @version 1.0
 * @category Response DTO
 */
@Getter
@Builder
public class CatTipoTramiteResponse {

    /** Identificador del tipo de tramite. */
    private Long idTipoTramite;

    /** Clave de servicio. */
    private String cveServicio;

    /** Descripcion del servicio. */
    private String descServicio;

    /** Clave de subservicio. */
    private String cveSubservicio;

    /** Descripcion del subservicio. */
    private String descSubservicio;

    /** Clave de modalidad. */
    private String cveModalidad;

    /** Descripcion de la modalidad. */
    private String descModalidad;

    /** Clave de flujo. */
    private String cveFlujo;

    /** Descripcion del flujo. */
    private String descFlujo;

    /** Nivel de servicio. */
    private Short nivelServicio;

    /** Nombre del servicio Axway. */
    private String nomServAxway;

    /** Nombre del mensaje Axway. */
    private String nomMensajeAxway;

    /** URL de Axway. */
    private String urlAxway;

    /** Fecha de captura. */
    private LocalDate fecCaptura;

    /** Fecha de fin de vigencia. */
    private LocalDate fecFinVigencia;

    /** Nombre del tipo de tramite. */
    private String nombre;

    /** Indicador de replica de informacion. */
    private Boolean blnReplicaInfo;

    /** Indicador de automatico. */
    private Boolean blnAutomatico;

    /** Fecha de inicio de vigencia. */
    private LocalDate fecIniVigencia;

    /** Indicador de activo. */
    private Boolean blnActivo;

    /** Indicador de asignacion. */
    private Boolean blnAsignacion;

    /** Clave de modulo. */
    private Short cveModulo;

    /** Nombre de la dependencia asociada. */
    private String nombreDependencia;
}
