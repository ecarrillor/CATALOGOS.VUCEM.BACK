package mx.gob.sat.catalogo.controller.response.catalogod;

import lombok.Builder;
import lombok.Getter;

import java.time.Instant;

/**
 * <b>Class:</b> CatCatalogoDResponse.java <br>
 * <b>Description:</b>
 * <p>DTO de salida para el catalogo de catalogos D (detalle).</p>
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
public class CatCatalogoDResponse {

    /** Clave del catalogo D. */
    private String cveCatalogo;

    /** Codigo generico 1. */
    private String codigoGenerico1;

    /** Codigo generico 2. */
    private String codigoGenerico2;

    /** Descripcion generica 1. */
    private String descGenerica1;

    /** Descripcion generica 2. */
    private String descGenerica2;

    /** Numero generico 1. */
    private Long numGenerico1;

    /** Numero generico 2. */
    private Long numGenerico2;

    /** Fecha de inicio de vigencia. */
    private Instant fecIniVigencia;

    /** Fecha de fin de vigencia. */
    private Instant fecFinVigencia;

    /** Indicador de activo. */
    private Boolean blnActivo;

    /** Clave del catalogo H asociado. */
    private String cveCatalogoH;

    /** Nombre del catalogo H asociado. */
    private String nomCatalogoH;

    /** Clave del catalogo D relacionado. */
    private String cveCatalogoR;
}
