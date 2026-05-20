package mx.gob.sat.catalogo.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table(name = "cat_combinacion_sg")
public class CatCombinacionSg {

    @Id
    @Column(name = "id_combinacion_sg", nullable = false)
    private Long idCombinacionSg;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cvc_especie", referencedColumnName = "cve_catalogo")
    private CatCatalogoD cvcEspecie;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cvc_funcion_zootecnica", referencedColumnName = "cve_catalogo")
    private CatCatalogoD cvcFuncionZootecnica;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cvc_nombre_comun", referencedColumnName = "cve_catalogo")
    private CatCatalogoD cvcNombreComun;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cvc_tipo_producto", referencedColumnName = "cve_catalogo")
    private CatCatalogoD cvcTipoProducto;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cve_pais", referencedColumnName = "cve_pais")
    private CatPais cvePais;

    @NotNull
    @Column(name = "fec_ini_vigencia", nullable = false)
    private LocalDate fecIniVigencia;

    @Column(name = "fec_fin_vigencia")
    private LocalDate fecFinVigencia;

    @Column(name = "bln_activo")
    private Boolean blnActivo;

    @Size(max = 20)
    @Column(name = "ide_tipo_certificado_merc", length = 20)
    private String ideTipoCertificadoMerc;
}
