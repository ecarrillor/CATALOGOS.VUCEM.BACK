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
 * <b>Class:</b> CatEmpresaRecif.java <br>
 * <b>Description:</b>
 * <p>Entidad JPA para el catalogo de empresas RECIF.</p>
 *
 * @author Javier Chávez Barrios
 * @email jchavezb@ultrasist.com.mx
 * @created 18 de mayo del 2026
 * @version 1.0
 * @category Entidad
 */
@Getter
@Setter
@Entity
@Table(name = "cat_empresa_recif")
public class CatEmpresaRecif {

    /** Clave RECIF (PK). */
    @Id
    @Size(max = 21)
    @Column(name = "recif", nullable = false, length = 21)
    private String recif;

    /** RFC de la empresa. */
    @Size(max = 13)
    @Column(name = "rfc", length = 13)
    private String rfc;

    /** Razon social de la empresa. */
    @Size(max = 255)
    @Column(name = "razon_social", length = 255)
    private String razonSocial;

    /** Indicador de activo. */
    @NotNull
    @Column(name = "bln_activo", nullable = false)
    private Boolean blnActivo;

    /** Fecha de inicio de vigencia. */
    @NotNull
    @Column(name = "fec_ini_vigencia", nullable = false)
    private LocalDate fecIniVigencia;

    /** Fecha de fin de vigencia. */
    @Column(name = "fec_fin_vigencia")
    private LocalDate fecFinVigencia;

    /** Unidad administrativa asociada. */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cve_unidad_administrativa")
    private CatUnidadAdministrativa cveUnidadAdministrativa;
}
