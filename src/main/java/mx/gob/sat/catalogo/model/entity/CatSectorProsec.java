package mx.gob.sat.catalogo.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

/**
 * <b>Class:</b> CatSectorProsec.java <br>
 * <b>Description:</b>
 * <p>Entidad JPA para el catalogo de sectores PROSEC.</p>
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
@Table(name = "cat_sector_prosec")
public class CatSectorProsec {

    /** Clave del sector PROSEC. */
    @Id
    @Size(max = 6)
    @Column(name = "cve_sector_prosec", nullable = false, length = 6)
    private String cveSectorProsec;

    /** Nombre del sector PROSEC. */
    @Size(max = 120)
    @Column(name = "nombre", length = 120)
    private String nombre;

    /** Indicador de productor indirecto. */
    @Column(name = "bln_productor_indirecto")
    private Boolean blnProductorIndirecto;

    /** Indicador de ampliacion de mercancias. */
    @Column(name = "bln_ampliacion_mercancias")
    private Boolean blnAmpliacionMercancias;

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
