package mx.gob.sat.catalogo.controller.request.recintofiscalizado;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

/**
 * <b>Class:</b> CatRecintoFiscalizadoRequest.java <br>
 * <b>Description:</b>
 * <p>DTO de entrada para el catalogo de recintos fiscalizados.</p>
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
public class CatRecintoFiscalizadoRequest {

    /** Identificador de tipo de recinto fiscalizado. */
    @NotNull
    @Size(max = 20)
    private String ideTipoRecintoFiscalizado;

    /** Nombre del recinto fiscalizado. */
    @NotNull
    @Size(max = 120)
    private String nombre;

    /** RFC del recinto fiscalizado. */
    @Size(max = 13)
    private String rfc;

    /** Numero de autorizacion. */
    @Size(max = 25)
    private String numAutorizacion;

    /** Fecha de inicio de vigencia. */
    @NotNull
    private LocalDate fecIniVigencia;

    /** Fecha de fin de vigencia. */
    private LocalDate fecFinVigencia;

    /** Indicador de activo. */
    @NotNull
    private Boolean blnActivo;

    /** Codigo CAMIR. */
    @Size(max = 4)
    private String codCamir;

    /** Indicador de comercio RF y MF. */
    @NotNull
    private Boolean blnComRfMf;

    /** Correo electronico. */
    @Size(max = 300)
    private String correoElectronico;

    /** URL del recinto. */
    @Size(max = 300)
    private String descUrl;

    /** Tipo. */
    @Size(max = 5)
    private String tipo;

    /** Clave de la aduana asociada. */
    private String cveAduana;
}
