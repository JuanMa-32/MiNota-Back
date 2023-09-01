package com.juanma32.MiNotaEscolar.entities;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name="localidades")
@Data
public class Localidad {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String localidad;
    private String municipio;
}
