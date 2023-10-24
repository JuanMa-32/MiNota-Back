package com.juanma32.MiNotaEscolar.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.Date;
import java.util.List;

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
    @NotNull(message = "campo Obligatorio")
    private Date alta;
    @Temporal(TemporalType.DATE)
    private Date baja;

    @NotNull(message = "campo Obligatorio")
    private Integer diasCumplir;
    @NotBlank(message = "campo obligatorio")
    private String obligacion;
    @NotBlank(message = "campo obligatorio")
    private String funcion;

    private String observacion;

    private String motivoBaja;

    @JsonIgnoreProperties("servicio")
    @ManyToOne
    @JoinColumn(name = "cargo_id")
    private Cargo cargo;

    @OneToMany
    private List<Novedad> novedades;

}
