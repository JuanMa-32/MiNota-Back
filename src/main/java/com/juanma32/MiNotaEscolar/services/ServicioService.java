package com.juanma32.MiNotaEscolar.services;

import com.juanma32.MiNotaEscolar.entities.Servicio;

import java.util.List;
import java.util.Optional;

public interface ServicioService {
    Servicio save(Servicio servicio);
    Optional<Servicio> findById(Long id);

}
