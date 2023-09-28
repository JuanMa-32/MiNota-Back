package com.juanma32.MiNotaEscolar.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import java.util.Date;

@Entity
@Table(name = "usuarios")
@Data
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "Campo obligatorio")
    private Integer cuil;
    @NotNull(message = "Campo obligatorio")
    private Integer dni;
    @NotBlank(message = "Campo obligatorio")
    private String apellido;
    @NotBlank(message = "Campo obligatorio")
    private String nombre;
    @NotBlank(message = "Campo obligatorio")
    private String genero;
    @Temporal(TemporalType.DATE)
    private Date nacimiento;
    @NotBlank(message = "Campo obligatorio")
    private String calle;
    @NotNull(message = "Campo obligatorio")
    private Integer numero;
    @NotBlank(message = "Campo obligatorio")
    private String depto;
    @NotBlank(message = "Campo obligatorio")
    private String piso;
    @OneToOne
    private Localidad localidad;
    @NotNull(message = "Campo obligatorio")
    private Integer codigoPostal;
    @NotBlank(message = "Campo obligatorio")
    private String barrio;
    @NotBlank(message = "Campo obligatorio")
    private String manzana;
    @NotBlank(message = "Campo obligatorio")
    private String casa;

    private String referenciaDomicilio;

    private String nivelEstudio;

    private String ocupacion;

    private Integer telefonoFijo;
    private String prestadora;
    private Integer celular;
    private String estadoCivil;
    private String obraSocial;
    private String grupoSanguineo;
    private String lugarNacimiento;
    private String nacionalidad;
}
