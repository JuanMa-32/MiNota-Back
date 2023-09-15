package com.juanma32.MiNotaEscolar.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.List;

@Entity
@Table(name = "escuelas")
@Data
public class Escuela {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Campo Requerido")
    @Column(length = 30)
    private String numero;
    @NotBlank(message = "Campo Requerido")
    @Column(length = 30)
    private String anexo;
    @NotBlank(message = "Campo Requerido")
    @Column(length = 30)
    private String cue;
    @NotBlank(message = "Campo Requerido")
    @Column(length = 30)
    private String subCue;
    @NotBlank(message = "Campo Requerido")
    @Column(length = 30)
    private String nombre;
    @NotBlank(message = "Campo Requerido")
    @Column(length = 30)
    private String direccionLinea;
    @NotBlank(message = "Campo Requerido")
    @Column(length = 30)
    private String nivel;
    @NotBlank(message = "Campo Requerido")
    @Column(length = 30)
    private String listaDeRegimenes;
    @NotBlank(message = "Campo Requerido")
    @Column(length = 30)
    private String gestion;
    @NotBlank(message = "Campo Requerido")
    @Column(length = 30)
    private String reparticion;
    @NotBlank(message = "Campo Requerido")
    @Column(length = 30)
    private String supervision;
    @NotBlank(message = "Campo Requerido")
    @Column(length = 30)
    private String regional;
    @NotBlank(message = "Campo Requerido")
    @Column(length = 30)
    private String delegacion;
    @NotBlank(message = "Campo Requerido")
    @Column(length = 30)
    private String zona;
    @NotBlank(message = "Campo Requerido")
    @Column(length = 30)
    private String comparteEdificio;
    @NotBlank(message = "Campo Requerido")
    @Column(length = 30)
    private String calle;
    @NotBlank(message = "Campo Requerido")
    @Column(length = 30)
    private String numeroCalle;
    @Column(length = 30)
    private String barrio;
    @ManyToOne
    private Localidad localidad;
    @NotBlank(message = "Campo Requerido")
    @Column(length = 30)
    private String codigoPostal;

    private Integer telefono;
    @Email(message = "Formato incorrecto")
    @Column(length = 30)
    private String email;

    @Column(length = 30)
    private String emailAlternativo;
    @NotBlank(message = "Campo Requerido")
    @Column(length = 30)
    private String fechaCreacion;
    @Column(length = 30)
    private String anioResolucion;
    @NotBlank(message = "Campo Requerido")
    @Column(length = 30)
    private String numeroResolucion;
   // @Temporal(TemporalType.DATE)
   @Column(length = 30)
    private String fechaCierre;
    @Column(length = 30)
    private String anioResolucionCierre;
    @Column(length = 30)
    private String numeroResolucionCierre;

    //contexto
    @NotBlank(message = "Campo Requerido")
    @Column(length = 30)
    private String ambito;
    @NotBlank(message = "Campo Requerido")
    @Column(length = 30)
    private String modalidadCursado;
    @NotBlank(message = "Campo Requerido")
    @Column(length = 30)
    private String comparteEntrada;
    @NotBlank(message = "Campo Requerido")
    @Column(length = 30)
    private String servicioNutricional;
    @NotBlank(message = "Campo Requerido")
    @Column(length = 30)
    private String especialidad;//educaion especial

    //datoa institucionales
    @NotBlank(message = "Campo Requerido")
    @Column(length = 30)
    private String alimentacionGratuita;
    @NotBlank(message = "Campo Requerido")
    @Column(length = 30)
    private String escuelaFrontera;
    @NotBlank(message = "Campo Requerido")
    @Column(length = 30)
    private String sector;
    @NotBlank(message = "Campo Requerido")
    @Column(length = 30)
    private String proyectosAsignados;
    @NotBlank(message = "Campo Requerido")
    @Column(length = 30)
    private String jornadaExtendida;
    @NotBlank(message = "Campo Requerido")
    @Column(length = 30)
    private String medioDeAcceso;
    @NotBlank(message = "Campo Requerido")
    @Column(length = 30)
    private String lineasDeColectivosDeAcceso;
    @Column(length = 30)
    @NotBlank(message = "Campo Requerido")
    private String escuelaAlbergue;


    //infraestructura Tecnologica-equipamiento
    @NotBlank(message = "Campo Requerido")
    @Column(length = 30)
    private String equipamientoAlumnos;
    @NotBlank(message = "Campo Requerido")
    @Column(length = 30)
    private String equipamientoDocentes;

    @NotBlank(message = "Campo Requerido")
    @Column(length = 30)
    private String equipamientoAdministrativos;

//infraestructura Tecnologica-conectividad
    @Column(length = 30)
    private String conectividad;
    @Column(length = 30)
    private String proveedorServicio;
    @Column(length = 30)
    private String anchoBanda;
    @Column(length = 30)
    private String financiado;
    @Column(length = 30)
    private String tipoDeConexion;
    @Column(length = 30)
    private String accesodeConexion;
    @Column(length = 30)
    private Integer aulasConConexion;
    @Column(length = 30)
    private String funciona;


    //infraestructura edificia
    private Integer aulas;
    @NotBlank(message = "Campo Requerido")
    @Column(length = 30)
    private String cierrePerimetral;

    @Column(length = 30)
    private String calefaccion;
    @Column(length = 30)
    private String ventilacion;
    @Column(length = 30)
    private String alarma;
    @Column(length = 30)
    private String edificio;


    @NotBlank(message = "Campo Requerido")
    @Column(length = 30)
    private String tipoGas;
    @NotBlank(message = "Campo Requerido")
    @Column(length = 30)
    private String tipoLuz;
    @NotBlank(message = "Campo Requerido")
    @Column(length = 30)
    private String agua;
    @NotBlank(message = "Campo Requerido")
    @Column(length = 30)
    private String cloacas;
    @NotBlank(message = "Campo Requerido")
    @Column(length = 30)
    private String serviciosMunicipales;
    @NotBlank(message = "Campo Requerido")
    @Column(length = 30)
    private String laboratorioInformatica;
    @NotBlank(message = "Campo Requerido")
    @Column(length = 30)
    private String laboratorioCiencias;

    @NotBlank(message = "Campo Requerido")
    @Column(length = 30)
    private String espacioTechado;



    @NotNull(message = "Campo Requerido")
    private Boolean cancha;

    @NotBlank(message = "Campo Requerido")
    @Column(length = 30)
    private String medidorLuz;
    @NotBlank(message = "Campo Requerido")
    @Column(length = 30)
    private String medidorGaz;
    @NotBlank(message = "Campo Requerido")
    @Column(length = 30)
    private String cocina;
    @NotBlank(message = "Campo Requerido")
    @Column(length = 30)
    private String comedor;

    private Boolean biblioteca;

    private Boolean salonMusica;

    private Boolean sum;

    private Boolean bombaAgua;


    private Boolean sereno;

    private Boolean sistemaSeguridad;

    private Boolean senialCelular;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Division> division;



}
