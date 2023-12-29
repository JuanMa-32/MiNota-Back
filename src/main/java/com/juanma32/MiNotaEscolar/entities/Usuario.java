package com.juanma32.MiNotaEscolar.entities;

import com.juanma32.MiNotaEscolar.Enums.Role;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.Date;

@Entity
@Table(name = "usuarios")
@Getter@Setter
@AllArgsConstructor @NoArgsConstructor
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
    private String genero;
    @Temporal(TemporalType.DATE)
    private Date nacimiento;

    private String email;
    private String password;
    @Enumerated(value = EnumType.STRING)
    private Role role;

    //datos domicilio

    private String calle;
    private Integer numero;
    private String depto;
    private String piso;
    @ManyToOne
    private Localidad localidad;

    private Integer codigoPostal;
    private String barrio;
    private String manzana;
    private String casa;
    private String referenciaDomicilio;

    //datos personales
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

    //alumnos
    private String condicion;
    @Temporal(TemporalType.DATE)
    private Date desde;
    private String causaEntrada;

    private String cicloLectivo;

    
}
