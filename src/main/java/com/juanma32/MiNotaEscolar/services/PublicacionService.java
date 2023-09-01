package com.juanma32.MiNotaEscolar.services;

import com.juanma32.MiNotaEscolar.entities.Publicacion;

import java.util.List;
import java.util.Optional;

public interface PublicacionService {

    Publicacion save(Publicacion publicacion);
    void delete(Long id);

    List<Publicacion> findAll();

    Optional<Publicacion> findById(Long id);

}
