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
 * <b>Class:</b> CatRecintoFiscalizado.java <br>
 * <b>Description:</b>
 * <p>Entidad JPA para el catalogo de recintos fiscalizados.</p>
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
@Table(name = "cat_recinto_fiscalizado")
public class CatRecintoFiscalizado {

    /** Identificador del recinto fiscalizado. */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long idRecintoFiscalizado;

    /** Identificador de tipo de recinto fiscalizado. */
    @NotNull
    @Size(max = 20)
    @Column(name = "ide_tipo_recinto_fiscalizado", nullable = false, length = 20)
    private String ideTipoRecintoFiscalizado;

    /** Nombre del recinto fiscalizado. */
    @NotNull
    @Size(max = 120)
    @Column(name = "nombre", nullable = false, length = 120)
    private String nombre;

    /** RFC del recinto fiscalizado. */
    @Size(max = 13)
    @Column(name = "rfc", length = 13)
    private String rfc;

    /** Numero de autorizacion. */
    @Size(max = 25)
    @Column(name = "num_autorizacion", length = 25)
    private String numAutorizacion;

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

    /** Codigo CAMIR. */
    @Size(max = 4)
    @Column(name = "cod_camir", length = 4)
    private String codCamir;

    /** Indicador de comercio RF y MF. */
    @NotNull
    @Column(name = "bln_com_rf_mf", nullable = false)
    private Boolean blnComRfMf;

    /** Correo electronico. */
    @Size(max = 300)
    @Column(name = "correo_electronico", length = 300)
    private String correoElectronico;

    /** URL del recinto. */
    @Size(max = 300)
    @Column(name = "desc_url", length = 300)
    private String descUrl;

    /** Tipo. */
    @Size(max = 5)
    @Column(name = "tipo", length = 5)
    private String tipo;

    /** Aduana asociada. */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cve_aduana", referencedColumnName = "cve_aduana")
    private CatAduana cveAduana;
}
