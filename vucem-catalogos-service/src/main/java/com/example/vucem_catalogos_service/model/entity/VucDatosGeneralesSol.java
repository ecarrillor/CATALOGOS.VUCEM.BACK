package com.example.vucem_catalogos_service.model.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.Instant;

@Getter
@Setter
@Entity
@Table(name = "vuc_datos_generales_sol")
public class VucDatosGeneralesSol {
    @Id
    @Column(name = "id_solicitud", nullable = false)
    private Long id;

    @Size(max = 500)
    @Column(name = "act_econo_preponderante", length = 500)
    private String actEconoPreponderante;

    @Size(max = 2)
    @Column(name = "ambito", length = 2)
    private String ambito;

    @Column(name = "anio")
    private Short anio;

    @Size(max = 2)
    @Column(name = "avaluo_bien_cultural", length = 2)
    private String avaluoBienCultural;

    @Size(max = 35)
    @Column(name = "clabe_bancaria", length = 35)
    private String clabeBancaria;

    @Size(max = 7)
    @Column(name = "clave_pedimento", length = 7)
    private String clavePedimento;

    @Size(max = 256)
    @Column(name = "desc_sist_pesaje_medicion", length = 256)
    private String descSistPesajeMedicion;

    @Size(max = 2)
    @Column(name = "estado", length = 2)
    private String estado;

    @Size(max = 2)
    @Column(name = "factor_conversion", length = 2)
    private String factorConversion;

    @Column(name = "fec_embarque")
    private Instant fecEmbarque;

    @Column(name = "fec_operacion")
    private Instant fecOperacion;

    @Column(name = "fec_pago")
    private Instant fecPago;

    @Column(name = "fec_revocacion")
    private Instant fecRevocacion;

    @Column(name = "fec_verificacion")
    private Instant fecVerificacion;

    @Size(max = 2)
    @Column(name = "chk_sr_reg_vehiculos", length = 2)
    private String chkSrRegVehiculos;

    @Column(name = "bln_consolidacion_cargas")
    private Short blnConsolidacionCargas;

    @Size(max = 2)
    @Column(name = "chk_toma_muestra", length = 2)
    private String chkTomaMuestra;

    @Size(max = 2)
    @Column(name = "motivo", length = 2)
    private String motivo;

    @Size(max = 2)
    @Column(name = "chk_danio_exposicion", length = 2)
    private String chkDanioExposicion;

    @Column(name = "fec_reporte_pruebas")
    private Instant fecReportePruebas;

    @Column(name = "fec_fin")
    private Instant fecFin;

    @Column(name = "fec_inicio")
    private Instant fecInicio;

    @Column(name = "justificacion_tecnica", length = Integer.MAX_VALUE)
    private String justificacionTecnica;

    @Size(max = 2)
    @Column(name = "metodo_separacion_contable", length = 2)
    private String metodoSeparacionContable;

    @Column(name = "monto", precision = 16, scale = 2)
    private BigDecimal monto;

    @Size(max = 100)
    @Column(name = "num_autorizacion", length = 100)
    private String numAutorizacion;

    @Size(max = 25)
    @Column(name = "num_certificado", length = 25)
    private String numCertificado;

    @Size(max = 2)
    @Column(name = "num_certificado_internacional", length = 2)
    private String numCertificadoInternacional;

    @Size(max = 2)
    @Column(name = "num_constancia", length = 2)
    private String numConstancia;

    @Size(max = 35)
    @Column(name = "num_cuanta_bancaria", length = 35)
    private String numCuantaBancaria;

    @Size(max = 2)
    @Column(name = "num_expediente", length = 2)
    private String numExpediente;

    @Size(max = 2)
    @Column(name = "num_licencia", length = 2)
    private String numLicencia;

    @Column(name = "patente_agente_adu")
    private Short patenteAgenteAdu;

    @Size(max = 30)
    @Column(name = "num_permiso", length = 30)
    private String numPermiso;

    @Size(max = 30)
    @Column(name = "num_prog_dgcese", length = 30)
    private String numProgDgcese;

    @Size(max = 15)
    @Column(name = "num_registro", length = 15)
    private String numRegistro;

    @Size(max = 15)
    @Column(name = "num_registro_ambiental", length = 15)
    private String numRegistroAmbiental;

    @Size(max = 2)
    @Column(name = "num_registro_uma", length = 2)
    private String numRegistroUma;

    @Size(max = 2)
    @Column(name = "num_reporte", length = 2)
    private String numReporte;

