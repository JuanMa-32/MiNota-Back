package com.juanma32.MiNotaEscolar.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.Date;

@Entity
@Table(name = "novedades")
@Data
public class Novedad {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Campo Obligatorio")
    private String articulo;

    @NotNull
    @Temporal(TemporalType.DATE)
    private Date desde;
    @NotNull
    @Temporal(TemporalType.DATE)
    private Date hasta;

    private Integer dias;
}
