package com.example.vucem_catalogos_service.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;
import java.time.LocalDate;

@Data
@Entity
@Table(name = "cat_servicio_immex")
public class CatServicioImmex {
    @Id
    @Column(name = "id_servicio_immex", nullable = false)
    private Short id;

    @Size(max = 200)
    @Column(name = "nombre", length = 200)
    private String nombre;

    @NotNull
    @Column(name = "fec_ini_vigencia", nullable = false)
    private LocalDate fecIniVigencia;

    @Size(max = 20)
    @Column(name = "ide_tipo_servicio_immex", length = 20)
    private String ideTipoServicioImmex;

    @Column(name = "fec_fin_vigencia")
    private LocalDate fecFinVigencia;

    @NotNull
    @Column(name = "bln_activo", nullable = false)
    private Boolean blnActivo;


}
