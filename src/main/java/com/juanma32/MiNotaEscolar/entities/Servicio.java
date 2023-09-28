package com.juanma32.MiNotaEscolar.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import java.util.Date;

@Entity
@Table(name = "servicios")
@Data
public class Servicio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Usuario usuario;

    @NotBlank(message = "campo obligatorio")
    private String situacionRevista;
    @Temporal(TemporalType.DATE)
    private Date alta;
    @Temporal(TemporalType.DATE)
    private Date baja;

    @NotNull(message = "campo Obligatorio")
    private Integer diasCumplir;
    @NotBlank(message = "campo obligatorio")
    private String obligacion;
    @NotBlank(message = "campo obligatorio")
    private String funcion;
    @NotBlank(message = "campo obligatorio")
    private String observacion;

}
