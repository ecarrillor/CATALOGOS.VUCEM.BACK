package mx.gob.sat.catalogo.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
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
 * <b>Class:</b> CatDependencia.java <br>
 * <b>Description:</b>
 * <p>Entidad JPA para el catalogo de dependencias.</p>
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
@Table(name = "cat_dependencia")
public class CatDependencia {

    /** Identificador de la dependencia. */
    @Id
    @Column(name = "id_dependencia", nullable = false)
    private Long id;

    /** Nombre de la dependencia. */
    @Size(max = 120)
    @Column(name = "nombre", length = 120)
    private String nombre;

    /** Acronimo de la dependencia. */
    @Size(max = 20)
    @Column(name = "acronimo", length = 20)
    private String acronimo;

    /** Calendario asociado. */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cve_calendario", referencedColumnName = "cve_calendario")
    private CatCalendario cveCalendario;

    /** Fecha de captura. */
    @Column(name = "fec_captura")
    private LocalDate fecCaptura;

    /** Indicador de tramites VU. */
    @Column(name = "bln_tramites_vu")
    private Boolean blnTramitesVu;

    /** Fecha de inicio de vigencia. */
    @NotNull
    @Column(name = "fec_ini_vigencia", nullable = false)
    private LocalDate fecIniVigencia;

    /** Fecha de fin de vigencia. */
    @Column(name = "fec_fin_vigencia")
    private LocalDate fecFinVigencia;

    /** Indicador de activo. */
    @NotNull
    @Column(name = "bln_activo", nullable = false)
    private Boolean blnActivo;
}
