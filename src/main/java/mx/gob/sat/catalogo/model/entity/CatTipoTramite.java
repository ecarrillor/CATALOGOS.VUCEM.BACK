package mx.gob.sat.catalogo.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

/**
 * <b>Class:</b> CatTipoTramite.java <br>
 * <b>Description:</b>
 * <p>Entidad JPA para el catalogo de tipos de tramite.</p>
 *
 * @author Javier Chávez Barrios
 * @email jchavezb@ultrasist.com.mx
 *
 * @created 18 de mayo del 2026
 * @version 1.0
 * @category Entidad
 */
@Getter
@Setter
@Entity
@Table(name = "cat_tipo_tramite")
public class CatTipoTramite {

    /** Identificador del tipo de tramite. */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long idTipoTramite;

    /** Clave de servicio. */
    @Size(max = 2)
    @Column(name = "cve_servicio", length = 2)
    private String cveServicio;

    /** Descripcion del servicio. */
    @Size(max = 500)
    @Column(name = "desc_servicio", length = 500)
    private String descServicio;

    /** Clave de subservicio. */
    @Size(max = 5)
    @Column(name = "cve_subservicio", length = 5)
    private String cveSubservicio;

    /** Descripcion del subservicio. */
    @Size(max = 500)
    @Column(name = "desc_subservicio", length = 500)
    private String descSubservicio;

    /** Clave de modalidad. */
    @Size(max = 11)
    @Column(name = "cve_modalidad", length = 11)
    private String cveModalidad;

    /** Descripcion de la modalidad. */
    @Size(max = 500)
    @Column(name = "desc_modalidad", length = 500)
    private String descModalidad;

    /** Clave de flujo. */
    @Size(max = 20)
    @Column(name = "cve_flujo", length = 20)
    private String cveFlujo;

    /** Descripcion del flujo. */
    @Size(max = 250)
    @Column(name = "desc_flujo", length = 250)
    private String descFlujo;

    /** Nivel de servicio. */
    @Column(name = "nivel_servicio")
    private Short nivelServicio;

    /** Nombre del servicio Axway. */
    @Size(max = 120)
    @Column(name = "nom_serv_axway", length = 120)
    private String nomServAxway;

    /** Nombre del mensaje Axway. */
    @Size(max = 120)
    @Column(name = "nom_mensaje_axway", length = 120)
    private String nomMensajeAxway;

    /** URL de Axway. */
    @Size(max = 120)
    @Column(name = "url_axway", length = 120)
    private String urlAxway;

    /** Fecha de captura. */
    @Column(name = "fec_captura")
    private LocalDate fecCaptura;

    /** Fecha de fin de vigencia. */
    @Column(name = "fec_fin_vigencia")
    private LocalDate fecFinVigencia;

    /** Nombre del tipo de tramite. */
    @Size(max = 250)
    @Column(name = "nombre", length = 250)
    private String nombre;

    /** Indicador de replica de informacion. */
    @Column(name = "bln_replica_info")
    private Boolean blnReplicaInfo;

    /** Indicador de automatico. */
    @Column(name = "bln_automatico")
    private Boolean blnAutomatico;

    /** Fecha de inicio de vigencia. */
    @NotNull
    @Column(name = "fec_ini_vigencia", nullable = false)
    private LocalDate fecIniVigencia;

    /** Indicador de activo. */
    @NotNull
    @Column(name = "bln_activo", nullable = false)
    private Boolean blnActivo;

    /** Indicador de asignacion. */
    @Column(name = "bln_asignacion")
    private Boolean blnAsignacion;

    /** Clave de modulo. */
    @NotNull
    @Column(name = "cve_modulo", nullable = false)
    private Short cveModulo;

    /** Dependencia asociada. */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_dependencia", referencedColumnName = "id_dependencia")
    private CatDependencia idDependencia;
}
