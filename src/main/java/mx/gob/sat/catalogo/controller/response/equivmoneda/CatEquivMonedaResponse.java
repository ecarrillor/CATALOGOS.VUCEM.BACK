package mx.gob.sat.catalogo.controller.response.equivmoneda;

import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * <b>Class:</b> CatEquivMonedaResponse.java <br>
 * <b>Description:</b>
 * <p>DTO de salida para el catalogo de equivalencias de moneda.</p>
 *
 * @author Javier Chávez Barrios
 * @email jchavezb@ultrasist.com.mx
 *
 * @created 18 de mayo del 2026
 * @version 1.0
 * @category Response DTO
 */
@Getter
@Builder
public class CatEquivMonedaResponse {

    /** Identificador de la equivalencia de moneda. */
    private Integer idEquivMoneda;

    /** Valor de conversion. */
    private BigDecimal valorConversion;

    /** Fecha de inicio de vigencia. */
    private LocalDate fecIniVigencia;

    /** Fecha de fin de vigencia. */
    private LocalDate fecFinVigencia;

    /** Indicador de activo. */
    private Boolean blnActivo;

    /** Clave de la moneda de origen. */
    private String cveMonedaOrigen;

    /** Nombre de la moneda de origen. */
    private String nombreMonedaOrigen;

    /** Clave de la moneda de destino. */
    private String cveMonedaDestino;

    /** Nombre de la moneda de destino. */
    private String nombreMonedaDestino;
}