    @Size(max = 17)
    @Column(name = "num_sucursal_bancaria", length = 17)
    private String numSucursalBancaria;

    @Size(max = 200)
    @Column(name = "nom_banco", length = 200)
    private String nomBanco;

    @Size(max = 200)
    @Column(name = "nom_consul", length = 200)
    private String nomConsul;

    @Size(max = 200)
    @Column(name = "nom_oficial_autorizado", length = 200)
    private String nomOficialAutorizado;

    @Size(max = 2)
    @Column(name = "num_certificados", length = 2)
    private String numCertificados;

    @Size(max = 2)
    @Column(name = "num_copias", length = 2)
    private String numCopias;

    @Size(max = 2)
    @Column(name = "personal", length = 2)
    private String personal;

    @Size(max = 2)
    @Column(name = "preferencia_arancelaria", length = 2)
    private String preferenciaArancelaria;

    @Size(max = 17)
    @Column(name = "puntos_verificacion", length = 17)
    private String puntosVerificacion;

    @Size(max = 2)
    @Column(name = "tipo_regimen", length = 2)
    private String tipoRegimen;

    @Size(max = 2)
    @Column(name = "c_region", length = 2)
    private String cRegion;

    @Size(max = 2)
    @Column(name = "region", length = 2)
    private String region;

    @Size(max = 2)
    @Column(name = "tasa_ad_valorem", length = 2)
    private String tasaAdValorem;

    @Size(max = 2)
    @Column(name = "tipo_cambio", length = 2)
    private String tipoCambio;

    @Size(max = 2)
    @Column(name = "tipo_moneda", length = 2)
    private String tipoMoneda;

    @Size(max = 2)
    @Column(name = "tipo_operacion", length = 2)
    private String tipoOperacion;

    @Size(max = 2)
    @Column(name = "tratado", length = 2)
    private String tratado;

    @Column(name = "imp_valor_comercial", precision = 19, scale = 2)
    private BigDecimal impValorComercial;

    @Size(max = 20)
    @Column(name = "ide_tipo_solicitud_pexim", length = 20)
    private String ideTipoSolicitudPexim;

    @Size(max = 20)
    @Column(name = "ide_actividad_productiva", length = 20)
    private String ideActividadProductiva;

    @Size(max = 20)
    @Column(name = "ide_tipo_caat", length = 20)
    private String ideTipoCaat;

    @Size(max = 20)
    @Column(name = "ide_tipo_prog_fom_exp", length = 20)
    private String ideTipoProgFomExp;

    @Size(max = 20)
    @Column(name = "ide_tipo_transito", length = 20)
    private String ideTipoTransito;

    @Size(max = 2000)
    @Column(name = "observaciones", length = 2000)
    private String observaciones;

    @Column(name = "descripcion", length = Integer.MAX_VALUE)
    private String descripcion;

    @Size(max = 4000)
    @Column(name = "desc_generica1", length = 4000)
    private String descGenerica1;

    @Size(max = 2000)
    @Column(name = "desc_generica2", length = 2000)
    private String descGenerica2;

    @Size(max = 2000)
    @Column(name = "desc_generica3", length = 2000)
    private String descGenerica3;

    @Size(max = 20)
    @Column(name = "ide_generica1", length = 20)
    private String ideGenerica1;

    @Size(max = 20)
    @Column(name = "ide_generica2", length = 20)
    private String ideGenerica2;

    @Size(max = 20)
    @Column(name = "ide_generica3", length = 20)
    private String ideGenerica3;

    @Column(name = "bln_empresa_mismo_grupo")
    private Short blnEmpresaMismoGrupo;

    @Size(max = 8)
    @Column(name = "plazo", length = 8)
    private String plazo;

    @Column(name = "bln_reg_automatizado")
    private Short blnRegAutomatizado;

    @Column(name = "bln_immex")
    private Short blnImmex;

    @Column(name = "periodo_dictaminacion")
    private Short periodoDictaminacion;

    @Column(name = "desc_clob_generica1", length = Integer.MAX_VALUE)
    private String descClobGenerica1;

    @Column(name = "desc_clob_generica2", length = Integer.MAX_VALUE)
    private String descClobGenerica2;

    @Column(name = "bln_empresa_controladora")
    private Short blnEmpresaControladora;

    @Column(name = "fec_propuesta_visita")
    private Instant fecPropuestaVisita;

    @Column(name = "bln_merc_forma_parte_patrim")
    private Short blnMercFormaPartePatrim;

    @Size(max = 15)
    @Column(name = "cve_ucon", length = 15)
    private String cveUcon;

