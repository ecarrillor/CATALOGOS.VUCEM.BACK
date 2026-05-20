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
 * <b>Class:</b> CatTipoEmpresaRecif.java <br>
 * <b>Description:</b>
 * <p>Entidad JPA para el catalogo de tipos de empresa RECIF.</p>
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
@Table(name = "cat_tipo_empresa_recif")
public class CatTipoEmpresaRecif {

    /** Clave del tipo de empresa RECIF. */
    @Id
    @Size(max = 2)
    @Column(name = "cve_tipo_empresa_recif", nullable = false, length = 2)
    private String cveTipoEmpresaRecif;

    /** Descripcion del tipo de empresa RECIF. */
    @Size(max = 50)
    @Column(name = "descripcion", length = 50)
    private String descripcion;

    /** Tipo de empresa RECIF relacionado (auto-referencia). */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cve_tipo_empresa_recif_r", referencedColumnName = "cve_tipo_empresa_recif")
    private CatTipoEmpresaRecif cveTipoEmpresaRecifR;

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
