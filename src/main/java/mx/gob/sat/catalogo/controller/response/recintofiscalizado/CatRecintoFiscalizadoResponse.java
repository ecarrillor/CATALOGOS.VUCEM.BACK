package mx.gob.sat.catalogo.controller.response.recintofiscalizado;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;

/**
 * <b>Class:</b> CatRecintoFiscalizadoResponse.java <br>
 * <b>Description:</b>
 * <p>DTO de salida para el catalogo de recintos fiscalizados.</p>
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
public class CatRecintoFiscalizadoResponse {

    /** Identificador del recinto fiscalizado. */
    private Long idRecintoFiscalizado;

    /** Identificador de tipo de recinto fiscalizado. */
    private String ideTipoRecintoFiscalizado;

    /** Nombre del recinto fiscalizado. */
    private String nombre;

    /** RFC del recinto fiscalizado. */
    private String rfc;

    /** Numero de autorizacion. */
    private String numAutorizacion;

    /** Fecha de inicio de vigencia. */
    private LocalDate fecIniVigencia;

    /** Fecha de fin de vigencia. */
    private LocalDate fecFinVigencia;

    /** Indicador de activo. */
    private Boolean blnActivo;

    /** Codigo CAMIR. */
    private String codCamir;

    /** Indicador de comercio RF y MF. */
    private Boolean blnComRfMf;

    /** Correo electronico. */
    private String correoElectronico;

    /** URL del recinto. */
    private String descUrl;

    /** Tipo. */
    private String tipo;

    /** Clave de la aduana asociada. */
    private String cveAduana;

    /** Nombre de la aduana asociada. */
    private String nombreAduana;
}