    @Size(max = 120)
    @Column(name = "nom_establecimiento_operacion", length = 120)
    private String nomEstablecimientoOperacion;

    @Size(max = 120)
    @Column(name = "nom_establecimiento_tif", length = 120)
    private String nomEstablecimientoTif;

    @Column(name = "bln_excento_pago")
    private Short blnExcentoPago;

    @Column(name = "fec_arribo")
    private Instant fecArribo;

    @Column(name = "fec_inspeccion")
    private Instant fecInspeccion;

    @Column(name = "fec_ini_permanencia_extanjero")
    private Instant fecIniPermanenciaExtanjero;

    @Column(name = "fec_fin_permanencia_extanjero")
    private Instant fecFinPermanenciaExtanjero;

    @Column(name = "bln_guardia_custodia")
    private Short blnGuardiaCustodia;

    @Size(max = 200)
    @Column(name = "desc_lugar_embarque", length = 200)
    private String descLugarEmbarque;

    @Size(max = 200)
    @Column(name = "desc_locacion", length = 200)
    private String descLocacion;

    @Size(max = 100)
    @Column(name = "num_permiso_cnsns", length = 100)
    private String numPermisoCnsns;

    @Size(max = 10)
    @Column(name = "num_programa_immex", length = 10)
    private String numProgramaImmex;

    @Size(max = 20)
    @Column(name = "cve_oisa", length = 20)
    private String cveOisa;

    @Size(max = 20)
    @Column(name = "cve_permiso_sedena", length = 20)
    private String cvePermisoSedena;

    @Size(max = 256)
    @Column(name = "desc_punto_ingreso", length = 256)
    private String descPuntoIngreso;

    @Size(max = 20)
    @Column(name = "ide_tipo_movimiento_gob", length = 20)
    private String ideTipoMovimientoGob;

    @Column(name = "fec_tramite_semarnat")
    private Instant fecTramiteSemarnat;

    @Column(name = "bln_prioridad_solicitud")
    private Short blnPrioridadSolicitud;

    @Size(max = 30)
    @Column(name = "ide_actividad_en_destino", length = 30)
    private String ideActividadEnDestino;

    @Column(name = "bln_informacion_confidencial")
    private Short blnInformacionConfidencial;

    @Column(name = "bln_productor_extranjero")
    private Short blnProductorExtranjero;

    @Size(max = 20)
    @Column(name = "num_establecimiento", length = 20)
    private String numEstablecimiento;

    @Size(max = 80)
    @Column(name = "coordenadas_geograficas", length = 80)
    private String coordenadasGeograficas;

    @Column(name = "bln_franja_region_fronteriza")
    private Short blnFranjaRegionFronteriza;

    @Column(name = "capacidad_almacenamiento")
    private Long capacidadAlmacenamiento;

    @Column(name = "bln_req_inspec_inmd")
    private Short blnReqInspecInmd;

    @Size(max = 100)
    @Column(name = "num_carro_ferrocarril", length = 100)
    private String numCarroFerrocarril;

    @Column(name = "id_lugar_inspeccion")
    private Integer idLugarInspeccion;

    @Size(max = 50)
    @Column(name = "desc_guia_ruta", length = 50)
    private String descGuiaRuta;

    @Column(name = "num_total_carros")
    private Short numTotalCarros;

    @Size(max = 50)
    @Column(name = "total_guias", length = 50)
    private String totalGuias;

    @Size(max = 20)
    @Column(name = "ide_medio_transporte_gob", length = 20)
    private String ideMedioTransporteGob;

    @Column(name = "cantidad_bienes")
    private Integer cantidadBienes;

    @Column(name = "bln_generico1")
    private Short blnGenerico1;

    @Size(max = 20)
    @Column(name = "ide_tipo_certificacion_nom", length = 20)
    private String ideTipoCertificacionNom;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_combinacion_sg")
    private CatCombinacionSg idCombinacionSg;

    @Column(name = "bln_modificacion")
    private Short blnModificacion;

    @Column(name = "bln_prorroga")
    private Short blnProrroga;

    @Column(name = "bln_cisen")
    private Short blnCisen;

    @Column(name = "bln_solic_ferroviarios")
    private Short blnSolicFerroviarios;

    @Column(name = "id_folio_externo")
    private Long idFolioExterno;

    @Size(max = 1000)
    @Column(name = "info_adicional", length = 1000)
    private String infoAdicional;

    @Size(max = 25)
    @Column(name = "folio_importacion_temporal", length = 25)
    private String folioImportacionTemporal;


}