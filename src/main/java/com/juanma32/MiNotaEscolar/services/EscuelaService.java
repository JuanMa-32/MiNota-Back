package com.juanma32.MiNotaEscolar.services;

import com.juanma32.MiNotaEscolar.entities.Escuela;

import java.util.List;
import java.util.Optional;

public interface EscuelaService {

    Escuela save(Escuela escuela);
    List<Escuela> findAll();
    Optional<Escuela> findById(Long id);


}
