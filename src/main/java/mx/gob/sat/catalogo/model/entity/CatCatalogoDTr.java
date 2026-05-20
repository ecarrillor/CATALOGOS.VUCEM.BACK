package mx.gob.sat.catalogo.model.entity;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table(name = "cat_catalogo_d_tr")
public class CatCatalogoDTr {

    @EmbeddedId
    private CatCatalogoDTrId id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cve_catalogo", referencedColumnName = "cve_catalogo", insertable = false, updatable = false)
    private CatCatalogoD cveCatalogo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cve_lenguaje", referencedColumnName = "cve_lenguaje", insertable = false, updatable = false)
    private CatLenguaje cveLenguaje;

    @Size(max = 2000)
    @Column(name = "descripcion", length = 2000)
    private String descripcion;

    @Column(name = "fec_ini_vigencia")
    private LocalDate fecIniVigencia;

    @Column(name = "fec_fin_vigencia")
    private LocalDate fecFinVigencia;

    @Column(name = "bln_activo")
    private Boolean blnActivo;
}
