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
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * <b>Class:</b> CatEquivMoneda.java <br>
 * <b>Description:</b>
 * <p>Entidad JPA para el catalogo de equivalencias de moneda.</p>
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
@Table(name = "cat_equiv_moneda")
public class CatEquivMoneda {

    /** Identificador de la equivalencia de moneda. */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer idEquivMoneda;

    /** Valor de conversion. */
    @NotNull
    @Column(name = "valor_conversion", nullable = false, precision = 22, scale = 8)
    private BigDecimal valorConversion;

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

    /** Moneda de origen. */
    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cve_moneda_origen", referencedColumnName = "cve_moneda", nullable = false)
    private CatMoneda cveMonedaOrigen;

    /** Moneda de destino. */
    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cve_moneda_destino", referencedColumnName = "cve_moneda", nullable = false)
    private CatMoneda cveMonedaDestino;
}
