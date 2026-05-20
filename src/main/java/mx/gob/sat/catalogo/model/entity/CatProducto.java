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
 * <b>Class:</b> CatProducto.java <br>
 * <b>Description:</b>
 * <p>Entidad JPA para el catalogo de productos.</p>
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
@Table(name = "cat_producto")
public class CatProducto {

    /** Clave del producto. */
    @Id
    @Size(max = 10)
    @Column(name = "cve_producto", nullable = false, length = 10)
    private String cveProducto;

    /** Sigla del producto. */
    @Size(max = 3)
    @Column(name = "sigla", length = 3)
    private String sigla;

    /** Nombre del producto. */
    @Size(max = 60)
    @Column(name = "nombre", length = 60)
    private String nombre;

    /** Descripcion del producto. */
    @Size(max = 120)
    @Column(name = "descripcion", length = 120)
    private String descripcion;

    /** Fecha de captura. */
    @Column(name = "fec_captura")
    private LocalDate fecCaptura;

    /** Fecha de fin de vigencia. */
    @Column(name = "fec_fin_vigencia")
    private LocalDate fecFinVigencia;

    /** Fecha de inicio de vigencia. */
    @NotNull
    @Column(name = "fec_ini_vigencia", nullable = false)
    private LocalDate fecIniVigencia;

    /** Indicador de activo. */
    @NotNull
    @Column(name = "bln_activo", nullable = false)
    private Boolean blnActivo;
}
