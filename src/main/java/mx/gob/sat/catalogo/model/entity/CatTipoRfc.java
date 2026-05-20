package mx.gob.sat.catalogo.model.entity;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table(name = "cat_tipo_rfc")
public class CatTipoRfc {

    @EmbeddedId
    private CatTipoRfcId id;

    @Size(max = 254)
    @Column(name = "razon_social", length = 254)
    private String razonSocial;

    @Column(name = "fec_fin_vigencia")
    private LocalDate fecFinVigencia;

    @Column(name = "fec_ini_vigencia")
    private LocalDate fecIniVigencia;

    @Size(max = 255)
    @Column(name = "direccion", length = 255)
    private String direccion;

    @Size(max = 30)
    @Column(name = "telefono", length = 30)
    private String telefono;

    @Size(max = 20)
    @Column(name = "clave", length = 20)
    private String clave;

    @Column(name = "bln_activo")
    private Boolean blnActivo;

    @Size(max = 320)
    @Column(name = "correo_electronico", length = 320)
    private String correoElectronico;

    @Size(max = 30)
    @Column(name = "fax", length = 30)
    private String fax;

    @Column(name = "bln_lab_acreditado")
    private Boolean blnLabAcreditado;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cve_unidad_administrativa")
    private CatUnidadAdministrativa cveUnidadAdministrativa;
}
