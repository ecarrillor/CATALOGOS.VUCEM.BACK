package mx.gob.sat.catalogo.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

/**
 * <b>Class:</b> CatSitDomIdc.java <br>
 * <b>Description:</b>
 * <p>Entidad JPA para el catalogo de situacion domicilio IDC.</p>
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
@Table(name = "cat_sit_dom_idc")
public class CatSitDomIdc {

    /** Identificador de situacion domicilio IDC. */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long idSitDomIdc;

    /** Descripcion. */
    @Size(max = 1000)
    @Column(name = "descripcion", length = 1000)
    private String descripcion;

    /** Fecha de inicio de vigencia. */
    @Column(name = "fec_ini_vigencia")
    private Instant fecIniVigencia;

    /** Fecha de fin de vigencia. */
    @Column(name = "fec_fin_vigencia")
    private Instant fecFinVigencia;

    /** Indicador de activo. */
    @Column(name = "bln_activo")
    private Boolean blnActivo;
}
