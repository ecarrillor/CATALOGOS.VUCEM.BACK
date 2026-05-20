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
 * <b>Class:</b> CatScian.java <br>
 * <b>Description:</b>
 * <p>Entidad JPA para el catalogo SCIAN.</p>
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
@Table(name = "cat_scian")
public class CatScian {

    /** Clave SCIAN. */
    @Id
    @Size(max = 6)
    @Column(name = "cve_scian", nullable = false, length = 6)
    private String cveScian;

    /** Descripcion SCIAN. */
    @Size(max = 250)
    @Column(name = "desc_scian", length = 250)
    private String descScian;

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

    /** Giro. */
    @Size(max = 6)
    @Column(name = "giro", length = 6)
    private String giro;
}
