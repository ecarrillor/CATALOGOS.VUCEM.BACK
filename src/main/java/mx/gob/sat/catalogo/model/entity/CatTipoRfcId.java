package mx.gob.sat.catalogo.model.entity;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Data;
import java.io.Serializable;

@Data
@Embeddable
public class CatTipoRfcId implements Serializable {
    private static final long serialVersionUID = 1L;

    @Column(name = "rfc", nullable = false, length = 13)
    private String rfc;

    @Column(name = "ide_tipo_rfc", nullable = false, length = 20)
    private String ideTipoRfc;
}
