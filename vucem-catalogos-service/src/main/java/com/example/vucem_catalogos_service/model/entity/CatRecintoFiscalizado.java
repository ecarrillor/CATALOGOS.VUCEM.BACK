package com.example.vucem_catalogos_service.model.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

@Data
@Entity
@Table(name = "cat_recinto_fiscalizado")
public class CatRecintoFiscalizado {
    @Id
    @Column(name = "id_recinto_fiscalizado", nullable = false)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cve_aduana", referencedColumnName = "cve_aduana")
    private CatAduana cveAduana;

    @Size(max = 20)
    @NotNull
    @Column(name = "ide_tipo_recinto_fiscalizado", nullable = false, length = 20)
    private String ideTipoRecintoFiscalizado;

    @Size(max = 120)
    @NotNull
    @Column(name = "nombre", nullable = false, length = 120)
    private String nombre;

    @Size(max = 13)
    @Column(name = "rfc", length = 13)
    private String rfc;

    @Size(max = 25)
    @Column(name = "num_autorizacion", length = 25)
    private String numAutorizacion;

    @NotNull
    @Column(name = "fec_ini_vigencia", nullable = false)
    private Instant fecIniVigencia;

    @Column(name = "fec_fin_vigencia")
    private Instant fecFinVigencia;

    @NotNull
    @Column(name = "bln_activo", nullable = false)
    private Boolean blnActivo;

    @Size(max = 4)
    @Column(name = "cod_camir", length = 4)
    private String codCamir;

    @NotNull
    @Column(name = "bln_com_rf_mf", nullable = false)
    private Short blnComRfMf;

    @Size(max = 300)
    @Column(name = "correo_electronico", length = 300)
    private String correoElectronico;

    @Size(max = 300)
    @Column(name = "desc_url", length = 300)
    private String descUrl;

    @Size(max = 5)
    @Column(name = "tipo", length = 5)
    private String tipo;


}
