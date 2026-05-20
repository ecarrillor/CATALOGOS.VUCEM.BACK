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
 * <b>Class:</b> CatPatenteAduanal.java <br>
 * <b>Description:</b>
 * <p>Entidad JPA para el catalogo de patentes aduanales.</p>
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
@Table(name = "cat_patente_aduanal")
public class CatPatenteAduanal {

    /** Clave de la patente aduanal. */
    @Id
    @Size(max = 4)
    @Column(name = "cve_patente_aduanal", nullable = false, length = 4)
    private String cvePatenteAduanal;

    /** RFC del agente aduanal. */
    @Size(max = 13)
    @Column(name = "rfc", length = 13)
    private String rfc;

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

    /** Identificador de estado de patente autorizada. */
    @Size(max = 20)
    @Column(name = "ide_est_patente_aut", length = 20)
    private String ideEstPatenteAut;
}
