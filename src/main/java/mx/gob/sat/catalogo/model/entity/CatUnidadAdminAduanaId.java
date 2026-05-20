package mx.gob.sat.catalogo.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Data;
import java.io.Serializable;

/**
 * <b>Class:</b> CatUnidadAdminAduanaId.java <br>
 * <b>Description:</b>
 * <p>Clave compuesta para la entidad CatUnidadAdminAduana.</p>
 *
 * @author Javier Chávez Barrios
 * @email jchavezb@ultrasist.com.mx
 *
 * @created 18 de mayo del 2026
 * @version 1.0
 * @category Entidad
 */
@Data
@Embeddable
public class CatUnidadAdminAduanaId implements Serializable {
    private static final long serialVersionUID = 1L;

    @Column(name = "cve_unidad_administrativa", nullable = false, length = 10)
    private String cveUnidadAdministrativa;

    @Column(name = "cve_aduana", nullable = false, length = 3)
    private String cveAduana;
}
