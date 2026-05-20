package mx.gob.sat.catalogo.controller.request.tipotramite;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

/**
 * <b>Class:</b> CatTipoTramiteRequest.java <br>
 * <b>Description:</b>
 * <p>DTO de entrada para el catalogo de tipos de tramite.</p>
 *
 * @author Javier Chávez Barrios
 * @email jchavezb@ultrasist.com.mx
 *
 * @created 18 de mayo del 2026
 * @version 1.0
 * @category Request DTO
 */
@Getter
@Setter
public class CatTipoTramiteRequest {

    /** Clave de servicio. */
    @Size(max = 2)
    private String cveServicio;

    /** Descripcion del servicio. */
    @Size(max = 500)
    private String descServicio;

    /** Clave de subservicio. */
    @Size(max = 5)
    private String cveSubservicio;

    /** Descripcion del subservicio. */
    @Size(max = 500)
    private String descSubservicio;

    /** Clave de modalidad. */
    @Size(max = 11)
    private String cveModalidad;

    /** Descripcion de la modalidad. */
    @Size(max = 500)
    private String descModalidad;

    /** Clave de flujo. */
    @Size(max = 20)
    private String cveFlujo;

    /** Descripcion del flujo. */
    @Size(max = 250)
    private String descFlujo;

    /** Nivel de servicio. */
    private Short nivelServicio;

    /** Nombre del servicio Axway. */
    @Size(max = 120)
    private String nomServAxway;

    /** Nombre del mensaje Axway. */
    @Size(max = 120)
    private String nomMensajeAxway;

    /** URL de Axway. */
    @Size(max = 120)
    private String urlAxway;

    /** Fecha de captura. */
    private LocalDate fecCaptura;

    /** Fecha de fin de vigencia. */
    private LocalDate fecFinVigencia;

    /** Nombre del tipo de tramite. */
    @Size(max = 250)
    private String nombre;

    /** Indicador de replica de informacion. */
    private Boolean blnReplicaInfo;

    /** Indicador de automatico. */
    private Boolean blnAutomatico;

    /** Fecha de inicio de vigencia. */
    @NotNull
    private LocalDate fecIniVigencia;

    /** Indicador de activo. */
    @NotNull
    private Boolean blnActivo;

    /** Indicador de asignacion. */
    private Boolean blnAsignacion;

    /** Clave de modulo. */
    @NotNull
    private Short cveModulo;

    /** Identificador de la dependencia asociada. */
    private Short idDependencia;
}
