package com.juanma32.MiNotaEscolar.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.util.List;

@Entity
@Table(name = "cargos")
@Data
public class Cargo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Campo obligatorio")
    private String motivo;
    @NotBlank(message = "Campo obligatorio")
    private String fundamento;

    private String carrera;

    private String materia;
    @NotBlank(message = "Campo obligatorio")
    private String regimen;
    @NotBlank(message = "Campo obligatorio")
    private String condicionCargo;
    @NotBlank(message = "Campo obligatorio")
    private String turno;
    @NotBlank(message = "Campo obligatorio")
    private String resolucionCreacion;
    @NotBlank(message = "Campo obligatorio")
    private String codigoJunta;

    @OneToOne
    private Division division;
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Servicio> servicio;
}
