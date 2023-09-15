package com.juanma32.MiNotaEscolar.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.Date;

@Entity
@Table(name = "divisiones")
@Data
public class Division {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "campo obligatorio")
    private String curso;
    @NotBlank(message = "campo obligatorio")
    private String divisionCurso;
    @NotBlank(message = "campo obligatorio")
    private String turno;
    @NotBlank(message = "campo obligatorio")
    private String resolucion;
    @NotBlank(message = "campo obligatorio")
    private String carrera;
    @NotBlank(message = "campo obligatorio")
    private String modalidad;
    @NotNull(message = "fecha de alta obligatoria")
    @Temporal(TemporalType.DATE)
    private Date alta;
    @Temporal(TemporalType.DATE)
    private Date baja;

    @OneToOne
    private Cargo cargo;




}
