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
 * <b>Class:</b> CatAprobCertSe.java <br>
 * <b>Description:</b>
 * <p>Entidad JPA para el catalogo de aprobaciones de certificado SE.</p>
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
@Table(name = "cat_aprob_cert_se")
public class CatAprobCertSe {

    /** Identificador de la aprobacion certificado SE (PK). */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_aprob_cert_se", nullable = false)
    private Short idAprobCertSe;

    /** Identificador de tipo de aprobacion certificado SE. */
    @NotNull
    @Size(max = 20)
    @Column(name = "ide_tipo_aprob_cert_se", nullable = false, length = 20)
    private String ideTipoAprobCertSe;

    /** Numero de aprobacion certificado. */
    @NotNull
    @Size(max = 30)
    @Column(name = "num_aprob_cert", nullable = false, length = 30)
    private String numAprobCert;

    /** Fecha de emision. */
    @Column(name = "fec_emision")
    private LocalDate fecEmision;

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

    /** Unidad administrativa asociada. */
    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "cve_unidad_administrativa", nullable = false)
    private CatUnidadAdministrativa cveUnidadAdministrativa;
}
