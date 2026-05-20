package mx.gob.sat.catalogo.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

/**
 * <b>Class:</b> CatCatalogoH.java <br>
 * <b>Description:</b>
 * <p>Entidad JPA para el catalogo de catalogos H (encabezado).</p>
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
@Table(name = "cat_catalogo_h")
public class CatCatalogoH {

    /** Clave del catalogo H. */
    @Id
    @Size(max = 6)
    @Column(name = "cve_catalogo_h", nullable = false, length = 6)
    private String cveCatalogoH;

    /** Nombre del catalogo. */
    @NotNull
    @Size(max = 300)
    @Column(name = "nom_catalogo", nullable = false, length = 300)
    private String nomCatalogo;

    /** Fecha de inicio de vigencia. */
    @NotNull
    @Column(name = "fec_ini_vigencia", nullable = false)
    private Instant fecIniVigencia;

    /** Fecha de fin de vigencia. */
    @Column(name = "fec_fin_vigencia")
    private Instant fecFinVigencia;

    /** Indicador de activo. */
    @NotNull
    @Column(name = "bln_activo", nullable = false)
    private Boolean blnActivo;
}
