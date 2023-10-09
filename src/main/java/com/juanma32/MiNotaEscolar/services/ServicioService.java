package com.juanma32.MiNotaEscolar.services;

import com.juanma32.MiNotaEscolar.entities.Servicio;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface ServicioService {
    Servicio save(Servicio servicio);
    Optional<Servicio> findById(Long id);

    Page<Servicio> findAll(Pageable page,Long idEscuela);



}
