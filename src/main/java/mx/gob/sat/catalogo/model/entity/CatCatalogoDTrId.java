package mx.gob.sat.catalogo.model.entity;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Data;
import java.io.Serializable;

@Data
@Embeddable
public class CatCatalogoDTrId implements Serializable {
    private static final long serialVersionUID = 1L;

    @Column(name = "cve_catalogo", nullable = false, length = 10)
    private String cveCatalogo;

    @Column(name = "cve_lenguaje", nullable = false, length = 2)
    private String cveLenguaje;
}
