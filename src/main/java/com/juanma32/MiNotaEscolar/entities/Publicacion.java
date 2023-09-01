package com.juanma32.MiNotaEscolar.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.Date;

@Entity
@Table(name = "publicaciones")
@Data
public class Publicacion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Campo requerido")
    private String titulo;

    @NotBlank(message = "Campo requerido")
    @Column(columnDefinition = "TEXT")
    private String cuerpo;

  //  @NotNull
    //private Usuario remitente;

    //@NotNull
    //private Usuario destinatario;

    @Temporal(TemporalType.DATE)
    private Date fechaDePublicacion;

    @PrePersist //logica adicional antes que la entidad sea peristida
    public void addDate(){
        this.fechaDePublicacion = new Date();
    }
}
