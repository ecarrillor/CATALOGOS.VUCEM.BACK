package mx.gob.sat.catalogo.controller.request.equivmoneda;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * <b>Class:</b> CatEquivMonedaRequest.java <br>
 * <b>Description:</b>
 * <p>DTO de entrada para el catalogo de equivalencias de moneda.</p>
 *
 * @author Javier Chávez Barrios
 * @email jchavezb@ultrasist.com.mx
 *
 * @created 18 de mayo del 2026
 * @version 1.0
 * @category Request DTO
 */
@Getter
@Setter
public class CatEquivMonedaRequest {

    /** Valor de conversion. */
    @NotNull
    private BigDecimal valorConversion;

    /** Fecha de inicio de vigencia. */
    @NotNull
    private LocalDate fecIniVigencia;

    /** Fecha de fin de vigencia. */
    private LocalDate fecFinVigencia;

    /** Indicador de activo. */
    @NotNull
    private Boolean blnActivo;

    /** Clave de la moneda de origen. */
    @NotNull
    private String cveMonedaOrigen;

    /** Clave de la moneda de destino. */
    @NotNull
    private String cveMonedaDestino;
}
