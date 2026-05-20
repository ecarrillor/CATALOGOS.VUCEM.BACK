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
 * <b>Class:</b> CatUnidadAdministrativa.java <br>
 * <b>Description:</b>
 * <p>Entidad JPA para el catalogo de unidades administrativas.</p>
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
@Table(name = "cat_unidad_administrativa")
public class CatUnidadAdministrativa {

    /** Clave de la unidad administrativa. */
    @Id
    @Size(max = 10)
    @Column(name = "cve_unidad_administrativa", nullable = false, length = 10)
    private String cveUnidadAdministrativa;

    /** Identificador de tipo de unidad administrativa. */
    @Size(max = 20)
    @Column(name = "ide_tipo_unidad_administrativa", length = 20)
    private String ideTipoUnidadAdministrativa;

    /** Nivel de la unidad administrativa. */
    @Column(name = "nivel")
    private Short nivel;

    /** Acronimo de la unidad administrativa. */
    @Size(max = 20)
    @Column(name = "acronimo", length = 20)
    private String acronimo;

    /** Nombre de la unidad administrativa. */
    @Size(max = 120)
    @Column(name = "nombre", length = 120)
    private String nombre;

    /** Descripcion de la unidad administrativa. */
    @Size(max = 120)
    @Column(name = "descripcion", length = 120)
    private String descripcion;

    /** Indicador de fronteriza. */
    @Column(name = "bln_fronteriza")
    private Boolean blnFronteriza;

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

    /** Unidad administrativa relacionada (auto-referencia). */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cve_unidad_administrativa_r", referencedColumnName = "cve_unidad_administrativa")
    private CatUnidadAdministrativa cveUnidadAdministrativaR;

    /** Entidad federativa asociada. */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cve_entidad", referencedColumnName = "cve_entidad")
    private CatEntidad cveEntidad;

    /** Dependencia asociada. */
    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_dependencia", referencedColumnName = "id_dependencia", nullable = false)
    private CatDependencia idDependencia;
}
