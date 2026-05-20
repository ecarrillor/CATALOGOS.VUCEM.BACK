package mx.gob.sat.catalogo.controller.request.catalogod;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

/**
 * <b>Class:</b> CatCatalogoDRequest.java <br>
 * <b>Description:</b>
 * <p>DTO de entrada para el catalogo de catalogos D (detalle).</p>
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
public class CatCatalogoDRequest {

    /** Clave del catalogo D. */
    @NotNull
    @Size(max = 10)
    private String cveCatalogo;

    /** Codigo generico 1. */
    @Size(max = 100)
    private String codigoGenerico1;

    /** Codigo generico 2. */
    @Size(max = 100)
    private String codigoGenerico2;

    /** Descripcion generica 1. */
    @Size(max = 1000)
    private String descGenerica1;

    /** Descripcion generica 2. */
    @Size(max = 300)
    private String descGenerica2;

    /** Numero generico 1. */
    private Long numGenerico1;

    /** Numero generico 2. */
    private Long numGenerico2;

    /** Fecha de inicio de vigencia. */
    @NotNull
    private Instant fecIniVigencia;

    /** Fecha de fin de vigencia. */
    private Instant fecFinVigencia;

    /** Indicador de activo. */
    @NotNull
    private Boolean blnActivo;

    /** Clave del catalogo H asociado. */
    @NotNull
    private String cveCatalogoH;

    /** Clave del catalogo D relacionado (auto-referencia). */
    private String cveCatalogoR;
}
